package com.mnafis.movie_mania.utils

import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.ContextCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class NetworkConnectionManager @Inject constructor(
    @ApplicationContext context: Context
) {
    private val connectivityManager: ConnectivityManager? =
        ContextCompat.getSystemService(context, ConnectivityManager::class.java)

    fun isNetworkAvailable(): Boolean =
        connectivityManager != null && connectivityManager.activeNetwork != null
}