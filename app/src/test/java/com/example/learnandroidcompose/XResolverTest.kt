package com.example.learnandroidcompose

import com.example.learnandroidcompose.domain.XResolver
import junit.framework.TestCase.assertEquals
import org.junit.Test

class XResolverTest {
    private val xResolver = XResolver()

    @Test
    fun resolveXTest() {
        val a = 2.0
        val b = 1.0
        val x = xResolver.resolveX(a, b)
        assertEquals(x, "x < -0.5")
    }
}