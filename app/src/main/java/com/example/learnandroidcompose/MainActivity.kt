package com.example.learnandroidcompose

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import com.example.learnandroidcompose.domain.FibonacciService
import com.example.learnandroidcompose.presentation.MainScreen
import com.example.learnandroidcompose.presentation.MainViewModel
import com.example.learnandroidcompose.ui.theme.LearnAndroidComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var fibonacciService: FibonacciService
    private var isBound = false
    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
            val binder = binder as FibonacciService.FibonacciBinder
            fibonacciService = binder.getService()
            isBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
        }
    }

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
                    thread {
                        if (isBound && uiState.isFibonacciInProgress) {
                            val result = fibonacciService.resolveFibonacci(uiState.n.toInt())
                            viewModel.onFibonacciResolved(result.toString())
                        }
                    }

                    MainScreen(
                        modifier = Modifier,
                        uiState,
                        { viewModel.onAValueChanged(it) },
                        { viewModel.onBValueChanged(it) },
                        { viewModel.onNValueChanged(it) },
                        { a, b -> viewModel.resolveX(a, b) },
                        viewModel::onResolveFibonacciClicked
                    )
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Intent(this, FibonacciService::class.java).also {
            bindService(it, serviceConnection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
        unbindService(serviceConnection)
    }
}