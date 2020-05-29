package com.app.womba.ui.intro

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.app.fragments.IntroFragment
import com.app.fragments.IntroSecondFragment
import com.app.womba.BR
import com.app.womba.R
import com.app.womba.adapter.IntroPagerAdapter
import com.app.womba.base.BaseActivity
import com.app.womba.databinding.ActivityIntroBinding
import com.app.womba.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_intro.*

class IntroActivity : BaseActivity<ActivityIntroBinding, IntroVM>() {
    var mPosition: Int = 0

    override val viewModel: IntroVM
        get() = ViewModelProviders.of(this).get(IntroVM::class.java)
    override val layoutId: Int = R.layout.activity_intro
    override val bindingVariable: Int = BR.viewModel
    override val context: Context = this

    override fun onCreate() {
        setUpViewPager()
    }

    override fun initListeners() {
        imgStart.setOnClickListener {
            if (mPosition != 0) {
                viewPager.currentItem = mPosition - 1
            }
        }

        imgFinish.setOnClickListener {
            if (mPosition == 3) {
                val intentObj = Intent(this, LoginActivity::class.java)
                intentObj.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intentObj)
                this.finish()
            } else {
                viewPager.currentItem = mPosition + 1
            }
        }
    }

    var adapter: IntroPagerAdapter? = null


    private fun setUpViewPager() {

        adapter = IntroPagerAdapter(this.supportFragmentManager)
        adapter!!.addFragment(
            IntroFragment.newInstance(
                "",
                getString(R.string.intro_message_first),
                resources.getDrawable(R.mipmap.intro_first)
            )
        )
        adapter!!.addFragment(
            IntroSecondFragment.newInstance(
                getString(R.string.knowing_better),
                getString(R.string.intro_message_second),
                resources.getDrawable(R.mipmap.intro_second)
            )
        )
        adapter!!.addFragment(
            IntroThirdFragment.newInstance(
                getString(R.string.title_third),
                getString(R.string.intro_message_third),
                resources.getDrawable(R.mipmap.intro_third)
            )
        )
        adapter!!.addFragment(
            IntroFourthFragment.newInstance(
                "",
                getString(R.string.intro_fourth),
                resources.getDrawable(R.drawable.ic_intro_fourth)
            )
        )

        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                mPosition = position
                imgDotFirst.setImageDrawable(resources.getDrawable(R.drawable.ic_dot_white))
                imgDotSecond.setImageDrawable(resources.getDrawable(R.drawable.ic_dot_white))
                imgDotThird.setImageDrawable(resources.getDrawable(R.drawable.ic_dot_white))
                imgDotFourth.setImageDrawable(resources.getDrawable(R.drawable.ic_dot_white))
                imgFinish.setImageDrawable(resources.getDrawable(R.drawable.ic_intro_next))
                imgStart.setImageDrawable(resources.getDrawable(R.drawable.ic_intro_back))

                when (position) {
                    0 -> {
                        imgStart.setImageDrawable(resources.getDrawable(R.drawable.ic_intro_back_disabled))
                        imgDotFirst.setImageDrawable(resources.getDrawable(R.drawable.ic_dot_color))
                    }
                    1 -> {
                        imgDotSecond.setImageDrawable(resources.getDrawable(R.drawable.ic_dot_color))
                    }
                    2 -> {
                        imgDotThird.setImageDrawable(resources.getDrawable(R.drawable.ic_dot_color))
                    }
                    else -> {
                        imgDotFourth.setImageDrawable(resources.getDrawable(R.drawable.ic_dot_color))
                        imgFinish.setImageDrawable(resources.getDrawable(R.drawable.ic_check))
                    }
                }
            }
        })

    }

}
