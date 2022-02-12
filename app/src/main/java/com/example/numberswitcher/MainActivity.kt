package com.example.numberswitcher

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

    //private lateinit var data: Uri
    //private var action: String? = null

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val text = intent
            .getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT)

        numberHandler(text)
        Log.d("DARAGOT", text.toString())
     //   Log.d("DataGOT", action.toString())
    }

    private fun numberHandler(phoneNumber: CharSequence?) {

        var phone = phoneNumber.toString()
        phone = phone.replace(" ", "")

        if(phone.startsWith("05"))
            phone = "966" + phone.substring(phone.indexOf("05")+1)
        else if(phone.startsWith("+"))
            phone = phone.substring(phone.indexOf("+")+1)

        switchToApp(phone)
    }

    private fun switchToApp(cleanPhone: String) {
        val whatsAppURI = "https://api.whatsapp.com/send?phone="
        val uri = Uri.parse(whatsAppURI + cleanPhone)
        val i = Intent(Intent.ACTION_VIEW, uri)
        startActivity(i)
    }
}