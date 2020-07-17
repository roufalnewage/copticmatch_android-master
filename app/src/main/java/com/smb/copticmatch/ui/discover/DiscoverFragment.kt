package com.smb.copticmatch.ui.discover

/**
 * Created by Shijil Kadambath on 03/08/2018
 * for NewAgeSMB
 * Email : shijil@newagesmb.com
 */
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.smb.copticmatch.AppExecutors

import com.smb.copticmatch.R
import com.smb.copticmatch.databinding.FragmentDiscoverBinding
import com.smb.copticmatch.databinding.FragmentLoginBinding
import com.smb.copticmatch.ui.BaseFragment
import javax.inject.Inject

private const val TAG: String = "DiscoverFragment"

/**
 * A simple [Fragment] subclass.
 *
 */
class DiscoverFragment : BaseFragment<FragmentDiscoverBinding>() {


    @Inject
    lateinit var appExecutors: AppExecutors

    lateinit var mViewModel: DiscoverViewModel



    override fun getLayoutId(): Int {
        return R.layout.fragment_discover;
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel = getViewModel(DiscoverViewModel::class.java)

    }

    fun navController() = findNavController()
}
