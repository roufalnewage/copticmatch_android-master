package com.smb.copticmatch.ui.chat

/**
 * Created by Shijil Kadambath on 03/08/2018
 * for NewAgeSMB
 * Email : shijil@newagesmb.com
 */
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.smb.copticmatch.AppExecutors

import com.smb.copticmatch.R
import com.smb.copticmatch.common.autoCleared
import com.smb.copticmatch.data.model.User
import com.smb.copticmatch.databinding.FragmentChatBinding
import com.smb.copticmatch.databinding.FragmentLoginBinding
import com.smb.copticmatch.ui.BaseFragment
import javax.inject.Inject

private const val TAG: String = "ChatFragment"

/**
 * A simple [Fragment] subclass.
 *
 */
class ChatFragment : BaseFragment<FragmentChatBinding>() {

    companion object {
        val ARG_CHAT_TYPE = "tab_number"
        val GENERAL_CHAT = 1
        val MATCHES_CHAT = 2
    }


    @Inject
    lateinit var appExecutors: AppExecutors
    lateinit var mViewModel: ChatViewModel
    var adapter by autoCleared<ChatListAdapter>()

    override fun getLayoutId(): Int {
        return R.layout.fragment_chat;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = getViewModel(ChatViewModel::class.java)

        mBinding.toolbar.visibility = if (arguments?.getInt(ARG_CHAT_TYPE,GENERAL_CHAT)==GENERAL_CHAT)
            View.VISIBLE else View.GONE

        adapter = ChatListAdapter(dataBindingComponent = dataBindingComponent, appExecutors = appExecutors) {

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
