package com.example.learnandroidcompose

import android.widget.Space
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

@Composable
fun MainScreen(
    modifier: Modifier
) {
    var a by rememberSaveable { mutableStateOf("") }
    var b by rememberSaveable { mutableStateOf("") }
    var x by rememberSaveable { mutableStateOf("") }

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
                    value = a,
                    onValueChange = { a = it },
                    prefixText = "a ="
                )
                Spacer(modifier = Modifier.height(15.dp))
                CustomEditText(
                    value = b,
                    onValueChange = { b = it },
                    prefixText = "b ="
                )
            }
            Spacer(modifier = Modifier.width(15.dp))

            FilledTonalButton(
                onClick = {
                    if (a.isNotEmpty() && b.isNotEmpty()) {
                        val aDouble = a.toDouble()
                        val bDouble = a.toDouble()
                        x = resolveX(aDouble, bDouble)
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
                text = "x = $x",
                fontSize = 18.sp
            )
        }
    }
}

private fun resolveX(a: Double, b: Double): String {
    return if (a == 0.0) {
        if (b < 0) {
            "∞"
        } else {
            "Неравенство не имеет решения"
        }
    } else if (b == 0.0 ) {
        if (a < 0.0) {
            "+ бесконечность"
        } else {
//            "-∞"
            "- бесконечность"
        }
    } else {
        val x = -b * a
        x.toString()
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
    MainScreen(modifier = Modifier)
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