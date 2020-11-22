package org.potados.todo

import android.app.Application
import android.content.Context

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        context = this
    }

    companion object {
        private lateinit var context: Context

        fun getAppContext() = context
    }
}