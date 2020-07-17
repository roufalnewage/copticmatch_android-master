package com.smb.copticmatch.ui.matches

/**
 * Created by Shijil Kadambath on 03/08/2018
 * for NewAgeSMB
 * Email : shijil@newagesmb.com
 */
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.smb.copticmatch.AppExecutors

import com.smb.copticmatch.R
import com.smb.copticmatch.common.autoCleared
import com.smb.copticmatch.data.model.User
import com.smb.copticmatch.databinding.FragmentLoginBinding
import com.smb.copticmatch.databinding.FragmentMatchesBinding
import com.smb.copticmatch.ui.BaseFragment
import javax.inject.Inject

private const val TAG: String = "MatchesFragment"

/**
 * A simple [Fragment] subclass.
 *
 */
class MatchesFragment : BaseFragment<FragmentMatchesBinding>() {


    @Inject
    lateinit var appExecutors: AppExecutors

    lateinit var mViewModel: MatchesViewModel
    var adapter by autoCleared<MatchesListAdapter>()

    override fun getLayoutId(): Int {
        return R.layout.fragment_matches;
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel = getViewModel(MatchesViewModel::class.java)

        adapter = MatchesListAdapter(dataBindingComponent = dataBindingComponent, appExecutors = appExecutors) {

            /* navController().navigate(
                    LoginFragmentDirections.showRegistration()
            )*/
        }

        mBinding.listChat.adapter = adapter
        val list = ArrayList<User>()
        list .add(User("aaaa",1))
        list .add(User("bbbb",1))
        list .add(User("cccc",1))
        list .add(User("dddd",1))
        list .add(User("dddd",1))
        list .add(User("dddd",1))
        list .add(User("dddd",1))
        list .add(User("dddd",1))
        adapter.submitList(list)
    }

    fun navController() = findNavController()
}
