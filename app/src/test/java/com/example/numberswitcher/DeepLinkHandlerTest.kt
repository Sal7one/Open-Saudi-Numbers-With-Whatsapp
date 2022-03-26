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

}