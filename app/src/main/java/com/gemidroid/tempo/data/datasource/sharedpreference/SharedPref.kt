package com.gemidroid.tempo.data.datasource.sharedpreference

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class SharedPref(context: Context) {

    private val instance: SharedPreferences =
        context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE)

    fun saveString(key: String, value: String?) {
        instance.apply { edit().putString(key, value).apply() }
    }

    fun getString(key: String): String {
        instance.apply { return getString(key, "")!! }
    }

    fun containKey(key: String): Boolean = instance.contains(key)
}
