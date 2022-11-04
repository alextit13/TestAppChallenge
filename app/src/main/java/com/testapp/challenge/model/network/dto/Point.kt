package com.testapp.challenge.model.network.dto

import com.google.gson.annotations.SerializedName

/**
 * @author aliakseicherniakovich
 */
class Point(
    @SerializedName("x") var x: Double,
    @SerializedName("y") val y: Double
)
