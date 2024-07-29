package com.alala.binderdemo.aidl

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.alala.binderdemo.byaidl.Book
import com.alala.binderdemo.byaidl.IBookManager
import com.alala.binderdemo.byaidl.IOnNewBookArrivedListener
import java.util.concurrent.CopyOnWriteArrayList

class BookManagerService : Service() {
    private val TAG = "BookManagerService"
    private val bookList = CopyOnWriteArrayList<Book>()
    private val listenerList = CopyOnWriteArrayList<IOnNewBookArrivedListener>()

    private val mBinder = object : IBookManager.Stub() {
        override fun getBookList(): MutableList<Book> {
            return bookList
        }

        override fun addBook(book: Book?) {
            book?.let { bookList.add(it) }
        }

        override fun registListener(listener: IOnNewBookArrivedListener?) {
            if (!listenerList.contains(listener)) {
                listenerList.add(listener)
            } else {
                Log.d(TAG, "Listener is already exist.")
            }
            Log.d(TAG, "Listener list size is {${listenerList.size}}.")
        }

        override fun unregistListener(listener: IOnNewBookArrivedListener?) {
            if (listenerList.contains(listener)) {
                listenerList.remove(listener)
            } else {
                Log.d(TAG, "Listener is not found.")
            }
            Log.d(TAG, "Listener list size is {${listenerList.size}}.")
        }

    }

    override fun onCreate() {
        super.onCreate()
        bookList.add(Book("book1", "001"))
        bookList.add(Book("book2", "002"))
    }

    override fun onBind(intent: Intent?): IBinder? {
        return mBinder
    }
}