package com.smb.copticmatch.utils

import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Singleton

@Singleton
class RequestBodyUtil {
    companion object {
        fun getRequestBodyMap(reqMap: HashMap<String, Any>): RequestBody {
            val reqJson = JSONObject()
            for ((key, value) in reqMap) {
                try {
                    when (value) {
                        is Int -> reqJson.put(key, Integer.parseInt(value.toString()))
                        is Double -> reqJson.put(key, java.lang.Double.parseDouble(value.toString()))
                        is Boolean -> reqJson.put(key, java.lang.Boolean.parseBoolean(value.toString()))
                        is Long -> reqJson.put(key, java.lang.Long.parseLong(value.toString()))
                        is Float -> reqJson.put(key, java.lang.Float.parseFloat(value.toString()).toDouble())
                        is JSONArray -> reqJson.put(key, value)
                        else -> reqJson.putOpt(key, value)
                    }
                } catch (e: JSONException) {

                }

            }
            return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), reqJson.toString())
        }
    }
}