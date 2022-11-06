package com.testapp.challenge.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.testapp.challenge.model.db.entity.PointResponseLocalEntity

/**
 * @author aliakseicherniakovich
 */

@Database(entities = [PointResponseLocalEntity::class], version = 1)
@TypeConverters(PointDbConverter::class)
abstract class PointDb : RoomDatabase() {

    abstract fun pointsDao(): PointDao
}
