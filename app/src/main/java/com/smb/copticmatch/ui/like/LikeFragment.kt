package com.smb.copticmatch.ui.like

/**
 * Created by Shijil Kadambath on 03/08/2018
 * for NewAgeSMB
 * Email : shijil@newagesmb.com
 */
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.smb.copticmatch.AppExecutors

import com.smb.copticmatch.R
import com.smb.copticmatch.databinding.FragmentLikeBinding
import com.smb.copticmatch.databinding.FragmentLoginBinding
import com.smb.copticmatch.ui.BaseFragment
import javax.inject.Inject

private const val TAG: String = "LikeFragment"

/**
 * A simple [Fragment] subclass.
 *
 */
class LikeFragment : BaseFragment<FragmentLikeBinding>() {


    @Inject
    lateinit var appExecutors: AppExecutors

    lateinit var mViewModel: LikeViewModel



    override fun getLayoutId(): Int {
        return R.layout.fragment_like;
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel = getViewModel(LikeViewModel::class.java)

    }

    fun navController() = findNavController()
}
