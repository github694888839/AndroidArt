// IBinderPool.aidl
package com.alala.binderdemo.binderpool;

// Declare any non-default types here with import statements

interface IBinderPool {
    IBinder queryBinder(int binderCode);
}