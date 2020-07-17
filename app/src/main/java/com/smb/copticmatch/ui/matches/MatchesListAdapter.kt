package com.smb.copticmatch.ui.matches

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.smb.copticmatch.AppExecutors
import com.smb.copticmatch.R
import com.smb.copticmatch.data.model.User
import com.smb.copticmatch.databinding.ItemChatListBinding
import com.smb.copticmatch.databinding.ItemMatchesListBinding
import com.smb.copticmatch.ui.BaseDataBindListAdapter

class MatchesListAdapter(
        private val dataBindingComponent: DataBindingComponent,
        appExecutors: AppExecutors,
        private val itemClickCallback: ((User) -> Unit)?
) : BaseDataBindListAdapter<User, ItemMatchesListBinding>(
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

    override fun createBinding(parent: ViewGroup): ItemMatchesListBinding {
        val binding = DataBindingUtil.inflate<ItemMatchesListBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_matches_list,
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

    override fun bind(binding: ItemMatchesListBinding, item: User) {
        //binding.user = item
    }
}