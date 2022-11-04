package com.testapp.challenge.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.testapp.challenge.model.db.entity.PointResponseLocalEntity

/**
 * @author aliakseicherniakovich
 */

@Database(entities = [PointResponseLocalEntity::class], version = 1)
abstract class PointDb : RoomDatabase() {

    abstract fun pointsDao(): PointDao
}
