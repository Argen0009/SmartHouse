package mbk.io.smarthouse.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import mbk.io.smarthouse.data.local.database.dao.CameraDao
import mbk.io.smarthouse.data.local.database.dao.DoorDao
import mbk.io.smarthouse.data.local.model.CameraEntity
import mbk.io.smarthouse.data.local.model.DoorEntity

@Database(entities = [CameraEntity::class, DoorEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cameraDao(): CameraDao
    abstract fun doorDao(): DoorDao
}