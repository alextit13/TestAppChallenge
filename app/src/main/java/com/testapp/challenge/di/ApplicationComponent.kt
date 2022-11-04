package com.testapp.challenge.di

import com.testapp.challenge.ui.chart.ChartViewModelFactory
import com.testapp.challenge.ui.main.MainActivityViewModel
import com.testapp.challenge.ui.main.MainViewModelFactory
import dagger.Component
import javax.inject.Singleton

/**
 * @author aliakseicherniakovich
 */
@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    // factories
    fun mainViewModelsFactory(): MainViewModelFactory

    fun chartViewModelsFactory(): ChartViewModelFactory

    // viewModels
    fun mainViewModel(): MainActivityViewModel
}
