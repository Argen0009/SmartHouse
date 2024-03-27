package mbk.io.smarthouse.domain.usecases

import androidx.lifecycle.LiveData
import mbk.io.smarthouse.data.resoure.Resource
import mbk.io.smarthouse.data.response.door.DoorModel
import mbk.io.smarthouse.data.local.model.DoorEntity
import mbk.io.smarthouse.domain.repositories.DoorsRepository
import javax.inject.Inject

class GetDoorUseCase @Inject constructor(private val doorsRepository: DoorsRepository) {
    fun getDoors():LiveData<Resource<DoorModel>> = doorsRepository.getDoors()

    suspend fun getDBDoors(): List<DoorEntity> = doorsRepository.getDBDoors()
    suspend fun insert(doorEntity: DoorEntity) = doorsRepository.insert(doorEntity)
    suspend fun clearAll() = doorsRepository.clearAllDoors()

    suspend fun deleteDoor(doorEntity: DoorEntity) = doorsRepository.deleteDoor(doorEntity)

}