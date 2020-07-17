package com.smb.copticmatch.ui.home

/**
 * Created by Shijil Kadambath on 03/08/2018
 * for NewAgeSMB
 * Email : shijil@newagesmb.com
 */
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController

import com.smb.copticmatch.R
import com.smb.copticmatch.databinding.FragmentHomeTabHostBinding
import com.smb.copticmatch.databinding.FragmentLoginBinding
import com.smb.copticmatch.ui.BaseFragment
import com.smb.copticmatch.ui.chat.ChatFragment

private const val TAG: String = "TabHostFragment"

/**
 * A simple [Fragment] subclass.
 *
 */
class TabHostFragment : BaseFragment<FragmentHomeTabHostBinding>() {

    companion object {
        val TAB_NUMBER = "tab_number"

        fun newInstance(tabNumber: Int): TabHostFragment {
            val fragment = TabHostFragment()
            val bundle = Bundle()
            bundle.putInt(TAB_NUMBER, tabNumber)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home_tab_host;
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val master = childFragmentManager.findFragmentById(R.id.master_nav_fragment) as NavHostFragment?
        if(master != null){
            val navController = master.navController
            val navInflater = navController.navInflater

            when(arguments?.getInt(TAB_NUMBER)){

                0 ->{
                    val graph = navInflater.inflate(R.navigation.discover)
                    master.navController.setGraph(graph)
                }

                1 ->{
                    val graph = navInflater.inflate(R.navigation.like)
                    master.navController.setGraph(graph)
                }

                2 ->{
                    val graph = navInflater.inflate(R.navigation.chat)
                    val bundle = Bundle()
                    bundle.putInt(ChatFragment.ARG_CHAT_TYPE,ChatFragment.GENERAL_CHAT)
                    master.navController.setGraph(graph,bundle)
                }

                3 ->{
                    val graph = navInflater.inflate(R.navigation.matches)
                    master.navController.setGraph(graph)
                }

                4 ->{
                    val graph = navInflater.inflate(R.navigation.profile)
                    master.navController.setGraph(graph)
                }

            }


        }


    }

    fun navController() = findNavController()
}
