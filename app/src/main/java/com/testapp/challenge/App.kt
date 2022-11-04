package com.testapp.challenge

import android.app.Application
import androidx.room.Room
import com.testapp.challenge.model.db.PointDb

/**
 * @author aliakseicherniakovich
 */
class App : Application() {

    val db = Room.databaseBuilder(
        this,
        PointDb::class.java,
        LOCAL_POINTS_DB_NAME
    ).build()

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {
        lateinit var appInstance: App

        private const val LOCAL_POINTS_DB_NAME = "points"
    }
}
