package com.alala.binderdemo.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers

class MyViewModel : ViewModel() {
    fun  test(){
        Dispatchers.IO
    }
}