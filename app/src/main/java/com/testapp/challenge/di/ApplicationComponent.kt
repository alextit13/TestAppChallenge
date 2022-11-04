package com.testapp.challenge.di

import com.testapp.challenge.ui.main.MainActivityViewModel
import com.testapp.challenge.ui.main.ViewModelFactory
import dagger.Component
import javax.inject.Singleton

/**
 * @author aliakseicherniakovich
 */
@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun mainViewModel(): MainActivityViewModel

    fun viewModelsFactory(): ViewModelFactory
}
