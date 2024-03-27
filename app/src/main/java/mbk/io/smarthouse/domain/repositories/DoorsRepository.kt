package mbk.io.smarthouse.domain.repositories

import androidx.lifecycle.LiveData
import mbk.io.smarthouse.data.resoure.Resource
import mbk.io.smarthouse.data.response.door.DoorModel
import mbk.io.smarthouse.data.local.model.DoorEntity

interface DoorsRepository {
    fun getDoors(): LiveData<Resource<DoorModel>>
    suspend fun getDBDoors(): List<DoorEntity>

    suspend fun clearAllDoors()

    suspend fun insert(doorEntity: DoorEntity)

    suspend fun deleteDoor(doorEntity: DoorEntity)
}