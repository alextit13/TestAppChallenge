package com.testapp.challenge.model.network.response

/**
 * @author aliakseicherniakovich
 */
sealed class PointCallResponse {

    class Success(val idDataInDb: Int): PointCallResponse()
    class Error(val message: String): PointCallResponse()
}
