package com.smb.copticmatch.ui.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.smb.copticmatch.AppExecutors
import com.smb.copticmatch.R
import com.smb.copticmatch.data.model.User
import com.smb.copticmatch.databinding.ItemChatListBinding
import com.smb.copticmatch.ui.BaseDataBindListAdapter

class ChatListAdapter(
        private val dataBindingComponent: DataBindingComponent,
        appExecutors: AppExecutors,
        private val itemClickCallback: ((User) -> Unit)?
) : BaseDataBindListAdapter<User, ItemChatListBinding>(
        appExecutors = appExecutors,
        diffCallback = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
                        && oldItem.name .equals( newItem.name)
            }
        }
) {

    override fun createBinding(parent: ViewGroup): ItemChatListBinding {
        val binding = DataBindingUtil.inflate<ItemChatListBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_chat_list,
                parent,
                false,
                dataBindingComponent
        )
        /*   binding.root.setOnClickListener {
               binding.user?.let {
                   itemClickCallback?.invoke(it)
               }
           }*/
        return binding
    }

    fun  getItemValue(position: Int):User{
        return getItem(position)
    }

    override fun bind(binding: ItemChatListBinding, item: User) {
        //binding.user = item
    }
}