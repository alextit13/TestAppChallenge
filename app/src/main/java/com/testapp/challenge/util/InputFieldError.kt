package com.testapp.challenge.util

import androidx.annotation.StringRes
import com.testapp.challenge.R

/**
 * @author aliakseicherniakovich
 */
class InputFieldError private constructor(
    var isError: Boolean,
    @StringRes
    val errorMessageResId: Int = R.string.error_empty_enter_point_amount
) {
    companion object {
        fun error(isError: Boolean = false) = InputFieldError(isError)
    }
}
