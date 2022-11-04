package com.testapp.challenge.model.network.service

import retrofit2.Retrofit

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
            Retrofit.Builder()
                .baseUrl("")
                .build()
        }
        return retrofit!!
    }
}
