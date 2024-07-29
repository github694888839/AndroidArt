package com.alala.binderdemo.aidl

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.alala.binderdemo.byaidl.Book
import com.alala.binderdemo.byaidl.IBookManager
import com.alala.binderdemo.R

class BookManagerActivity : AppCompatActivity() {
    private val TAG = "BookManagerActivity"
    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, binder: IBinder?) {
            val iBookManager = IBookManager.Stub.asInterface(binder)
            try {
                val bookList = iBookManager.bookList
                Log.d(TAG, "获取了book的列表：{$bookList}")

                //add book
                val book = Book("Book3", "003")
                iBookManager.addBook(book)
            } catch (e: Exception) {
                TODO("Not yet implemented")
            }
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            TODO("Not yet implemented")
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_book_manager)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val intent = Intent(this, IBookManager::class.java)
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onDestroy() {
        unbindService(serviceConnection)
        super.onDestroy()
    }
}