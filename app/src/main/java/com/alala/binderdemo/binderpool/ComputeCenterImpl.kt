package com.alala.binderdemo.binderpool

class ComputeCenterImpl : ICompute.Stub() {
    override fun compute(a: Int, b: Int): Int {
        return a + b
    }
}