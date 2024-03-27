package mbk.io.smarthouse.api

import android.app.Application
import androidx.room.Room
import dagger.hilt.android.HiltAndroidApp
import mbk.io.smarthouse.data.local.database.AppDatabase

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "database-name"
        ).build()
    }

    companion object {
        lateinit var db: AppDatabase
    }
}