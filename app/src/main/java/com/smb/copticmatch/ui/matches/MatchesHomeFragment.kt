package com.smb.copticmatch.ui.matches

/**
 * Created by Shijil Kadambath on 03/08/2018
 * for NewAgeSMB
 * Email : shijil@newagesmb.com
 */
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

import com.smb.copticmatch.R
import com.smb.copticmatch.databinding.FragmentLoginBinding
import com.smb.copticmatch.databinding.FragmentMatchesBinding
import com.smb.copticmatch.databinding.FragmentMatchesHomeBinding
import com.smb.copticmatch.ui.BaseFragment

private const val TAG: String = "MatchesFragment"

/**
 * A simple [Fragment] subclass.
 *
 */
class MatchesHomeFragment : BaseFragment<FragmentMatchesHomeBinding>() {



    override fun getLayoutId(): Int {
        return R.layout.fragment_matches_home;
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val adapter = context?.let { it -> TabsPagerAdapter(childFragmentManager, 2) }
        mBinding.viewpager.adapter = adapter
        mBinding.tabLayout.setupWithViewPager(mBinding.viewpager)


        for (i in 0 until mBinding.tabLayout.tabCount) {

            when (i) {

                0 -> {
                    mBinding.tabLayout.getTabAt(i)!!.setText(R.string.matches)
                }

                1 -> {
                    mBinding.tabLayout.getTabAt(i)!!.setText(R.string.chat)
                }
            }
        }

    }


    class TabsPagerAdapter(manager: FragmentManager, private val numTabs: Int)
        : FragmentPagerAdapter(manager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        override fun getItem(position: Int): Fragment = MatchesHostFragment.newInstance(position)

        override fun getCount(): Int {
            return numTabs
        }


    }
}
