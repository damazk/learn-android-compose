package com.example.learnandroidcompose

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.example.learnandroidcompose.domain.XResolver
import com.example.learnandroidcompose.presentation.MainScreen
import com.example.learnandroidcompose.ui.theme.LearnAndroidComposeTheme
import org.junit.Rule
import org.junit.Test

class MainScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val xResolver = XResolver()

    @Test
    fun test_x_shows_result() {

        composeTestRule.setContent {
            LearnAndroidComposeTheme {
                MainScreen(modifier = Modifier, resolveX = xResolver::resolve)
            }
        }

        composeTestRule.onNodeWithText("a =").performTextInput("2")
        composeTestRule.onNodeWithText("b =").performTextInput("3")
        composeTestRule.onNodeWithText("Найти x").performClick()
        composeTestRule.onNodeWithText("x = x < - 1.5")
    }
}