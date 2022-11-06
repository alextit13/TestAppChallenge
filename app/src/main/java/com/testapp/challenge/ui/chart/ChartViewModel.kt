package com.testapp.challenge.ui.chart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.testapp.challenge.model.network.Repository
import com.testapp.challenge.model.network.dto.Point
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author aliakseicherniakovich
 */
class ChartViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private var id: Int = UNKNOWN_ARGS_ID

    private val _listCoordinatesFlow = MutableStateFlow<List<Point>?>(null)
    val listCoordinatesFlow = _listCoordinatesFlow.asStateFlow()

    fun onViewLoaded(id: Int) {
        if (this.id != UNKNOWN_ARGS_ID) {
            return
        }
        this.id = id
        viewModelScope.launch { loadPoints() }
    }

    private suspend fun loadPoints() {
        val points = repository.getDataFromLocalDb(id).sortedBy { it.x }
        _listCoordinatesFlow.value = null
        _listCoordinatesFlow.value = points
        repository.clearDb()
    }

    companion object {
        const val UNKNOWN_ARGS_ID = -1
    }
}
