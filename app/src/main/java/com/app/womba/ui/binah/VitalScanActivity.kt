package com.app.womba.ui.binah

import ai.binah.hrv.bnh_hrv_sdk
import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.graphics.*
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.*
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import com.app.womba.BR
import com.app.womba.R
import com.app.womba.base.BaseActivity
import com.app.womba.databinding.ActivityVitalScanBinding
import com.app.womba.utils.InterConsts
import com.app.womba.utils.navigateClass
import kotlinx.android.synthetic.main.activity_vital_scan.*
import java.lang.ref.WeakReference
import java.util.*

class VitalScanActivity : BaseActivity<ActivityVitalScanBinding, VitalScanVM>(), View.OnClickListener {

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.activity_vital_scan

    override val viewModel: VitalScanVM
        get() = ViewModelProviders.of(this).get(VitalScanVM::class.java)

    override val context: Context
        get() =this

    private var mAlgo: bnh_hrv_sdk? = null
    private var mPaint: Paint? = null

    override fun onCreate() {

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                REQUEST_CAMERA_PERMISSION
            )
        }

        mViewDataBinding!!.bpmValue.text = ""
        mHRVRunning = false
        val mSDKResponeHadler =
            EngineMessageHandler(this)
        mAlgo = bnh_hrv_sdk(
            this, this,
            mViewDataBinding!!.texture, mSDKResponeHadler, bnh_hrv_sdk.AspectRatios.Aspect_ratio16x9
        )
        Log.e("HRV", "SDK was created")
        mViewDataBinding!!.stressValue.text = ""
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaint!!.style = Paint.Style.STROKE
        mPaint!!.color = Color.parseColor("#315D9E")
        mPaint!!.strokeWidth = 10f


    }

    inner class EngineMessageHandler constructor(activity: Context) :
        Handler() {
        private val mActivity: WeakReference<Context> = WeakReference(activity)
        override fun handleMessage(msg: Message) {
            val activity = mActivity.get()
            if (activity != null) {
                when (msg.what) {
                    0 -> {
                        val v = msg.obj as Double //returning pulse rate
                        if (v > 0) {
                            val output =
                                String.format(Locale.US, "%3.0f", v)
                            hideHeartLoader()
                            mViewDataBinding!!.bpmValue.text = output
                        } else {
                            mViewDataBinding!!.bpmValue.text = ""
                        }
                    }
                    1 ->                         /*double value = (double) msg.obj;//returning trace
                        if (mLastXValue > mMaxXPoints) {
                            mGraphSeries.appendData(new DataPoint(mLastXValue, value), true, mMaxXPoints);
                        } else {
                            mGraphSeries.appendData(new DataPoint(mLastXValue, value), false, mMaxXPoints);
                        }*/mLastXValue += 1
                    3 -> {
                        jString = msg.obj as String
                        mAlgo!!.StopHRV()
                        enablePlayButton()
                        Log.e("Data", jString)
                    }
                    5 -> {
                        val stress = msg.obj as String
                        mViewDataBinding!!.stressValue.text = stress
                    }
                    4 -> {
                        val a =
                            mViewDataBinding!!.overlappView.holder.surface
                        if (a.isValid == false) return
                        try {
                            val sc = a.lockCanvas(null)
                            if (sc == null) {
                                Log.i(TAG, "Canvas")
                            } else {
                                sc.drawColor(
                                    Color.TRANSPARENT,
                                    PorterDuff.Mode.CLEAR
                                )
                                a.unlockCanvasAndPost(sc)
                            }
                        } catch (e: IllegalArgumentException) {
                        }
                    }
                    2 -> {
                        val rectangleFace = msg.obj as RectF
                        val a: Surface
                        val sc: Canvas?
                        a = mViewDataBinding!!.overlappView.holder.surface
                        if (a.isValid == false) return
                        try {
                            sc = a.lockCanvas(null)
                            if (sc == null) {
                                Log.i(TAG, "Canvas")
                            } else {
                                sc.drawColor(
                                    Color.TRANSPARENT,
                                    PorterDuff.Mode.CLEAR
                                )
                                sc.drawRect(rectangleFace, mPaint!!)
                                a.unlockCanvasAndPost(sc)
                            }
                        } catch (e: IllegalArgumentException) {
                        }
                    }
                    100 -> {
                        val b = msg.obj as Double
                        if (b > 0) {
                            val output =
                                String.format(Locale.US, "%3.0f", b)
                            hideBreathLoader()
                            mViewDataBinding!!.BreathValue.text = output
                        } else {
                            mViewDataBinding!!.BreathValue.text = ""
                        }
                    }
                    110 -> {
                        val spo2 = msg.obj as Double
                        if (spo2 > 0) {
                            val output =
                                String.format(Locale.US, "%3.0f", spo2)
                            hideRespirationLoader()
                            mViewDataBinding!!.Spo2Value.text = output
                        } else {
                            mViewDataBinding!!.Spo2Value.text = ""
                        }
                    }
                    else -> Log.d(
                        TAG,
                        "Unknown message from lib id: " + msg.what
                    )
                }
            }
        }

    }

    private fun showHeartLoader() {
        mViewDataBinding!!.heartLoader.show()
    }

    private fun hideHeartLoader() {
        mViewDataBinding!!.heartLoader.hide()
    }

    private fun showRespirationLoader() {
        mViewDataBinding!!.respirationLoader.show()
    }

    private fun hideRespirationLoader() {
        mViewDataBinding!!.respirationLoader.hide()
    }

    private fun showBreathLoader() {
        mViewDataBinding!!.breathLoader.show()
    }

    private fun hideBreathLoader() {
        mViewDataBinding!!.breathLoader.hide()
    }

    private fun showAllLoaders() {
        showBreathLoader()
        showHeartLoader()
        showRespirationLoader()
    }


    private fun setVideoSize(
        surfaceView: SurfaceView?,
        videoWidth: Int,
        videoHeight: Int
    ) {

        // // Get the dimensions of the video
        val videoProportion = videoWidth.toFloat() / videoHeight.toFloat()
        val outSize = Point()
        this.windowManager.defaultDisplay.getSize(outSize)
        // Get the width of the screen
        val screenWidth = outSize.x
        val screenHeight = outSize.y
        val screenProportion =
            screenWidth.toFloat() / screenHeight.toFloat()

        // Get the SurfaceView layout parameters
        val lp = surfaceView!!.layoutParams
        if (videoProportion > screenProportion) {
            lp.width = screenWidth
            lp.height = (screenWidth.toFloat() / videoProportion).toInt()
        } else {
            lp.width = (videoProportion * screenHeight.toFloat()).toInt()
            lp.height = screenHeight
        }
        // Commit the layout parameters
        surfaceView.layoutParams = lp
    }

    private val mCallback: SurfaceHolder.Callback = object : SurfaceHolder.Callback {
        override fun surfaceCreated(holder: SurfaceHolder) {}
        override fun surfaceDestroyed(holder: SurfaceHolder) {
            mAlgo!!.onPause()
            holder.removeCallback(this)
        }

        override fun surfaceChanged(
            holder: SurfaceHolder,
            format: Int,
            width: Int,
            height: Int
        ) {
            setVideoSize(mViewDataBinding!!.overlappView, width, height)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                Toast.makeText(
                    this,
                    "Sorry!!!, you can't use this app without granting permission",
                    Toast.LENGTH_LONG
                ).show()
                this.finish()
            }
        }
    }

    private fun stopHRV() {
        mCountDownTimer?.cancel()
        hideVital()
        val x = mAlgo!!.getHRVStatus(1)
        mAlgo!!.StopHRV()
        mHRVRunning = false
        if (x) {
            enablePlayButton()
            return
        }
        enablePlayButtonDisableGrapth(false)
    }

    private fun startHRV() {
        showAllLoaders()
        showVitals()
        mHRVRunning = true
        mViewDataBinding!!.stressValue.text = ""
        mViewDataBinding!!.bpmValue.text = ""
        mViewDataBinding!!.Spo2Value.text = ""
        mViewDataBinding!!.BreathValue.text = ""
        mViewDataBinding!!.timerView.text = ""
        startCountDown()

        mAlgo!!.StartHRV(true)
    }

    private fun enablePlayButtonDisableGrapth(flg: Boolean) {
        mViewDataBinding!!.buttonHRV.isEnabled = flg
        mViewDataBinding!!.bpmValue.text = ""
        mViewDataBinding!!.timerView.text = ""
        //mGraph.setVisibility(View.INVISIBLE);
        mViewDataBinding!!.playButton.setOnClickListener(this)
        mViewDataBinding!!.playButton.setImageResource(R.drawable.play)
        mHRVRunning = false
    }

    fun enablePlayButton() {
        mViewDataBinding!!.timerView.text = ""
        mHRVRunning = false
    }

    fun openResults() {
        val openResultIntent = Intent(this, VitalDetailActivity::class.java)
        openResultIntent.putExtra(InterConsts.HEALTH_REPORT, jString)
        openResultIntent.putExtra("subject", mLastSubject)
        startActivity(openResultIntent)
    }

    override fun onResume() {
        super.onResume()
        mViewDataBinding!!.overlappView.holder.addCallback(mCallback)
        mViewDataBinding!!.overlappView.holder.setFormat(PixelFormat.TRANSLUCENT)
        mViewDataBinding!!.overlappView.setZOrderMediaOverlay(true)
        mViewDataBinding!!.overlappView.setZOrderOnTop(true)
        mAlgo!!.onResume()
    }

    public override fun onPause() {
        super.onPause()
    }

    public override fun onStop() {
        super.onStop()
    }

    companion object {
        private const val REQUEST_CAMERA_PERMISSION = 1
        private const val TAG = "HRV"
        private var mLastSubject: String? = "no_Subject"
        private var mLastXValue = 0
        private var progressNumber: Float = 0f
        private var jString: String? = null
        private var mHRVRunning = false
        private const val max_experiment_time_in_seconds = 160
        private var mCountDownTimer: CountDownTimer? = null

    }

    private fun startCountDown() {
        mCountDownTimer = object : CountDownTimer(160000, 1000) {
            override fun onTick(l: Long) {
                progressNumber = progressNumber.plus(0.625f)
                mViewDataBinding!!.progress.text = "${progressNumber.toInt()}%"
                /*val seconds = (l / 1000).toInt()
                mViewDataBinding!!.timerView.text = (String.format("%02d", seconds / 60)
                        + ":" + String.format("%02d", seconds % 60))*/
            }

            override fun onFinish() {
                stopHRV()
                gotoResultScreen()
            }
        }
        mCountDownTimer?.start()
    }

    private fun gotoResultScreen() {
        Handler().postDelayed({
            openResults()
        }, 1000)
    }


    override fun onClick(p0: View?) {
        this.hideKeyboard()
        when (p0?.id) {
            R.id.startButton -> {
                startHRV()
            }
            R.id.stopButton -> {
                mHRVRunning = false
                stopHRV()
            }
        }
    }

    private fun hideVital() {
        progressNumber = 0f
        mViewDataBinding!!.progress.visibility = View.GONE
        mViewDataBinding!!.startButton.visibility = View.VISIBLE
        mViewDataBinding!!.stopButton.visibility = View.GONE
        mViewDataBinding!!.vitalsLayout.visibility = View.GONE
    }

    private fun showVitals() {
        progressNumber = 0f
        mViewDataBinding!!.progress.visibility = View.VISIBLE
        mViewDataBinding!!.startButton.visibility = View.GONE
        mViewDataBinding!!.stopButton.visibility = View.VISIBLE
        mViewDataBinding!!.vitalsLayout.visibility = View.VISIBLE
    }

    override fun onBackPressed() {
        super.onBackPressed()
        mCountDownTimer?.cancel()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun initListeners() {
        mViewDataBinding!!.startButton.setOnClickListener(this)
        mViewDataBinding!!.stopButton.setOnClickListener(this)
        imgBack.setOnClickListener {
            finish()
        }
    }

}
