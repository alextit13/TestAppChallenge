package com.testapp.challenge.di

import com.testapp.challenge.BuildConfig
import com.testapp.challenge.model.file.StoreFileManager
import com.testapp.challenge.model.network.Repository
import com.testapp.challenge.model.network.service.PointService
import com.testapp.challenge.model.network.service.exception.ResultCallAdapterFactory
import com.testapp.challenge.ui.chart.Scaller
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
    fun provideScaller(): Scaller = Scaller()

    @Provides
    @Singleton
    fun provideStoreFileManager(): StoreFileManager {
        return StoreFileManager()
    }

    @Provides
    @Singleton
    fun providesRepository(pointService: PointService): Repository {
        return Repository(pointService)
    }

    @Provides
    @Singleton
    fun providesPointService(retrofit: Retrofit): PointService {
        return retrofit.create(PointService::class.java)
    }

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(ResultCallAdapterFactory())
            .build()
    }
}
