package mbk.io.smarthouse.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import mbk.io.myhome.data.model.door.DoorEntity

@Dao
interface DoorDao {

    @Query("SELECT*FROM door")
    suspend fun getAll(): List<DoorEntity>

    @Insert
    suspend fun insertDoor(camera: DoorEntity)

    @Query("DELETE FROM door")
    suspend fun clearAll()

    @Delete
    suspend fun deleteDoor(doorEntity: DoorEntity)
}