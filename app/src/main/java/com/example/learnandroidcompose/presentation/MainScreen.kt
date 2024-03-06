package com.example.learnandroidcompose.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun MainScreen(
    modifier: Modifier,
    uiState: MainState,
    onAValueChanged: (String) -> Unit,
    onBValueChanged: (String) -> Unit,
    resolveX: (Double, Double) -> Unit
) {

    val context = LocalContext.current

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(15.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "ax + b < 0",
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(15.dp))

            Column {
                CustomEditText(
                    value = uiState.a,
                    onValueChange = onAValueChanged,
                    prefixText = "a ="
                )
                Spacer(modifier = Modifier.height(15.dp))
                CustomEditText(
                    value = uiState.b,
                    onValueChange = onBValueChanged,
                    prefixText = "b ="
                )
            }
            Spacer(modifier = Modifier.width(15.dp))

            FilledTonalButton(
                onClick = {
                    if (uiState.a.isNotEmpty() && uiState.b.isNotEmpty()) {
                        val aDouble = uiState.a.toDouble()
                        val bDouble = uiState.b.toDouble()
                        resolveX(aDouble, bDouble)
                    } else {
                        Toast.makeText(context, "Пожалуйста, заполните поля 'a =' и 'b ='",
                            Toast.LENGTH_LONG).show()
                    }
                }
            ) {
                Text(text = "Найти x")
            }
            Spacer(modifier = Modifier.width(15.dp))

            Text(
                text = "x = ${uiState.x}",
                fontSize = 18.sp
            )
        }
    }
}

@Composable
fun CustomEditText(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    prefixText: String,
) {
    OutlinedTextField(
        modifier = modifier.width(120.dp),
        value = value,
        onValueChange = onValueChange,
        prefix = { Text(text = prefixText) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true
    )
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen(
        modifier = Modifier,
        uiState = MainState("1", "2", "3"),
        {},
        {},
        { a, b -> }
    )
}

@Preview(showBackground = true)
@Composable
fun CustomEditTextPreview() {
    CustomEditText(
        modifier = Modifier,
        value = "123",
        onValueChange = {},
        prefixText = "a ="
    )
}