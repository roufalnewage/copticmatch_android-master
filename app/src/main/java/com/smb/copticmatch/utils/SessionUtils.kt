package com.smb.copticmatch.utils

import android.content.Context
import android.content.SharedPreferences

import com.google.gson.Gson
import com.smb.copticmatch.data.model.User

import javax.inject.Singleton
import kotlin.system.exitProcess

@Singleton
class SessionUtils {




    companion object {

        lateinit var preferences: SharedPreferences

        //private static boolean rememberAppLogin = false;

        fun init(context: Context) {
            preferences = context.getSharedPreferences(AppConstants.PREFERENCE_NAME, Context.MODE_PRIVATE)
        }


        fun saveSession(session: User?, context: Context?) {

            if (session == null) return
            if (preferences ==null){
                preferences = context!!.getSharedPreferences(AppConstants.PREFERENCE_NAME, Context.MODE_PRIVATE)


            }


            val prefsEditor = preferences.edit()
            val gson = Gson()
            val json = gson.toJson(session)
            prefsEditor.putString(AppConstants.PRE_SESSION, json)
            prefsEditor.commit()
        }

        fun saveReferralCode(referralCode: String, context: Context?) {

            if (preferences ==null){
                preferences = context!!.getSharedPreferences(AppConstants.PREFERENCE_NAME, Context.MODE_PRIVATE)
            }
            val prefsEditor = preferences.edit()
            prefsEditor.putString(AppConstants.PRE_REFER_CODE, referralCode)
            prefsEditor.apply()
        }

         fun saveReferBonusCount(noOfBonusEntryReferee: Int, noOfBonusEntryReferrer: Int?) {

            val prefsEditor = preferences.edit()
            prefsEditor.putInt(AppConstants.PRE_REFER_BONUS_COUNT  , noOfBonusEntryReferrer!!)
            prefsEditor.putInt(AppConstants.PRE_REFEREE_BONUS_COUNT, noOfBonusEntryReferee)
            prefsEditor.apply()

        }
        fun getReferralBonusCount():Int{
            return preferences.getInt(AppConstants.PRE_REFER_BONUS_COUNT, 0)
        }

        fun getRefereeBonusCount():Int{
            return preferences.getInt(AppConstants.PRE_REFEREE_BONUS_COUNT, 0)
        }




        fun getReferralCode():String?{
            return preferences.getString(AppConstants.PRE_REFER_CODE, "")
        }

        fun hasSession(): Boolean {

            return if(loginSession != null){
                true
            } else{
                false
            }

        }

        val loginSession: User?
            get() {
                try {
                    val gson = Gson()
                    val json = preferences.getString(AppConstants.PRE_SESSION, "")
                    return gson.fromJson(json, User::class.java)
                } catch (e: Exception) {
                    return null
                }

            }

        fun clearSession() {
            preferences.edit().remove(AppConstants.PRE_AUTH_TOKEN)
                    .remove(AppConstants.PRE_REFRESH_TOKEN)
                    .remove(AppConstants.PRE_REFER_CODE)
                    .remove(AppConstants.PRE_SESSION).apply()
        }
        fun autoLogout() {
            preferences.edit().remove(AppConstants.PRE_AUTH_TOKEN)
                    .remove(AppConstants.PRE_REFRESH_TOKEN)
                    .remove(AppConstants.PRE_SESSION)
                    .apply()
            exitProcess(0)
        }

        fun saveFCMToken(token: String) {
            preferences.edit().putString(AppConstants.PRE_FCM, token).apply()
        }

        val fcmToken: String?
            get() = preferences.getString(AppConstants.PRE_FCM, "")

        fun saveToken(auth: String, refresh: String) {
            preferences.edit().putString(AppConstants.PRE_AUTH_TOKEN, auth)
                    .putString(AppConstants.PRE_REFRESH_TOKEN, refresh).apply()
        }

        val authToken: String?
            get() = preferences.getString(AppConstants.PRE_AUTH_TOKEN, "")

        val refreshToken: String?
            get() = preferences.getString(AppConstants.PRE_REFRESH_TOKEN, "")
    }


}