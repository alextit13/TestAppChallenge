package com.testapp.challenge.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.testapp.challenge.databinding.ActivityMainBinding
import com.testapp.challenge.ui.chart.ChartActivity
import com.testapp.challenge.ui.dialog.ErrorDialog
import com.testapp.challenge.ui.dialog.ErrorDialog.Companion.DIALOG_TAG
import com.testapp.challenge.ui.ext.appComponent
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels {
        appComponent().viewModelsFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observe()
        listen()
    }

    private fun observe() {
        viewModel.errorEnterAmountFlow.onEach {
            binding.etPointCount.error = if (it.isError) {
                getString(it.errorMessageResId)
            } else {
                null
            }
        }.launchIn(lifecycleScope)
        viewModel.stateButtonFlow.onEach { binding.btnRun.isEnabled = it }.launchIn(lifecycleScope)
        viewModel.errorResponseFlow.onEach {
            ErrorDialog.getInstance(it).show(supportFragmentManager, DIALOG_TAG)
        }.launchIn(lifecycleScope)
        viewModel.openNextScreenFlow.onEach {
            val chartIntent = ChartActivity.getInstance(this, it)
            startActivity(chartIntent)
        }.launchIn(lifecycleScope)
    }

    private fun listen() {
        binding.btnRun.setOnClickListener {
            viewModel.onClickRun(getEnterText())
        }
    }

    private fun getEnterText(): String = binding.etPointCount.text?.toString() ?: ""
}
