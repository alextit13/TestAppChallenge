package com.testapp.challenge.ui.chart

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.testapp.challenge.databinding.ActivityChartBinding
import com.testapp.challenge.model.network.dto.Point
import com.testapp.challenge.ui.chart.ChartViewModel.Companion.UNKNOWN_ARGS_ID
import com.testapp.challenge.ui.chart.adapter.PointGridAdapter
import com.testapp.challenge.ui.ext.appComponent
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * @author aliakseicherniakovich
 */
class ChartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChartBinding

    private val viewModel: ChartViewModel by viewModels {
        appComponent().chartViewModelsFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observe()
        listen()
        viewModel.onViewLoaded(intent.getIntExtra(ID_KEY, UNKNOWN_ARGS_ID))
    }

    private fun observe() {
        viewModel.listCoordinatesFlow.filterNotNull().onEach {
            showCoordinates(it)
        }.launchIn(lifecycleScope)
    }

    private fun showCoordinates(list: List<Point>) {
        if (binding.listCoordinates.adapter == null) {
            binding.listCoordinates.adapter = PointGridAdapter()
        }
        (binding.listCoordinates.adapter as? PointGridAdapter)?.bindSubList(list)
    }

    private fun listen() {
        // todo
    }

    companion object {
        fun getInstance(context: Context, id: Int): Intent {
            return Intent(context, ChartActivity::class.java).apply {
                putExtra(ID_KEY, id)
            }
        }

        private const val ID_KEY = "ID_KEY"
    }
}
