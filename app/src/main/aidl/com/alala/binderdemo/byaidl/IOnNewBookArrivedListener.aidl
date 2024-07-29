// IOnNewBookArrivedListener.aidl
package com.alala.binderdemo.byaidl;
import com.alala.binderdemo.byaidl.Book;
// Declare any non-default types here with import statements

interface IOnNewBookArrivedListener {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void onNewBookArrived(in Book book);
}