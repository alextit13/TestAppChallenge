package com.testapp.challenge.model.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.testapp.challenge.model.db.entity.PointResponseLocalEntity

/**
 * @author aliakseicherniakovich
 */

@Dao
interface PointDao {

    @Insert(entity = PointResponseLocalEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertPoints(entity: PointResponseLocalEntity)

    @Query("SELECT * FROM pointresponselocalentity WHERE id=:id LIMIT 1")
    suspend fun getPoints(id: Int): PointResponseLocalEntity

    @Query("DELETE FROM pointresponselocalentity")
    suspend fun delete()
}
