package no.apps.bedrock.utils.extensions

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

val Context.connectivityManager: ConnectivityManager
    get() = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

val Context.isOnline: Boolean
    @SuppressLint("MissingPermission")
    get() = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)?.run {
        return@run hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    } ?: false

