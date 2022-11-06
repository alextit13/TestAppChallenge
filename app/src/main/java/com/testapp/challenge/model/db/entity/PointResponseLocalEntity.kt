package com.testapp.challenge.model.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.testapp.challenge.model.network.dto.Point

/**
 * @author aliakseicherniakovich
 */
@Entity
data class PointResponseLocalEntity(
    @PrimaryKey val id: Int = ID,
    @ColumnInfo(name = "points") val points: List<Point>
) {
    companion object {
        const val ID = 1
    }
}
