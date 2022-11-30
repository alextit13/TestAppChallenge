package com.testapp.challenge.model.repository

import com.testapp.challenge.model.network.dto.Point
import com.testapp.challenge.model.network.response.PointCallResponse

/**
 * @author aliakseicherniakovich
 */
interface Repository {

    suspend fun getPoints(count: Int): PointCallResponse

    suspend fun getDataFromLocalDb(id: Int): List<Point>

    suspend fun clearDb()
}
