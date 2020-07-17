package com.smb.copticmatch.ui.profile

/**
 * Created by Shijil Kadambath on 03/08/2018
 * for NewAgeSMB
 * Email : shijil@newagesmb.com
 */
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.smb.copticmatch.AppExecutors

import com.smb.copticmatch.R
import com.smb.copticmatch.databinding.FragmentLoginBinding
import com.smb.copticmatch.databinding.FragmentProfileBinding
import com.smb.copticmatch.ui.BaseFragment
import javax.inject.Inject

private const val TAG: String = "ProfileFragment"

/**
 * A simple [Fragment] subclass.
 *
 */
class ProfileFragment : BaseFragment<FragmentProfileBinding>() {


    @Inject
    lateinit var appExecutors: AppExecutors

    lateinit var mViewModel: ProfileViewModel



    override fun getLayoutId(): Int {
        return R.layout.fragment_profile;
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel = getViewModel(ProfileViewModel::class.java)

    }

    fun navController() = findNavController()
}
