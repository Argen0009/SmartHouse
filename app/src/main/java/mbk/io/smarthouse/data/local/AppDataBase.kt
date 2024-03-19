package mbk.io.smarthouse.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import mbk.io.myhome.data.model.door.DoorEntity
import mbk.io.smarthouse.data.local.camera.CameraDao
import mbk.io.smarthouse.data.local.dao.DoorDao
import mbk.io.smarthouse.data.local.model.camera.CameraEntity

@Database(entities = [CameraEntity::class, DoorEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cameraDao(): CameraDao
    abstract fun doorDao(): DoorDao
}