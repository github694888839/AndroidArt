package com.alala.binderdemo.bymessenger

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.alala.binderdemo.R
import com.alala.binderdemo.base.MyConstants

class MessengerActivity : AppCompatActivity() {
    var service: Messenger? = null
    private val TAG = "messenger"
    private val connection = object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            service = Messenger(p1)
            val msg = Message.obtain(null, MyConstants.MSG_FROM_CLIENT)
            val data = Bundle().apply {
                putString("msg", "this is client.")
            }
            msg.data = data
            msg.replyTo = mGetReplyMessenger
            try {
                service?.send(msg)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            TODO("Not yet implemented")
        }

    }

    private inner class MessengerHandler : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                MyConstants.MSG_FROM_SERVICE -> {
                    Log.i(TAG, "Receive from service: ${msg.data.getString("replay")}")
                }

                else -> {
                    super.handleMessage(msg)
                }
            }

        }

    }

    private val mGetReplyMessenger = Messenger(MessengerHandler())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_messenger)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val intent = Intent(this, MessengerService::class.java)
        bindService(intent, connection, Context.BIND_AUTO_CREATE)

    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(connection)
    }
}