package com.example.mvvmbloginformation.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.mvvmbloginformation.application.ApplicationContext.Companion.appContext

class NetworkConnection {
    companion object {
        /**
         * checking internet is available or not
         */
        @RequiresApi(Build.VERSION_CODES.M)
        fun isNetworkConnected(): Boolean {

            val connectivityManager =
                appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val activeNetwork = connectivityManager.activeNetwork

            val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)

            return networkCapabilities != null &&
                    networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        }

        fun isNetworkConnectedKitkat(): Boolean {
            val cm =
                appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return cm.isActiveNetworkMetered
        }
    }

}