package com.example.safebodatest.core.network_utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

/**
 * Class to handle network functionality.
 * */
object NetworkUtils {
    /**
     * Checks internet connection status.
     * */
    fun hasInternetConnection(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as (ConnectivityManager)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork
            val activeNetwork = connectivityManager.getNetworkCapabilities(network)
            return when {
                activeNetwork?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ?: false -> true
                activeNetwork?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI_AWARE)
                    ?: false -> true
                activeNetwork?.hasTransport(NetworkCapabilities.TRANSPORT_VPN) ?: false -> true
                activeNetwork?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ?: false -> true
                else -> false
            }
        } else {
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }

}