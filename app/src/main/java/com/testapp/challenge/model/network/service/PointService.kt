package com.testapp.challenge.model.network.service

import com.testapp.challenge.model.network.dto.PointResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author aliakseicherniakovich
 */
interface PointService {

    @GET("/api/test/points")
    suspend fun getPoints(@Query("count") count: Int): Result<PointResponse>
}
