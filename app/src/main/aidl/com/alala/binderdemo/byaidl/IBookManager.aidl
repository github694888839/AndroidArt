package com.alala.binderdemo.byaidl;

import com.alala.binderdemo.byaidl.Book;
import com.alala.binderdemo.byaidl.IOnNewBookArrivedListener;

interface IBookManager{
    List<Book> getBookList();
    void addBook(in Book book);
    void registListener(in IOnNewBookArrivedListener listener);
    void unregistListener(in IOnNewBookArrivedListener listener);
}