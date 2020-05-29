package com.app.womba.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(fm: FragmentManager?) :
    FragmentPagerAdapter(fm!!, BEHAVIOR_SET_USER_VISIBLE_HINT) {

    private val mFragmentList = ArrayList<Fragment>()
    private val mFragmentTitleList = ArrayList<String>()

    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }

    fun addFragment(fragment: Fragment, title: String) {
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }

    fun addFragment(fragment: Fragment) {
        mFragmentList.add(fragment)
    }

    fun getSize(): Int {
        return mFragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return mFragmentTitleList[position]
    }

    fun setPageTitle(position: Int,title:String) {
        mFragmentTitleList[position]=title
    }
}