package com.mnafis.compose_ui_android_experiment

import android.content.SharedPreferences

class SharedPrefUtil(private val sharedPreferences: SharedPreferences) {

    companion object {
        const val DEFAULT_INT = -1
        const val DEFAULT_VALUE = "defaultValue"
    }

    private enum class SharedPrefKey(val value: String) {
        SHOULD_INJECT_MOCK_DATA("should_inject_mock_data")
    }

    private fun setString(key: SharedPrefKey, value: String) =
        sharedPreferences.edit().putString(key.value, value).apply()
    private fun getString(key: SharedPrefKey, defaultValue: String = DEFAULT_VALUE): String =
        sharedPreferences.getString(key.value, defaultValue)!!
}