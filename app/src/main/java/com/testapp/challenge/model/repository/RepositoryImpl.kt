package com.testapp.challenge.model.repository

import com.testapp.challenge.App
import com.testapp.challenge.model.db.entity.PointResponseLocalEntity
import com.testapp.challenge.model.network.dto.Point
import com.testapp.challenge.model.network.dto.PointResponse
import com.testapp.challenge.model.network.response.PointCallResponse
import com.testapp.challenge.model.network.service.PointService
import com.testapp.challenge.model.network.service.exception.ResultCall

/**
 * @author aliakseicherniakovich
 */
class RepositoryImpl(private val pointService: PointService): Repository {

    override suspend fun getPoints(count: Int): PointCallResponse {
        pointService.getPoints(count).onSuccess {
            return PointCallResponse.Success(saveDataToLocalDb(it))
        }.onFailure {
            return PointCallResponse.Error(it.message ?: "")
        }

        throw Exception(ResultCall.UNKNOWN_ERROR)
    }

    override suspend fun getDataFromLocalDb(id: Int): List<Point> {
        return App.appInstance.db?.pointsDao()?.getPoints(id)?.points ?: listOf()
    }

    override suspend fun clearDb() {
        App.appInstance.db?.pointsDao()?.delete()
    }

    private suspend fun saveDataToLocalDb(pointResponse: PointResponse): Int {
        App.appInstance.db?.pointsDao()
            ?.upsertPoints(PointResponseLocalEntity(points = pointResponse.points))
        return PointResponseLocalEntity.ID
    }
}
