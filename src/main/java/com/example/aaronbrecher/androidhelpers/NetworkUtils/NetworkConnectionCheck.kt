package com.example.aaronbrecher.androidhelpers.NetworkUtils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by aaronbrecher on 5/9/18.
 */
class NetworkConnectionCheck(){

    companion object {
        //must check permissions before calling this
        @SuppressLint("MissingPermission")
        fun hasNetworkConnection(context: Context): Boolean{
            val manager: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = manager.activeNetworkInfo
            var isConnected = true
            if(networkInfo == null || !networkInfo.isConnectedOrConnecting) isConnected = false
            return isConnected
        }
    }
}