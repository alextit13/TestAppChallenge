package com.testapp.challenge

import android.app.Application
import androidx.room.Room
import com.testapp.challenge.di.ApplicationComponent
import com.testapp.challenge.di.DaggerApplicationComponent
import com.testapp.challenge.model.db.PointDb

/**
 * @author aliakseicherniakovich
 */
class App : Application() {

    val appComponent: ApplicationComponent =
        DaggerApplicationComponent.create()

    var db: PointDb? = null

    override fun onCreate() {
        super.onCreate()
        appInstance = this

        db = Room.databaseBuilder(
                this,
        PointDb::class.java,
        LOCAL_POINTS_DB_NAME
        ).build()
    }

    companion object {
        lateinit var appInstance: App

        private const val LOCAL_POINTS_DB_NAME = "points"
    }
}
