package com.testapp.challenge.ui.chart

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast.LENGTH_SHORT
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.testapp.challenge.R
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
    private val launcherWriteExternalStorage =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                saveImageIntoFile()
            }
        }

    private val viewModel: ChartViewModel by viewModels {
        appComponent().chartViewModelsFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observe()
        viewModel.onViewLoaded(intent.getIntExtra(ID_KEY, UNKNOWN_ARGS_ID))
    }

    private fun observe() {
        viewModel.listCoordinatesFlow.filterNotNull().onEach {
            showCoordinates(it)
            showChart(it)
        }.launchIn(lifecycleScope)
        viewModel.saveFileResultEvent.onEach {
            Snackbar.make(binding.root, it, LENGTH_SHORT).show()
        }.launchIn(lifecycleScope)
        viewModel.chartModeFlow.onEach {
            binding.chartView.setChartMode(it)
        }.launchIn(lifecycleScope)
    }

    private fun showCoordinates(list: List<Point>) {
        if (binding.listCoordinates.adapter == null) {
            binding.listCoordinates.adapter = PointGridAdapter()
        }
        (binding.listCoordinates.adapter as? PointGridAdapter)?.bindSubList(list)
    }

    private fun showChart(list: List<Point>) {
        binding.chartView.setPointsData(list)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_options_chart, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_save_image -> onClickSaveFile()
            R.id.menu_change_mode -> changeChartMode()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onClickSaveFile() {
        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PERMISSION_GRANTED) {
            saveImageIntoFile()
        } else {
            launcherWriteExternalStorage.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
    }

    private fun changeChartMode() {
        viewModel.setChartMode()
    }

    private fun saveImageIntoFile() {
        viewModel.saveBitmapIntoFile(binding.chartView.getBitmap())
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
