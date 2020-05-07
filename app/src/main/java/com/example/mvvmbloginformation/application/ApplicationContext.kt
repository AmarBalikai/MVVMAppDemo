package com.example.mvvmbloginformation.application

import android.app.Application
import android.content.Context

class ApplicationContext : Application() {
    companion object {
        lateinit var appContext: Context
    }

    /**
     * This method is used to initialize the context
     */
    override fun onCreate() {
        super.onCreate()
        appContext = this
    }
}