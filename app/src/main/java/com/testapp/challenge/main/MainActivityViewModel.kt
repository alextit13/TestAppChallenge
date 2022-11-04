package com.testapp.challenge.main

import androidx.lifecycle.ViewModel
import com.testapp.challenge.util.InputFieldError
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * @author aliakseicherniakovich
 */
class MainActivityViewModel : ViewModel() {

    private val _errorFlow = MutableStateFlow(InputFieldError.error())
    val errorFlow = _errorFlow.asStateFlow()

    fun onClickRun(enterText: String) {
        if (enterText.isBlank()) {
            _errorFlow.value = InputFieldError.error(true)
            return
        } else {
            _errorFlow.value = InputFieldError.error()
        }

    }
}
