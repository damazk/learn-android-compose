package com.example.learnandroidcompose.domain

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import java.math.BigInteger

class FibonacciService: Service() {

    private val fibonacciBinder = FibonacciBinder()

    override fun onBind(intent: Intent): IBinder {
        return fibonacciBinder
    }

    fun resolveFibonacci(n: Int): BigInteger {
        return if (n <= 2) {
            BigInteger.ONE
        } else {
            fib(n)
        }
    }

    private fun fib(n: Int): BigInteger {
        var a = BigInteger.ONE
        var b = BigInteger.ONE
        for (i in 2 until n) {
            val temp = b
            b = a + b
            a = temp
        }
        return a
    }

    inner class FibonacciBinder : Binder() {
        fun getService() = this@FibonacciService
    }
}