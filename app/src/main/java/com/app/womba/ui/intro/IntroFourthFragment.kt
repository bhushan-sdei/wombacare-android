package com.app.womba.ui.intro

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.womba.R
import kotlinx.android.synthetic.main.intro_fragment.*

class IntroFourthFragment  : Fragment() {

    companion object {
        var mMessage: String = ""
        var mTitle: String = ""
        lateinit var mImage: Drawable

        fun newInstance(title: String, message: String, image: Drawable): IntroFourthFragment {
            mMessage = message
            mTitle = title
            mImage = image
            return  IntroFourthFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.intro_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        txtTitle.text = mTitle
        txtMessage.text = mMessage
        img.setImageDrawable(mImage)
    }

}
