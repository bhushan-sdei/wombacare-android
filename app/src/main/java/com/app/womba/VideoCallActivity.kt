package com.app.womba

import android.app.Activity
import android.content.Context
import android.media.AudioManager
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.vidyo.VidyoClient.Connector.Connector
import com.vidyo.VidyoClient.Connector.Connector.ConnectorDisconnectReason
import com.vidyo.VidyoClient.Connector.Connector.ConnectorFailReason
import com.vidyo.VidyoClient.Connector.ConnectorPkg
import com.vidyo.VidyoClient.Device.Device.DeviceState
import com.vidyo.VidyoClient.Device.LocalCamera
import com.vidyo.VidyoClient.Endpoint.ChatMessage
import com.vidyo.VidyoClient.Endpoint.Participant
import de.hdodenhof.circleimageview.CircleImageView
import java.io.UnsupportedEncodingException
import java.net.URLEncoder
import java.util.*

class VideoCallActivity : AppCompatActivity(),
    Connector.IConnect,
    Connector.IRegisterMessageEventListener,
    View.OnClickListener,
    Connector.IRegisterLocalCameraEventListener {
    private var vc: Connector? = null
    private var videoFrame: FrameLayout? = null
    private var frMic: FrameLayout? = null
    private var frCall: FrameLayout? = null
    private var frVideo: FrameLayout? = null
    var imgMic: CircleImageView? = null
    var imgVideo: CircleImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_screen)
        val toolbar =
            findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        ConnectorPkg.setApplicationUIContext(this)
        ConnectorPkg.initialize()
        videoFrame = findViewById<View>(R.id.videoFrame) as FrameLayout
        frMic = findViewById<View>(R.id.frameMic) as FrameLayout
        frCall = findViewById<View>(R.id.frameCall) as FrameLayout
        frVideo = findViewById<View>(R.id.frameVideo) as FrameLayout
        imgMic =
            findViewById<View>(R.id.imgMic) as CircleImageView
        imgVideo =
            findViewById<View>(R.id.imgVideo) as CircleImageView
        frMic!!.setOnClickListener(this)
        frCall!!.setOnClickListener(this)
        frVideo!!.setOnClickListener(this)
        Timer().schedule(
            object : TimerTask() {
                override fun run() {
                    startVideoCall()
                }
            },
            1000
        )
    }

    /**
     * Default activity life cycle method
     */
    public override fun onStop() {
        super.onStop()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun startVideoCall() {
        Timer().schedule(
            object : TimerTask() {
                override fun run() {
                    vc = Connector(
                        videoFrame,
                        Connector.ConnectorViewStyle.VIDYO_CONNECTORVIEWSTYLE_Tiles,
                        15,
                        "warning info@VidyoClient info@VidyoConnector",
                        "",
                        0
                    )
                    vc!!.showViewAt(videoFrame, 0, 0, videoFrame!!.width, videoFrame!!.height)
                    vc!!.cycleMicrophone()
                    vc!!.cycleSpeaker()
                    connectVideoCall()
                }
            },
            1000
        )
    }

    fun connectVideoCall() {
        val token: String = "cHJvdmlzaW9uAFNoYW5lU2hhbmVTaGFuZUA1MWVkMDAudmlkeW8uaW8ANjM2OTY3ODY5NzcAdW5kZWZpbmVkAGYxYjQyYmU5MjAyZjA2MDAyNDM3MzY4N2ZjZGE3Yzg5OTRkMDVhNWY1NjYyODgzOWQ5YWI5MTQ4NzA5YTY2ZjQyNTc3NDA1NmUxZWViNGM3MjljNmVmYjhlNGM0YWEyOA=="
        //String token = "cHJvdmlzaW9uAFNoYW5lU2hhbmVTaGFuZUA1MWVkMDAudmlkeW8uaW8ANjM2OTY3ODY5NzcAdW5kZWZpbmVkAGYxYjQyYmU5MjAyZjA2MDAyNDM3MzY4N2ZjZGE3Yzg5OTRkMDVhNWY1NjYyODgzOWQ5YWI5MTQ4NzA5YTY2ZjQyNTc3NDA1NmUxZWViNGM3MjljNmVmYjhlNGM0YWEyOA==";
        val displayName: String =
            "SHUBam"+ " " + "verma"
        var query: String? = ""
        try {
            query = URLEncoder.encode(token, "utf-8")
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }
        vc!!.connect("prod.vidyo.io", token,"SHUBam", "5ea3fc8cfee43b23ae36d76c", this)
    }

    fun disconnectVideoCall() {
        if (vc != null) vc!!.disconnect()
        finish()
    }

    override fun onSuccess() {
        Log.d(TAG, "onSuccess: ")
    }

    override fun onFailure(connectorFailReason: ConnectorFailReason) {
        Log.d(
            TAG,
            "onFailure: " + connectorFailReason.name
        )
    }

    override fun onDisconnected(connectorDisconnectReason: ConnectorDisconnectReason) {
        Log.d(
            TAG,
            "onDisconnected: " + connectorDisconnectReason.name
        )
    }

    override fun onChatMessageReceived(
        participant: Participant,
        chatMessage: ChatMessage
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
        if (videoFrame!!.visibility == View.VISIBLE) {
            // Its visible
            videoFrame!!.visibility = View.INVISIBLE
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                imgVideo!!.setImageDrawable(
                    resources.getDrawable(
                        R.drawable.ic_videocam_off_white_24dp,
                        applicationContext.theme
                    )
                )
            } else {
                imgVideo!!.setImageDrawable(resources.getDrawable(R.drawable.ic_videocam_off_white_24dp))
            }
        } else {
            // Either gone or invisible
            videoFrame!!.visibility = View.VISIBLE
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                imgVideo!!.setImageDrawable(
                    resources.getDrawable(
                        R.drawable.ic_videocam_white_24dp,
                        applicationContext.theme
                    )
                )
            } else {
                imgVideo!!.setImageDrawable(resources.getDrawable(R.drawable.ic_videocam_white_24dp))
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
                imgMic!!.setImageDrawable(
                    resources.getDrawable(
                        R.drawable.ic_mic_off_white_24dp,
                        applicationContext.theme
                    )
                )
            } else {
                imgMic!!.setImageDrawable(resources.getDrawable(R.drawable.ic_mic_off_white_24dp))
            }
        } else {
            audioManager.isMicrophoneMute = false
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                imgMic!!.setImageDrawable(
                    resources.getDrawable(
                        R.drawable.ic_mic_white_24dp,
                        applicationContext.theme
                    )
                )
            } else {
                imgMic!!.setImageDrawable(resources.getDrawable(R.drawable.ic_mic_white_24dp))
            }
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (vc != null) {
            videoFrame!!.removeAllViews()
            videoFrame!!.invalidate()
            vc!!.disconnect()
        }
    }

    override fun onLocalCameraAdded(localCamera: LocalCamera) {}
    override fun onLocalCameraRemoved(localCamera: LocalCamera) {}
    override fun onLocalCameraSelected(localCamera: LocalCamera) {}
    override fun onLocalCameraStateUpdated(
        localCamera: LocalCamera,
        deviceState: DeviceState
    ) {
    }

    companion object {
        private const val TAG = "DoctorListActivity"
        fun getScreenSize(activity: Activity): Double {
            var screenSize = 0.0
            val dm = DisplayMetrics()
            activity.windowManager.defaultDisplay.getMetrics(dm)
            val x = Math.pow(dm.widthPixels / dm.xdpi.toDouble(), 2.0)
            val y = Math.pow(dm.heightPixels / dm.ydpi.toDouble(), 2.0)
            screenSize = Math.sqrt(x + y)
            return screenSize
        }
    }
}