package com.example.numberswitcher

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.numberswitcher.ui.theme.dialog_background_color

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListOfNumbers(
    listOfNumbers: List<String>,
    clickedNumber: (String) -> Unit,
) {
    val activity = (LocalContext.current as? Activity)

    Dialog(onDismissRequest = { activity?.finish() }) {
        Card(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.padding(10.dp, 5.dp, 10.dp, 10.dp),
            elevation = 8.dp
        ) {
            Column(
                Modifier
                    .background(Color.White)
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(vertical = 25.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(id = R.string.multiple_numbers),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentWidth(align = Alignment.CenterHorizontally)
                                    .padding(top = 6.dp),
                                style = TextStyle(
                                    fontSize = 20.sp,
                                    color = Color.Black
                                )
                            )
                        }
                    }
                    items(listOfNumbers) { number ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(35.dp)
                                .background(color = Color.White),
                            elevation = 6.dp,
                            onClick = { clickedNumber(number) }) {
                            Text(
                                // Cleaner number for UI
                                // but the actual value sent will be the same of the list
                                text = "0" + number.substring(number.indexOf("966") + 3),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentWidth(align = Alignment.CenterHorizontally)
                                    .padding(top = 6.dp),
                                style = TextStyle(fontSize = 16.sp)
                            )
                        }
                        Spacer(modifier = Modifier.height(25.dp))
                    }
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                        .clickable(enabled = true, onClick = {
                            activity?.finish()
                        })
                        .background(dialog_background_color),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    TextButton(onClick = {
                        activity?.finish()
                    }) {
                        Text(
                            stringResource(R.string.cancel),
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White,
                            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
                        )
                    }
                }
            }
        }
    }
}