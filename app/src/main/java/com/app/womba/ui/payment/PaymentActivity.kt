package com.app.womba.ui.payment

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.app.womba.BR
import com.app.womba.R
import com.app.womba.base.BaseActivity
import com.app.womba.databinding.ActivityPaymentBinding
import com.app.womba.model.BookingResponse
import com.app.womba.model.postResponseModel.PaymentModel
import com.app.womba.model.postResponseModel.StripeToken
import com.app.womba.network.STRIPE_PUBLISHABLE_KEY
import com.app.womba.ui.appointments.AppointmentConfirmedActivity
import com.app.womba.utils.responseHandler
import com.app.womba.utils.showOkayAlert
import com.app.womba.utils.showToast
import com.stripe.android.ApiResultCallback
import com.stripe.android.Stripe
import com.stripe.android.model.Card
import com.stripe.android.model.Token
import kotlinx.android.synthetic.main.activity_payment.*

class PaymentActivity : BaseActivity<ActivityPaymentBinding, PaymentVM>() {

    override val viewModel: PaymentVM
        get() = ViewModelProviders.of(this).get(PaymentVM::class.java)
    override val layoutId: Int = R.layout.activity_payment
    override val bindingVariable: Int = BR.viewModel
    override val context: Context = this
    var checkslot = ""
    override fun onCreate() {
        paymentAmount.text = intent.getStringExtra("fee")
        checkslot = intent.getStringExtra("checkslot")
    }

    fun setProgress(boolean: Boolean) {
        if (boolean) {
            confirm_button.isEnabled = false
            progressBar.visibility = View.VISIBLE
        } else {
            confirm_button.isEnabled = true
            progressBar.visibility = View.GONE
        }
    }

    override fun initListeners() {

        mViewModel!!.getResponse().observe(this,
            androidx.lifecycle.Observer<BookingResponse> { mData ->
                if (mData != null && responseHandler(mData.code, mData.message)) {
                    setProgress(false)
                    val intent = Intent(mContext, AppointmentConfirmedActivity::class.java)
                    intent.putExtra("appointmentDate", intent.getStringExtra("appointmentDate"))
                    intent.putExtra("checkslot", checkslot)
                    startActivity(intent)
                    finish()
                }
            })

        confirm_button.setOnClickListener {
            hideKeyboard()
            val stripe = Stripe(this@PaymentActivity, STRIPE_PUBLISHABLE_KEY)

            if (null != mViewDataBinding!!.cardInputWidgetStripe.card) {
                if (null != mViewDataBinding!!.cardInputWidgetStripe.card!!.number &&
                    null != mViewDataBinding!!.cardInputWidgetStripe.card!!.expMonth &&
                    null != mViewDataBinding!!.cardInputWidgetStripe.card!!.expYear &&
                    null != mViewDataBinding!!.cardInputWidgetStripe.card!!.cvc
                ) {
                    setProgress(true)
                    val card = Card.create(
                        mViewDataBinding!!.cardInputWidgetStripe.card!!.number,
                        mViewDataBinding!!.cardInputWidgetStripe.card!!.expMonth,
                        mViewDataBinding!!.cardInputWidgetStripe.card!!.expYear,
                        mViewDataBinding!!.cardInputWidgetStripe.card!!.cvc
                    )
                    if (!card.validateNumber()) {
                        // Do not continue token creation.
                        setProgress(false)
                        showOkayAlert(this,
                            getString(R.string.app_name),
                            getString(R.string.please_enter_a_valid_card_number),
                            getString(R.string.ok),
                            DialogInterface.OnClickListener { dialogInterface, i ->
                                dialogInterface.dismiss()
                            })

                    } else {
                        stripe.createToken(
                            card,
                            object : ApiResultCallback<Token> {
                                override fun onSuccess(result: Token) {
                                    // Send token to your server
                                    setProgress(true)

                                    mViewModel!!.bookAppointment(
                                        PaymentModel(
                                            intent.getStringExtra("appointmentDate")!!,
                                            intent.getStringExtra("doctorId")!!,
                                            intent.getStringExtra("endTime")!!,
                                            intent.getStringExtra("patientId")!!,
                                            intent.getStringExtra("reason")!!,
                                            intent.getStringExtra("startTime")!!,
                                            StripeToken(result.id, result.type),
                                            intent.getStringExtra("userTimezone")!!
                                        )
                                    )
                                }

                                override fun onError(e: Exception) {
                                    // Show localized error message
                                    e.message?.let { it1 ->
                                        setProgress(false)
                                        showToast(
                                            it1
                                        )
                                    }
                                }
                            }
                        )
                    }
                } else {
                    setProgress(false)
                    showOkayAlert(this,
                        getString(R.string.app_name),
                        getString(R.string.error_card),
                        getString(R.string.ok),
                        DialogInterface.OnClickListener { dialogInterface, i ->
                            dialogInterface.dismiss()
                        })
                }
            } else {
                showOkayAlert(this,
                    getString(R.string.app_name),
                    getString(R.string.error_card),
                    getString(R.string.ok),
                    DialogInterface.OnClickListener { dialogInterface, i ->
                        dialogInterface.dismiss()
                    })
            }
        }

    }

}
