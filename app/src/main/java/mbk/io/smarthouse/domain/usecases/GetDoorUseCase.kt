package mbk.io.smarthouse.domain.usecases

import androidx.lifecycle.LiveData
import mbk.io.smarthouse.data.Resource
import com.geeks.smarthome.data.model.door.DoorModel
import mbk.io.myhome.data.model.door.DoorEntity
import mbk.io.smarthouse.domain.repositories.DoorsRepository
import javax.inject.Inject

class GetDoorUseCase @Inject constructor(private val doorsRepository: DoorsRepository) {
    fun getDoors():LiveData<Resource<DoorModel>> = doorsRepository.getDoors()

    suspend fun getDBDoors(): List<DoorEntity> = doorsRepository.getDBDoors()
    suspend fun insert(doorEntity: DoorEntity) = doorsRepository.insert(doorEntity)
    suspend fun clearAll() = doorsRepository.clearAllDoors()

    suspend fun deleteDoor(doorEntity: DoorEntity) = doorsRepository.deleteDoor(doorEntity)

}