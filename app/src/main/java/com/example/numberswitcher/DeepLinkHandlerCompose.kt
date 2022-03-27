package com.example.numberswitcher

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.numberswitcher.ui.theme.NumberSwitcherTheme

class DeepLinkHandlerCompose : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val text = intent.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT)

        setContent {
            NumberSwitcherTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(0.1f)
                        .background(Color.Transparent)
                ) {
                    if (text != null) {
                        NumberHandler(text.toString())
                    } else {
                        var numberWithTelSchema =
                            intent.extras?.getString(Intent.EXTRA_TEXT).toString()
                        numberWithTelSchema = numberWithTelSchema.replace("tel:", "")
                        NumberHandler(numberWithTelSchema)
                    }
                }
            }
        }
    }

    @Composable
    private fun NumberHandler(number: String) {
        val numberWithoutText = removeTextFromString(number)

        if (!isASaudiPhoneNumber(numberWithoutText)) {
            Toast.makeText(this, getString(R.string.not_a_saudi_number), Toast.LENGTH_SHORT)
                .show()
            onStop()
        } else {
            // List of Saudi numbers found
            val listOfNumbers = getListOfNumbers(numberWithoutText).map { formatNumber(it) }

            // Multiple numbers found
            if (listOfNumbers.size > 1) {
                ListOfNumbers(
                    listOfNumbers,
                ) { clickedNumber ->
                    switchToApp(clickedNumber)
                }
            } else {
                switchToApp(formatNumber(listOfNumbers[0]))
            }
        }
    }

    private fun switchToApp(number: String) {
        val whatsAppAPI = "https://api.whatsapp.com/send?phone="
        val uri = Uri.parse(whatsAppAPI + number)
        val i = Intent(Intent.ACTION_VIEW, uri)

        // To tell the system we to expect this activity to be finished after intent
        i.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(i)
    }

    private fun getListOfNumbers(numberWithoutText: String): List<String> {
        val regex = Regex("((\\+)?(966([_ \\-])?0?|0)(5[0-9]{8}|1([1-9])[0-9]{7}))")
        val matches = regex.findAll(numberWithoutText)
        // Group value 0 Matches all of the Regex pattern
        val stringOfNumbers = matches.map { it.groupValues[0] }.joinToString()

        // Split the string into List
        return stringOfNumbers.split(",").toList()
    }

    private fun formatNumber(number: String): String {
        var telNumber = number.replace(" ", "")
        when {
            // Edge case of +966 with 05 or 01 --> +966050 or +966013
            telNumber.startsWith("+96605") -> telNumber =
                "966" + telNumber.substring(telNumber.indexOf("05") + 1)
            telNumber.startsWith("+96601") -> telNumber =
                "966" + telNumber.substring(telNumber.indexOf("01") + 1)
            // Edge case of 966 with 05 or 01 --> 966050 or 966013
            telNumber.startsWith("96605") -> telNumber =
                "966" + telNumber.substring(telNumber.indexOf("05") + 1)
            telNumber.startsWith("96601") -> telNumber =
                "966" + telNumber.substring(telNumber.indexOf("01") + 1)
            // Mobile numbers
            telNumber.startsWith("05") -> telNumber =
                "966" + telNumber.substring(telNumber.indexOf("05") + 1)
            // Line/Telephone numbers
            telNumber.startsWith("01") -> telNumber =
                "966" + telNumber.substring(telNumber.indexOf("01") + 1)
            // 00 instead of +
            telNumber.startsWith("00") -> telNumber =
                telNumber.substring(telNumber.indexOf("00") + 2)
            // Doesn't matter just remove +
            telNumber.startsWith("+") -> telNumber = telNumber.substring(telNumber.indexOf("+") + 1)
        }
        return telNumber
    }

    private fun removeTextFromString(numberWithText: String): String {
        return numberWithText.replace(regex = Regex("([^(+)?0-9])"), "")
    }

    private fun isASaudiPhoneNumber(phone: String): Boolean {
        val saudiNumberRegex = "((\\+)?(966([_ \\-])?|0)(5[0-9]{8}|1([1-9])[0-9]{7}))"
        return phone.contains(Regex(saudiNumberRegex))
    }

    override fun onStop() {
        super.onStop()
        finishAndRemoveTask()
    }
}