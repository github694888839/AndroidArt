package com.alala.binderdemo.base

import java.io.Closeable

class MyUtils {
    companion object {
        @JvmStatic
        fun close(closeable: Closeable?) {
            try {
                closeable?.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}