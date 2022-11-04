package com.testapp.challenge.ui.chart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

/**
 * @author aliakseicherniakovich
 */
class ChartViewModelFactory @Inject constructor(
    provider: Provider<ChartViewModel>
) : ViewModelProvider.Factory {

    private val providers = mapOf<Class<*>, Provider<out ViewModel>>(
        ChartViewModel::class.java to provider
    )

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return providers[modelClass]!!.get() as T
    }
}
