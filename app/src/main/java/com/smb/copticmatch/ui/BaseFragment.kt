package com.smb.copticmatch.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.smb.copticmatch.binding.FragmentDataBindingComponent
import com.smb.copticmatch.common.autoCleared
import com.smb.copticmatch.data.api.Status
import com.smb.copticmatch.di.Injectable
import com.smb.copticmatch.utils.InternetCheck
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject

/**
 * Created by Shijil Kadambath on 03/08/2018
 * for NewAgeSMB
 * Email : shijil@newagesmb.com
 */


/**
 * A generic Fragment   .
 * @param <T> The type of the ViewDataBinding.
*/

abstract class BaseFragment< T : ViewDataBinding> : Fragment() , Injectable {

   // private var mActivity: BaseActivity? = null

    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)

    @Inject
    protected lateinit var mViewModelFactory: ViewModelProvider.Factory

    var mBinding by autoCleared<T>()

    @LayoutRes
    abstract fun getLayoutId(): Int


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, getLayoutId(),
                container, false, dataBindingComponent)

        return mBinding.root
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        /*if (context is BaseActivity) {
            this.mActivity = context
        }*/
    }


    fun <V : ViewModel> getViewModel(clazz: Class<V>): V {
       return  ViewModelProviders.of(this, mViewModelFactory).get(clazz)

    }


    fun isNetworkConnected(it: Status, message: String?): Boolean {
        if (it == Status.ERROR && message != null) {
            try {
                InternetCheck(object : InternetCheck.Consumer {
                    override fun accept(internet: Boolean?) {
                        if (!internet!!) {
                            showSnackBar("No Internet Connection")
                        } else {
                            try {
                                showSnackBar(JSONObject((message.toString())).getString("message"))
                            } catch (e: JSONException) {
                                e.printStackTrace()
                            }
                        }
                    }
                })

            } catch (e: JSONException) {
                showSnackBar(message.toString())
                e.printStackTrace()
            }
            return false
        } else {
            return true
        }
    }
    fun showSnackBar(message: String) {
        Snackbar.make(mBinding.root, message, Snackbar.LENGTH_LONG).show()
    }

}
