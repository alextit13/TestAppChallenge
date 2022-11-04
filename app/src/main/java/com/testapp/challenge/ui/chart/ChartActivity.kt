package com.testapp.challenge.ui.chart

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.testapp.challenge.databinding.ActivityChartBinding

/**
 * @author aliakseicherniakovich
 */
class ChartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChartBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
