package com.alala.binderdemo.binderpool

import android.os.IBinder
import com.alala.binderdemo.base.MyConstants

class BinderPoolImpl : IBinderPool.Stub() {
    override fun queryBinder(binderCode: Int): IBinder? {
        return when (binderCode) {
            BinderPool.BINDER_SECURITY_CENTER ->
                SecurityCenterImpl()

            BinderPool.BINDER_COMPUTE ->
                ComputeCenterImpl()

            else ->
                null
        }
    }
}