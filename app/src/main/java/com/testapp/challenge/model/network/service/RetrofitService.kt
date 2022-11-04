package com.testapp.challenge.model.network.service

import com.testapp.challenge.BuildConfig
import com.testapp.challenge.model.network.service.exception.ResultCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author aliakseicherniakovich
 */
object RetrofitService {

    val service: PointService by lazy {
        getRetrofitInstance().create(PointService::class.java)
    }

    private var retrofit: Retrofit? = null

    private fun getRetrofitInstance(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(ResultCallAdapterFactory())
                .build()
        }
        return retrofit!!
    }
}
