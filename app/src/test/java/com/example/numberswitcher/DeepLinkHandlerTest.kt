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
        numWithText1 = "Hello This is a stexct twekfwekisjf \n+966\n540-214-4\n5=1asd"
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

    @Test
    fun `Take phone number and format it to whatsapp api`() {
        // ------------------Correct numbers------------------------
        // Mobile phones
        assertTrue(isASaudiPhoneNumber(formatNumber(numberWithoutPlusAndCountryCode)))
        assertTrue(isASaudiPhoneNumber(formatNumber(numberWithoutPlus)))
        assertTrue(isASaudiPhoneNumber(formatNumber(numberWithPlusAndCountryCode)))
        // Telephone Lines
        assertTrue(isASaudiPhoneNumber(formatNumber(telephoneWithoutPlusAndCountryCode)))
        assertTrue(isASaudiPhoneNumber(formatNumber(telephoneWithoutPlus)))
        assertTrue(isASaudiPhoneNumber(formatNumber(telephoneWithPlusAndCountryCode)))

        // Should all be 12 digits with 9665xxxxxxxx or 9661xxxxxxxx format
        // This will be sent to whatsapp api
        assertEquals(12, formatNumber(numberWithoutPlusAndCountryCode).length)
        assertEquals(12, formatNumber(numberWithoutPlus).length)
        assertEquals(12, formatNumber(numberWithPlusAndCountryCode).length)
        assertEquals(12, formatNumber(telephoneWithoutPlusAndCountryCode).length)
        assertEquals(12, formatNumber(telephoneWithoutPlus).length)
        assertEquals(formatNumber(telephoneWithPlusAndCountryCode).length, 12)
        // -----------end----Correct numbers-------end-------------

        // ------------------Numbers with Text------------------------
        numWithText1 = removeTextFromString(numWithText1)
        numWithText2 = removeTextFromString(numWithText2)
        numWithText3 = removeTextFromString(numWithText3)
        numWithText4 = removeTextFromString(numWithText4)
        numWithText5 = removeTextFromString(numWithText5)
        numWithText6 = removeTextFromString(numWithText6)

        numWithText1 = getListOfNumbers(numWithText1)[0]
        numWithText2 = getListOfNumbers(numWithText2)[0]
        numWithText3 = getListOfNumbers(numWithText3)[0]
        numWithText4 = getListOfNumbers(numWithText4)[0]
        numWithText5 = getListOfNumbers(numWithText5)[0]
        numWithText6 = getListOfNumbers(numWithText6)[0]


        // Should all be 12 digits with 9665xxxxxxxx or 9661xxxxxxxx format
        // This will be sent to whatsapp api
        assertEquals(12, formatNumber(numWithText1).length)
        assertEquals(12, formatNumber(numWithText2).length)
        assertEquals(12, formatNumber(numWithText3).length)
        assertEquals(12, formatNumber(numWithText4).length)
        assertEquals(12, formatNumber(numWithText5).length)
        assertEquals(12, formatNumber(numWithText6).length)
        //-----------end----Numbers with Text-------end-------------

        // --------- Multiple numbers with text -------------
        assertEquals(4, getListOfNumbers(multiNums).size)
        print(getListOfNumbers(multiNums).map { formatNumber(it) }.toString())
        assertEquals(2, getListOfNumbers(multiNumsWithText1).size)
        assertEquals(2, getListOfNumbers(multiNumsWithText2).size)
        //------end --- Multiple numbers with text ------end-------

        // No saudi numbers found
        assertFalse(isASaudiPhoneNumber("+965512345678"))
        assertFalse(isASaudiPhoneNumber("k...."))
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
}