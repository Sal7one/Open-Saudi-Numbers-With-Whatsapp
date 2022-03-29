package com.example.numberswitcher

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CountrySelector(
) {
    Card(modifier = Modifier.size(500.dp)) {
        Column {
            Text(text = "Country Picker")
            Spacer(modifier = Modifier.height(50.dp))
            Row{
            Text(text = "Country code")
                TextField(value = "", onValueChange = {}, placeholder = { Text("+966") }  )
                Spacer(modifier = Modifier.width(30.dp))
                TextField(value = "", onValueChange = {},placeholder = { Text("550123456") }  )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CountrySelector_PREVIEW(){
    CountrySelector()
}