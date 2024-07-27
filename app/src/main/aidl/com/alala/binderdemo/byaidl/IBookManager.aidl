package com.alala.binderdemo;

import com.alala.binderdemo.Book;

interface IBookManager{
    List<Book> getBookList();
    void addBook(in Book book);
}