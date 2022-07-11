package com.twobuffers.suricate

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

fun isNetworkAvailable(ctx: Context): Boolean {
    val cm = ctx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager? ?: return false
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val cap = cm.getNetworkCapabilities(cm.activeNetwork) ?: return false
        return cap.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        val networks = cm.allNetworks
        networks.forEach { n ->
            val nInfo = cm.getNetworkInfo(n)
            if (nInfo != null && nInfo.isConnected) return@isNetworkAvailable true
        }
    } else {
        val networks = cm.allNetworkInfo
        networks.forEach { nInfo ->
            if (nInfo != null && nInfo.isConnected) return@isNetworkAvailable true
        }
    }
    return false
}
