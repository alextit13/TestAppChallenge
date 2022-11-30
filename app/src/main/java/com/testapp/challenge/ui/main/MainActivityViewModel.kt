package com.testapp.challenge.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.testapp.challenge.model.network.response.PointCallResponse
import com.testapp.challenge.model.repository.Repository
import com.testapp.challenge.ui.ext.isInt
import com.testapp.challenge.util.InputFieldError
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author aliakseicherniakovich
 */
class MainActivityViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _errorEnterAmountFlow = MutableStateFlow(InputFieldError.error())
    val errorEnterAmountFlow = _errorEnterAmountFlow.asStateFlow()

    private val _stateButtonFlow = MutableStateFlow(true)
    val stateButtonFlow = _stateButtonFlow.asStateFlow()

    private val _errorResponseFlow = Channel<String>(Channel.BUFFERED)
    val errorResponseFlow = _errorResponseFlow.receiveAsFlow()

    private val _openNextScreenEvent = Channel<Int>(Channel.BUFFERED)
    val openNextScreenFlow = _openNextScreenEvent.receiveAsFlow()

    fun onClickRun(enterText: String) {
        if (enterText.isBlank() || !enterText.isInt()) {
            _errorEnterAmountFlow.value = InputFieldError.error(true)
            return
        } else {
            _errorEnterAmountFlow.value = InputFieldError.error()
        }
        _stateButtonFlow.value = false
        val count = enterText.toInt()
        receivePoints(count)
        _stateButtonFlow.value = true
    }

    private fun receivePoints(count: Int) {
        viewModelScope.launch {
            val response = repository.getPoints(count)
            handlePointsResponse(response)
        }
    }

    private suspend fun handlePointsResponse(response: PointCallResponse) {
        when (response) {
            is PointCallResponse.Success -> _openNextScreenEvent.send(response.idDataInDb)
            is PointCallResponse.Error -> _errorResponseFlow.send(response.message)
        }
    }
}
