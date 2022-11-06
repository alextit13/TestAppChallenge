package com.testapp.challenge.model.network.dto

import com.google.gson.annotations.SerializedName

/**
 * @author aliakseicherniakovich
 */
class PointResponse(
    @SerializedName("points") var points: List<Point>
)
