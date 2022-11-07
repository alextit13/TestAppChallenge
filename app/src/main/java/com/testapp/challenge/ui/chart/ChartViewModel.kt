package com.testapp.challenge.ui.chart

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.testapp.challenge.R
import com.testapp.challenge.model.file.StoreFileManager
import com.testapp.challenge.model.network.Repository
import com.testapp.challenge.model.network.dto.Point
import com.testapp.chart.view.chart.ChartViewMode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * @author aliakseicherniakovich
 */
class ChartViewModel @Inject constructor(
    private val repository: Repository,
    private val storeFileManager: StoreFileManager,
    private val scaller: Scaller
) : ViewModel() {

    private var id: Int = UNKNOWN_ARGS_ID

    private val _listCoordinatesFlow = MutableStateFlow<List<Point>?>(null)
    val listCoordinatesFlow = _listCoordinatesFlow.asStateFlow()
    private val _listChartCoordinateFlow = MutableStateFlow<List<Pair<Float, Float>>?>(null)
    val listChartCoordinateFlow = _listChartCoordinateFlow.asStateFlow()
    private val _chartModeFlow = MutableStateFlow(ChartViewMode.defaultMode)
    val chartModeFlow = _chartModeFlow.asStateFlow()
    private val _saveFileResultEvent = Channel<Int>(Channel.BUFFERED)
    val saveFileResultEvent = _saveFileResultEvent.receiveAsFlow()

    fun onViewLoaded(id: Int) {
        if (this.id != UNKNOWN_ARGS_ID) {
            return
        }
        this.id = id
        viewModelScope.launch { loadPoints() }
    }

    fun saveBitmapIntoFile(bitmap: Bitmap) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val isSuccess = storeFileManager.saveBitmapIntoFile(bitmap)
                val message =
                    if (isSuccess) R.string.msg_success_save_file else R.string.msg_error_save_file
                withContext(Dispatchers.Main) {
                    _saveFileResultEvent.send(message)
                }
            }
        }
    }

    fun setChartMode() {
        val newMode = when (_chartModeFlow.value) {
            ChartViewMode.Linear -> ChartViewMode.Bezier
            ChartViewMode.Bezier -> ChartViewMode.Linear
        }
        _chartModeFlow.value = newMode
    }

    private suspend fun loadPoints() {
        val points = repository.getDataFromLocalDb(id).sortedBy { it.x }
        drawPoints(points)
        repository.clearDb()

        scaller.observe(points) {
            drawPoints(it)
        }
    }

    private fun drawPoints(points: List<Point>) {
        _listCoordinatesFlow.value = null
        _listCoordinatesFlow.value = points

        val pairsPoints = points.mapToPairs()
        _listChartCoordinateFlow.value = null
        _listChartCoordinateFlow.value = pairsPoints
    }

    fun onMoveLeft() {
        scaller.left()
    }

    fun onScaleIncrease() {
        scaller.plus()
    }

    fun onScaleDecrease() {
        scaller.minus()
    }

    fun onMoveRight() {
        scaller.right()
    }

    companion object {
        const val UNKNOWN_ARGS_ID = -1
    }
}
