package com.smb.copticmatch.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class TabsPagerAdapter(manager: FragmentManager, private val numTabs: Int)
    : FragmentPagerAdapter(manager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment = TabHostFragment.newInstance(position)

    override fun getCount(): Int {
        return numTabs
    }


}