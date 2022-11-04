package com.testapp.challenge.model.network

import com.testapp.challenge.model.network.dto.PointResponse
import com.testapp.challenge.model.network.response.PointCallResponse
import com.testapp.challenge.model.network.service.RetrofitService
import com.testapp.challenge.model.network.service.exception.ResultCall.Companion.UNKNOWN_ERROR

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
        TODO()
    }
}
