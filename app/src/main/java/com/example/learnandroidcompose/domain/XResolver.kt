package com.example.learnandroidcompose.domain

import java.math.RoundingMode

class XResolver: Resolver {

    override fun resolve(a: Double, b: Double): String {
        return if (a == 0.0) {
            // В случае a = 0 неравенство принимает вид b < 0,
            // что означает, что неравенство выполняется при любом x,
            // поэтому можно просто вывести сообщение об этом
            if (b < 0) {
                "∞"
            } else {
                "Неравенство не имеет\nрешения"
            }
        } else {
            val x = -(b / a)
            val formattedX = x.toBigDecimal().setScale(1, RoundingMode.DOWN).toDouble()
            if (a > 0) {
                "x < $formattedX"
            } else {
                "x > $formattedX"
            }
        }
    }
}