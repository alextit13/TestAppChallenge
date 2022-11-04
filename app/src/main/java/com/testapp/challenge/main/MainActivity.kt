package com.testapp.challenge.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.testapp.challenge.R
import com.testapp.challenge.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observe()
        listen()
    }

    private fun observe() {
        viewModel.errorFlow.onEach {
            binding.etPointCount.error = if (it.isError) {
                getString(it.errorMessageResId)
            } else {
                null
            }
        }.launchIn(lifecycleScope)
    }

    private fun listen() {
        binding.btnRun.setOnClickListener {
            viewModel.onClickRun(getEnterText())
        }
    }

    private fun getEnterText(): String = binding.etPointCount.text?.toString() ?: ""
}
