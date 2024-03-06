package com.example.learnandroidcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.learnandroidcompose.presentation.MainScreen
import com.example.learnandroidcompose.presentation.MainViewModel
import com.example.learnandroidcompose.ui.theme.LearnAndroidComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LearnAndroidComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val viewModel: MainViewModel = hiltViewModel()
                    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

                    MainScreen(
                        modifier = Modifier,
                        uiState,
                        { viewModel.onAValueChanged(it) },
                        { viewModel.onBValueChanged(it) },
                        { a, b -> viewModel.resolveX(a, b) }
                    )
                }
            }
        }
    }
}