package com.example.numberswitcher

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CountrySelector(
) {
    var countryCodeText by remember { mutableStateOf("") }
    var numberText by remember { mutableStateOf("") }

    val maxCountryCodeChars = 5

    Card(modifier = Modifier.fillMaxWidth()) {
        Column {
            Text(text = "Country Picker")
            Spacer(modifier = Modifier.width(30.dp))
            Spacer(modifier = Modifier.height(50.dp))
            Row {
                Text(text = "Country code")
                TextField(
                    value = countryCodeText,
                    onValueChange = {
                        if (it.length <= maxCountryCodeChars)
                            countryCodeText = it
                    },
                    placeholder = { Text("+966") },
                    modifier = Modifier.width(120.dp)
                )
                Spacer(modifier = Modifier.width(20.dp))
                TextField(value = numberText,
                    onValueChange = { numberText = it },
                    placeholder = { Text("550123456") }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CountrySelector_PREVIEW() {
    CountrySelector()
}