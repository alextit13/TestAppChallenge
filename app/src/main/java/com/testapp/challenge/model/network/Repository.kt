package com.testapp.challenge.model.network

import com.testapp.challenge.App
import com.testapp.challenge.model.db.entity.PointResponseLocalEntity
import com.testapp.challenge.model.db.entity.PointResponseLocalEntity.Companion.ID
import com.testapp.challenge.model.network.dto.PointResponse
import com.testapp.challenge.model.network.response.PointCallResponse
import com.testapp.challenge.model.network.service.RetrofitService
import com.testapp.challenge.model.network.service.exception.ResultCall.Companion.UNKNOWN_ERROR
import kotlin.random.Random

/**
 * @author aliakseicherniakovich
 */
class Repository {

    suspend fun getPoints(count: Int): PointCallResponse {
        RetrofitService.service.getPoints(count).onSuccess {
            return PointCallResponse.Success(saveDataToLocalDb(it))
        }.onFailure {
            return PointCallResponse.Error(it.message ?: "")
        }

        throw Exception(UNKNOWN_ERROR)
    }

    private suspend fun saveDataToLocalDb(pointResponse: PointResponse): Int {
        App.appInstance.db.pointsDao()
            .upsertPoints(PointResponseLocalEntity(points = pointResponse.points))
        return ID
    }
}
