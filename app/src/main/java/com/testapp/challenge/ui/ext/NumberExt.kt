package com.testapp.challenge.ui.ext

import androidx.core.text.isDigitsOnly
import java.lang.Exception

/**
 * @author aliakseicherniakovich
 */
fun String.isInt(): Boolean {
    if (!isDigitsOnly()) return false
    return try {
        toInt()
        true
    } catch (e: Exception) {
        false
    }
}
