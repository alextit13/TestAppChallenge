package com.testapp.challenge.model.network.dto

import com.google.gson.annotations.SerializedName

/**
 * @author aliakseicherniakovich
 */
class Point(
    @SerializedName("x") var x: Float,
    @SerializedName("y") val y: Float
)
