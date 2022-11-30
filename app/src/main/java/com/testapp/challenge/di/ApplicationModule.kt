package com.testapp.challenge.di

import com.testapp.challenge.BuildConfig
import com.testapp.challenge.model.file.FileManager
import com.testapp.challenge.model.file.StoreFileManager
import com.testapp.challenge.model.network.service.PointService
import com.testapp.challenge.model.network.service.exception.ResultCallAdapterFactory
import com.testapp.challenge.model.repository.Repository
import com.testapp.challenge.model.repository.RepositoryImpl
import com.testapp.challenge.ui.chart.scaller.Scaller
import com.testapp.challenge.ui.chart.scaller.ScallerImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * @author aliakseicherniakovich
 */
@Module
class ApplicationModule {

    @Provides
    @Singleton
    fun provideScaller(): Scaller = ScallerImpl()

    @Provides
    @Singleton
    fun provideStoreFileManager(): FileManager = StoreFileManager()

    @Provides
    @Singleton
    fun providesRepository(pointService: PointService): Repository = RepositoryImpl(pointService)

    @Provides
    @Singleton
    fun providesPointService(retrofit: Retrofit): PointService =
        retrofit.create(PointService::class.java)

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(ResultCallAdapterFactory())
        .build()
}
