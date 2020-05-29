package com.app.womba.utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation


fun enterReveal(myView: View) {

    // get the center for the clipping circle
    val cx = myView.measuredWidth / 2
    val cy = myView.measuredHeight / 2

    // get the final radius for the clipping circle
    val finalRadius = Math.max(myView.width, myView.height) / 2

    // create the animator for this view (the start radius is zero)
    val anim =
        ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0f, finalRadius.toFloat())

    // make the view visible and start the animation
    myView.visibility = View.VISIBLE
//        anim.duration=150
    anim.start()
}

fun exitReveal(myView: View) {

    // get the center for the clipping circle
    val cx = myView.measuredWidth / 2
    val cy = myView.measuredHeight / 2

    // get the initial radius for the clipping circle
    val initialRadius = myView.width / 2

    // create the animation (the final radius is zero)
    val anim =
        ViewAnimationUtils.createCircularReveal(myView, cx, cy, initialRadius.toFloat(), 0f)

    // make the view invisible when the animation is done
    anim.addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator) {
            super.onAnimationEnd(animation)
            myView.visibility = View.INVISIBLE
        }
    })

    // start the animation
//        anim.duration=150
    anim.start()
}


fun expand(v: View) {
    val matchParentMeasureSpec =
        View.MeasureSpec.makeMeasureSpec((v.parent as View).width, View.MeasureSpec.EXACTLY)
    val wrapContentMeasureSpec =
        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
    v.measure(matchParentMeasureSpec, wrapContentMeasureSpec)
    val targetHeight = v.measuredHeight

    // Older versions of android (pre API 21) cancel animations for views with a height of 0.
    v.layoutParams.height = 1
    v.visibility = View.VISIBLE
    val a = object : Animation() {
        override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
            v.layoutParams.height = if (interpolatedTime == 1f)
                ViewGroup.LayoutParams.WRAP_CONTENT
            else
                (targetHeight * interpolatedTime).toInt()
            v.requestLayout()
        }

        override fun willChangeBounds(): Boolean {
            return true
        }
    }

    // Expansion speed of 1dp/ms
    a.duration = (targetHeight / v.context.resources.displayMetrics.density).toInt().toLong()
    v.startAnimation(a)
}

fun collapse(v: View) {
    val initialHeight = v.measuredHeight

    val a = object : Animation() {
        override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
            if (interpolatedTime == 1f) {
                v.visibility = View.GONE
            } else {
                v.layoutParams.height =
                    initialHeight - (initialHeight * interpolatedTime).toInt()
                v.requestLayout()
            }
        }

        override fun willChangeBounds(): Boolean {
            return true
        }
    }

    // Collapse speed of 1dp/ms
    a.duration = (initialHeight / v.context.resources.displayMetrics.density).toInt().toLong()
    v.startAnimation(a)
}


