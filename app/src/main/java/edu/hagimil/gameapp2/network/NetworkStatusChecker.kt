package edu.hagimil.gameapp2.network

import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class NetworkStatusChecker(
    private val connectivityManager: ConnectivityManager
) {
    fun hasInternet(): Boolean {

        //check if connected
        val network = connectivityManager.activeNetwork ?: return false
        //check network capabilities
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        //WIFI OR VPN OR CELLULAR
        return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN)
    }
}