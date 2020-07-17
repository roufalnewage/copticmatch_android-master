/**
 * Created by Shijil Kadambath on 03/08/2018
 * for NewAgeSMB
 * Email : shijil@newagesmb.com
 */

package com.smb.copticmatch.utils


import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.smb.copticmatch.R
import java.io.IOException
import java.nio.charset.Charset
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern


object CommonUtils {

    val timestamp: String
        get() = SimpleDateFormat(AppConstants.TIMESTAMP_FORMAT, Locale.US).format(Date())


    @SuppressLint("all")
    fun getDeviceId(context: Context): String {
        return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
    }

    fun isEmailValid(editable: AppCompatEditText, context: Context, txtValidation: TextView): Boolean {
        val email = editable.text.toString()
        var isEmailValid = Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches()
        if (!isEmailValid) {
            isEmailValid = Pattern.compile(
                    "(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
                    .matcher(email).matches()
            var ip = ""
            ip = email.substring(ip.indexOf("@") + 1)
            if (isEmailValid&&!ip.startsWith("[")&&!ip.endsWith("]")) {
                isEmailValid=Pattern.compile(
                        "((25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9])\\.(25[0-5]|2[0-4]"
                                + "[0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1]"
                                + "[0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}"
                                + "|[1-9][0-9]|[0-9]))")
                        .matcher(email).matches()
            }
        }
        if (isEmailValid && email.endsWith(".web")) isEmailValid = false


        if (!isEmailValid) {
            editable.background = context.getDrawable(R.drawable.shape_text_input_red)
            editable.setHint("Email")
        }
        editable.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null && s.toString().length > 0) {
                    editable.background = context.getDrawable(R.drawable.shape_text_input)
                    txtValidation.text = ""
                }
            }

        })
        return isEmailValid
    }

    fun isPhoneValid( context: Context,editable: AppCompatEditText, txtValidation: TextView): Boolean {
        var isPhoneValid=Patterns.PHONE.matcher(editable.text.toString()).matches()
        if (!isPhoneValid) {
            editable.background = context.getDrawable(R.drawable.shape_text_input_red)
            editable.setHint("Phone")
        }else{
            isPhoneValid=if(editable.text.toString().length>=10)true else false
        }
        editable.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null && s.toString().length > 0) {
                    editable.background = context.getDrawable(R.drawable.shape_text_input)
                    txtValidation.text = ""
                }
            }

        })
        return isPhoneValid
    }

    fun isPhoneValidForWhiteBack( context: Context,editable: AppCompatEditText, txtValidation: TextView): Boolean {
        var isPhoneValid=Patterns.PHONE.matcher(editable.text.toString()).matches()
        if (!isPhoneValid) {
            editable.background = context.getDrawable(R.drawable.shape_text_input_red)
            editable.setHint("Phone")
        }else{
            isPhoneValid=if(editable.text.toString().length>=10)true else false
        }
        editable.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null && s.toString().length > 0) {
                    editable.background = context.getDrawable(R.drawable.shape_text_profile)
                    txtValidation.text = ""
                }
            }

        })
        return isPhoneValid
    }


    fun isNameValid( context: Context,editable: AppCompatEditText,message: String,  txtValidation: TextView): Boolean {
        val ps = Pattern.compile("^[a-zA-Z ]+$")
        val ms = ps.matcher(editable.text.toString())
        var isNameValid=ms.matches()
        if (!isNameValid) {
            editable.background = context.getDrawable(R.drawable.shape_text_input_red)
            editable.setHint(message)
        }
        editable.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null && s.toString().length > 0) {
                    editable.background = context.getDrawable(R.drawable.shape_text_input)
                    txtValidation.text = ""
                }
            }

        })
        return isNameValid
    }

    @Throws(IOException::class)
    fun loadJSONFromAsset(context: Context, jsonFileName: String): String {
        val manager = context.assets
        val `is` = manager.open(jsonFileName)

        val size = `is`.available()
        val buffer = ByteArray(size)
        `is`.read(buffer)
        `is`.close()

        return String(buffer, "UTF-8" as Charset)
    }

    fun isValidPassword(context: Context, editable: AppCompatEditText, message: String, txtValidation: TextView): Boolean {
        val pattern: Pattern
        val matcher: Matcher
        val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{6,}$"
        pattern = Pattern.compile(PASSWORD_PATTERN)
        matcher = pattern.matcher(editable.text!!.toString())
        editable.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null && s.toString().length > 0) {
                    editable.background = context.getDrawable(R.drawable.shape_text_input)
                    txtValidation.text = ""
                }
            }

        })
        if (!matcher.matches() && TextUtils.isEmpty(editable.text!!.toString())) {
            editable.background = context.getDrawable(R.drawable.shape_text_input_red)
            editable.setHint(message)
            editable.requestFocus()
            return false
        }


        return matcher.matches()

    }

    fun isValidPasswordForWhiteBack(context: Context, editable: AppCompatEditText, message: String, txtValidation: TextView): Boolean {
        val pattern: Pattern
        val matcher: Matcher
        val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{6,}$"
        pattern = Pattern.compile(PASSWORD_PATTERN)
        matcher = pattern.matcher(editable.text!!.toString())
        editable.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null && s.toString().length > 0) {
                    editable.background = context.getDrawable(R.drawable.shape_text_profile)
                    txtValidation.text = ""
                }
            }

        })
        if (!matcher.matches() && TextUtils.isEmpty(editable.text!!.toString())) {
            editable.background = context.getDrawable(R.drawable.shape_text_input_red)
            editable.setHint(message)
            editable.requestFocus()
            return false
        }


        return matcher.matches()

    }

    fun isEmptyField(context: Context, editable: AppCompatEditText, message: String, txtValidation: TextView): Boolean {
        editable.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null && s.toString().length > 0) {
                    editable.background = context.getDrawable(R.drawable.shape_text_input)
                    txtValidation.text = ""
                }
            }

        })

        if (TextUtils.isEmpty(editable.text!!.toString())) {
            editable.background = context.getDrawable(R.drawable.shape_text_input_red)
            editable.setHint(message)
            editable.requestFocus()
            return true
        }

        return false
    }



    @SuppressLint("SetTextI18n")
    fun isNameValidWithMessage(context: Context, editable: AppCompatEditText, message: String, txtValidation: TextView): Boolean {
        val ps = Pattern.compile("^[a-zA-Z ]+$")
        val ms = ps.matcher(editable.text.toString())
        var isNameValid=ms.matches()
        editable.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null && s.toString().length > 0) {
                    editable.background = context.getDrawable(R.drawable.shape_text_input)
                    txtValidation.text = message
                }
            }

        })

        if (!isNameValid) {
            editable.background = context.getDrawable(R.drawable.shape_text_input_red)
            txtValidation.text ="Enter a valid $message"
            editable.requestFocus()

            return true
        }

        return false
    }

    fun showSnackbar(context: Context?, parentLayout: View, message: String) {
        Snackbar.make(parentLayout, message, Snackbar.LENGTH_LONG)
                .setActionTextColor(ContextCompat.getColor(context!!, R.color.black))
                .show()
    }

    fun hideSoftKeyboard(view: View, context: Context?) {
        val imm = context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

    fun hideKeyboard(view: View, context: Context?) {
        val imm = context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }



}// This utility class is not publicly instantiable
