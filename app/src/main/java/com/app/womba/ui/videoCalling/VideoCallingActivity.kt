package com.app.womba.ui.videoCalling

import android.content.Context
import android.media.AudioManager
import android.os.Build
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.app.womba.BR
import com.app.womba.R
import com.app.womba.base.BaseActivity
import com.app.womba.databinding.ActivityVideoCallingBinding
import com.app.womba.model.AppointmentModel
import com.app.womba.model.VideoTokenModel
import com.app.womba.model.postResponseModel.GenerateToken
import com.app.womba.utils.InterConsts
import com.app.womba.utils.customViews.LoadingDialog
import com.app.womba.utils.responseHandler
import com.vidyo.VidyoClient.Connector.Connector
import com.vidyo.VidyoClient.Connector.ConnectorPkg
import com.vidyo.VidyoClient.Device.Device
import com.vidyo.VidyoClient.Device.LocalCamera
import com.vidyo.VidyoClient.Endpoint.ChatMessage
import com.vidyo.VidyoClient.Endpoint.Participant
import kotlinx.android.synthetic.main.activity_video_calling.*
import java.io.UnsupportedEncodingException
import java.net.URLEncoder
import java.util.*

class VideoCallingActivity : BaseActivity<ActivityVideoCallingBinding, VideoCallingVM>(),
    Connector.IConnect, Connector.IRegisterMessageEventListener,
    Connector.IRegisterLocalCameraEventListener, View.OnClickListener {

    override val viewModel: VideoCallingVM
        get() = ViewModelProviders.of(this).get(VideoCallingVM::class.java)
    override val layoutId: Int = R.layout.activity_video_calling
    override val bindingVariable: Int = BR.viewModel
    override val context: Context = this
    private var vc: Connector? = null

    override fun onCreate() {

        ConnectorPkg.setApplicationUIContext(this)
        ConnectorPkg.initialize()

    }

    var mVideoToken: VideoTokenModel.DataBean? = null
    var mData: AppointmentModel.DataBean? = null
//    var mDataDocInfo: AppointmentModel.DataBean.DoctorInfoBean? = null

    override fun initListeners() {

        frameMic.setOnClickListener(this)
        frameCall.setOnClickListener(this)
        frameVideo.setOnClickListener(this)


        mData = intent.getParcelableExtra<AppointmentModel.DataBean>(InterConsts.EXTRA)
//        mDataDocInfo =
//            intent.getParcelableExtra<AppointmentModel.DataBean.DoctorInfoBean>(InterConsts.DOCTOR_INFO)

        mViewModel!!.generateToken(GenerateToken(mData!!.patientId!!, mData!!.get_id()!!))

        mViewModel!!.getLoader().observe(this,
            Observer { mData ->
                if (mData) {
                    LoadingDialog.getLoader().showLoader(this)
                } else {
                    LoadingDialog.getLoader().dismissLoader()
                }
            })

        mViewModel!!.getResponse().observe(this,
            Observer<VideoTokenModel> { mData ->
                if (mData != null && responseHandler(mData.code, mData.message)) {
                    mVideoToken = mData.data!!
                    Timer().schedule(
                        object : TimerTask() {
                            override fun run() {

                                startVideoCall()
                            }
                        },
                        1000
                    )
                }
            })
    }


    override fun onStart() {
        super.onStart()
    }


    fun startVideoCall() {
        txtConnecting.visibility = View.VISIBLE


        Timer().schedule(
            object : TimerTask() {
                override fun run() {
                    vc = Connector(
                        videoFrame, Connector.ConnectorViewStyle.VIDYO_CONNECTORVIEWSTYLE_Tiles, 15,
                        "warning info@VidyoClient info@VidyoConnector", "", 0
                    )
                    vc!!.showViewAt(videoFrame, 0, 0, videoFrame.getWidth(), videoFrame.getHeight())
                    vc!!.cycleMicrophone()
                    vc!!.cycleSpeaker()
                    connectVideoCall()
                }
            },
            1000
        )
    }

    fun connectVideoCall() {
        val displayName: String ="Doctor"
        var query: String? = ""
        try {
            query = URLEncoder.encode(mVideoToken!!.token, "utf-8")
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }
        vc!!.connect(
            "prod.vidyo.io",
            mVideoToken!!.token,
            displayName,
            mVideoToken!!.get_id(),
            this
        )
    }


    fun disconnectVideoCall() {
        if (vc != null) vc!!.disconnect()
        finish()
    }

    override fun onLocalCameraAdded(localCamera: LocalCamera?) {}

    override fun onLocalCameraRemoved(localCamera: LocalCamera?) {}

    override fun onLocalCameraSelected(localCamera: LocalCamera?) {}

    override fun onLocalCameraStateUpdated(
        localCamera: LocalCamera?,
        deviceState: Device.DeviceState?
    ) {
    }

    override fun onSuccess() {
        txtConnecting.visibility = View.GONE
        Log.d("com.app.activities.VideoCallActivity.TAG", "onSuccess: ")
    }

    override fun onFailure(connectorFailReason: Connector.ConnectorFailReason) {
        Log.d(
            "com.app.activities.VideoCallActivity.TAG",
            "onFailure: " + connectorFailReason.name
        )
    }

    override fun onDisconnected(connectorDisconnectReason: Connector.ConnectorDisconnectReason) {
        Log.d(
            "TAG",
            "onDisconnected: " + connectorDisconnectReason.name
        )
    }

    override fun onChatMessageReceived(
        participant: Participant?,
        chatMessage: ChatMessage?
    ) {
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.frameMic -> toogleMicrophone()
            R.id.frameCall -> {
                disconnectVideoCall()
                toogleVideo()
            }
            R.id.frameVideo -> toogleVideo()
            else -> {
            }
        }
    }


    private fun toogleVideo() {
        if (videoFrame.visibility == View.VISIBLE) {
            // Its visible
            videoFrame.visibility = View.INVISIBLE
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                imgVideo.setImageDrawable(
                    resources.getDrawable(
                        R.drawable.ic_videocam_off_white_24dp,
                        applicationContext.theme
                    )
                )
            } else {
                imgVideo.setImageDrawable(resources.getDrawable(R.drawable.ic_videocam_off_white_24dp))
            }
        } else {
            // Either gone or invisible
            videoFrame.visibility = View.VISIBLE
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                imgVideo.setImageDrawable(
                    resources.getDrawable(
                        R.drawable.ic_videocam_white_24dp,
                        applicationContext.theme
                    )
                )
            } else {
                imgVideo.setImageDrawable(resources.getDrawable(R.drawable.ic_videocam_white_24dp))
            }
        }
    }

    private fun toogleMicrophone() {
        val audioManager =
            applicationContext.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        audioManager.mode = AudioManager.MODE_IN_CALL
        if (audioManager.isMicrophoneMute == false) {
            audioManager.isMicrophoneMute = true
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                imgMic.setImageDrawable(
                    resources.getDrawable(
                        R.drawable.ic_mic_off_white_24dp,
                        applicationContext.theme
                    )
                )
            } else {
                imgMic.setImageDrawable(resources.getDrawable(R.drawable.ic_mic_off_white_24dp))
            }
        } else {
            audioManager.isMicrophoneMute = false
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                imgMic.setImageDrawable(
                    resources.getDrawable(
                        R.drawable.ic_mic_white_24dp,
                        applicationContext.theme
                    )
                )
            } else {
                imgMic.setImageDrawable(resources.getDrawable(R.drawable.ic_mic_white_24dp))
            }
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (vc != null) {
            videoFrame.removeAllViews()
            videoFrame.invalidate()
            vc!!.disconnect()
        }
    }


}
