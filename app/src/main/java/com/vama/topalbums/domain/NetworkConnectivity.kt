package com.vama.topalbums.domain

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.NetworkCapabilities.*

class NetworkConnectivity(
    private val context: Context
) {

    fun isConnectedToInternet(): Boolean {
        val connectivityManager =
            context.getSystemService(CONNECTIVITY_SERVICE) as? ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)

            if (capabilities != null) {
                return when {
                    capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
                    capabilities.hasTransport(TRANSPORT_WIFI) -> true
                    capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
                    else -> false
                }
            }
        }
        return false
    }
}
