package com.alala.binderdemo.binderpool

import android.app.Service
import android.content.Intent
import android.os.IBinder

class BinderPoolService : Service() {

    private val binderPool = BinderPoolImpl()
    override fun onBind(p0: Intent?): IBinder? {
        return binderPool
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}