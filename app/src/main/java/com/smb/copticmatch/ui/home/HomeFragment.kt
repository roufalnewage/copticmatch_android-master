package com.smb.copticmatch.ui.home

/**
 * Created by Shijil Kadambath on 03/08/2018
 * for NewAgeSMB
 * Email : shijil@newagesmb.com
 */

import android.os.Bundle
import android.view.View
import com.smb.copticmatch.R
import com.smb.copticmatch.databinding.FragmentHomeBinding
import com.smb.copticmatch.databinding.ItemHomeTabBinding
import com.smb.copticmatch.ui.BaseFragment


private const val TAG: String = "HomeFragment"

/**
 * A simple [Fragment] subclass.
 *
 */
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_home;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val adapter = context?.let { it -> TabsPagerAdapter(childFragmentManager, 5) }
        mBinding.viewpager.adapter = adapter
        mBinding.tabLayout.setupWithViewPager(mBinding.viewpager)


        for (i in 0 until mBinding.tabLayout.tabCount) {

            mBinding.tabLayout.getTabAt(i)!!.customView = getTabView(i)
        }
    }

    private fun getTabView(position: Int): View? {
        val binding =  ItemHomeTabBinding.inflate(layoutInflater)

        when (position) {

           0 -> {
               binding.tvTab.setText(R.string.discover)
               binding.ivTab.setImageResource(R.drawable.selector_tab_discover)
            }
             1 -> {
                 binding.tvTab.setText(R.string.likes)
                 binding.ivTab.setImageResource(R.drawable.selector_tab_like)
            }
            2 -> {
                binding.tvTab.setText(R.string.chat)
                binding.ivTab.setImageResource(R.drawable.selector_tab_chat)
            }
            3 -> {
                binding.tvTab.setText(R.string.matches)
                binding.ivTab.setImageResource(R.drawable.selector_tab_match)
            }
            4 -> {
                binding.tvTab.setText(R.string.profile)
                binding.ivTab.setImageResource(R.drawable.selector_tab_profile)
            }
        }


        return binding.root
    }
}
