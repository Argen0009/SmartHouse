package mbk.io.smarthouse.presentora.door

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import mbk.io.smarthouse.data.Resource
import com.geeks.smarthome.data.model.door.DoorModel
import dagger.hilt.android.lifecycle.HiltViewModel
import mbk.io.myhome.data.model.door.DoorEntity
import mbk.io.smarthouse.domain.usecases.GetDoorUseCase
import javax.inject.Inject

@HiltViewModel
class DoorViewModel @Inject constructor(private val doorUseCase: GetDoorUseCase) : ViewModel() {
    fun getDoors(): LiveData<Resource<DoorModel>> = doorUseCase.getDoors()

    suspend fun getDBDoors(): List<DoorEntity> = doorUseCase.getDBDoors()

    suspend fun deleteDoor(doorEntity: DoorEntity) = doorUseCase.deleteDoor(doorEntity)

    suspend fun clearAll() = doorUseCase.clearAll()

    suspend fun insert(doorEntity: DoorEntity) = doorUseCase.insert(doorEntity)
}

