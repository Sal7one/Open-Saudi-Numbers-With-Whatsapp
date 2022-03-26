package com.example.numberswitcher

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class DeepLinkHandlerTest {
    // Correct values
    private lateinit var numberWithoutPlusAndCountryCode: String
    private lateinit var telephoneWithoutPlusAndCountryCode: String
    private lateinit var numberWithoutPlus: String
    private lateinit var telephoneWithoutPlus: String
    private lateinit var numberWithPlusAndCountryCode: String
    private lateinit var telephoneWithPlusAndCountryCode: String

    // Values that needs cleaning
    private lateinit var numWithText1: String
    private lateinit var numWithText2: String
    private lateinit var numWithText3: String
    private lateinit var numWithText4: String
    private lateinit var numWithText5: String
    private lateinit var numWithText6: String

    // Multiple numbers
    private lateinit var multiNums: String

    private lateinit var multiNumsWithText1: String
    private lateinit var multiNumsWithText2: String

    @Before
    fun setUp() {
        // Mobile
        numberWithoutPlusAndCountryCode = "0541234567"
        numberWithoutPlus = "966541234567"
        numberWithPlusAndCountryCode = "+966541234567"

        // Telephone
        telephoneWithoutPlusAndCountryCode = "0125876524"
        telephoneWithoutPlus = "9660125876524"
        telephoneWithPlusAndCountryCode = "+966125876524"

        // Numbers with text
        numWithText1 = "Hello This is a stexct twekfwekisjf \n+966\n540-21-4\n5=1asd"
        numWithText2 = "Hello This is a stexct twekfwekisjf sdfsdfsdfsdf+966-541234567aszf41saf6s-"
        numWithText3 =
            "Hello This is a stexct 966 541234567 twekfwekisjf sdfsdfsdfsdf+966-541234567aszf41saf6s-"
        numWithText4 =
            "Hello This is a stexct 966 0541234567 twekfwekisjf sdfsdfsdfsdf+966-541234567aszf41saf6s-"
        numWithText5 =
            "Hello This is a stexct +966 0541234567 twekfwekisjf sdfsdfsdfsdf+966-541234567aszf41saf6s-"
        numWithText6 =
            "Hello This is a stexct +966 541234567 twekfwekisjf sdfsdfsdfsdf+966-541234567aszf41saf6s-"

        // Multiple numbers
        multiNums = "+966541234567+966541234567+966541234567+966541234567"
        multiNumsWithText1 = "fsdfsdf+966541234567fsdf\nfdsfdsf+966541234567sfsdfd"
        multiNumsWithText2 = "sdfsdf\n0540885465sddsfsdf\n0541234567sadasd"
    }
}