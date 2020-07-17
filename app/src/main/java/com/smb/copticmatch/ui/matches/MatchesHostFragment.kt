package com.smb.copticmatch.ui.matches

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
import com.smb.copticmatch.databinding.FragmentMatchesBinding
import com.smb.copticmatch.ui.BaseFragment
import com.smb.copticmatch.ui.chat.ChatFragment
import com.smb.copticmatch.ui.home.TabHostFragment

private const val TAG: String = "MatchesFragment"

/**
 * A simple [Fragment] subclass.
 *
 */
class MatchesHostFragment : BaseFragment<FragmentHomeTabHostBinding>() {


    companion object {
        val TAB_NUMBER = "tab_number"

        fun newInstance(tabNumber: Int): MatchesHostFragment {
            val fragment = MatchesHostFragment()
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
            val graph = navInflater.inflate(R.navigation.matches)

            when(arguments?.getInt(TabHostFragment.TAB_NUMBER)){

                0 ->{
                    graph.startDestination = R.id.matches_fragment
                }

                1 ->{
                    graph.startDestination = R.id.chat_fragment
                }
            }
            val bundle = Bundle()
            bundle.putInt(ChatFragment.ARG_CHAT_TYPE, ChatFragment.MATCHES_CHAT)
            master.navController.setGraph(graph, bundle)
        }
    }

    fun navController() = findNavController()
}
