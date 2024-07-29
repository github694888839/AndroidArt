package com.alala.binderdemo.binderpool

import android.os.Bundle
import android.os.RemoteException
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.alala.binderdemo.R


class BinderPoolActivity : AppCompatActivity() {
    val TAG = "BinderPoolActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_binder_pool)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun doWork() {
        val binderPool: BinderPool = BinderPool.getInstance(this@BinderPoolActivity)
        val securityBinder = binderPool
            .queryBinder(BinderPool.BINDER_SECURITY_CENTER)
        val mSecurityCenter = ISecurityCenter.Stub.asInterface(securityBinder)
        Log.d(TAG, "visit ISecurityCenter")
        val msg = "helloworld-安卓"
        println("content:$msg")
        try {
            val password: String = mSecurityCenter.encrypt(msg)
            println("encrypt:$password")
            System.out.println("decrypt:" + mSecurityCenter.decrypt(password))
        } catch (e: RemoteException) {
            e.printStackTrace()
        }

        Log.d(TAG, "visit ICompute")
        val computeBinder = binderPool
            .queryBinder(BinderPool.BINDER_COMPUTE)
        val mCompute = ICompute.Stub.asInterface(computeBinder)
        try {
            System.out.println("3+5=" + mCompute.compute(3, 5))
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
    }
}