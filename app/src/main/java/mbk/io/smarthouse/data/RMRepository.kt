package mbk.io.smarthouse.data

import androidx.lifecycle.LiveData
import com.geeks.smarthome.data.model.door.DoorModel
import mbk.io.myhome.api.ApiService
import mbk.io.myhome.data.model.door.DoorEntity
import mbk.io.smarthouse.data.local.AppDatabase
import mbk.io.smarthouse.data.local.model.camera.CameraEntity
import mbk.io.smarthouse.data.local.model.camera.CameraModel
import mbk.io.smarthouse.domain.base.BaseRepository
import javax.inject.Inject

class RMRepository @Inject constructor(private val api: ApiService, private val db: AppDatabase) :
    BaseRepository(api) {
    fun getCameras(): LiveData<Resource<CameraModel>> {
        return apiRequest {
            api.getCameras().body()!!
        }
    }

    fun getDoors(): LiveData<Resource<DoorModel>> = apiRequest {
        api.getDoors().body()!!
    }
    suspend fun getDBCameras(): List<CameraEntity> = db.cameraDao().getAll()

    suspend fun clearAll() = db.cameraDao().clearAll()
    suspend fun deleteCamera(cameraEntity: CameraEntity) = db.cameraDao().delete(cameraEntity)


    suspend fun insert(cameraEntity: CameraEntity) =
        db.cameraDao().insertCamera(cameraEntity)


    suspend fun insert(doorEntity: DoorEntity) = db.doorDao().insertDoor(doorEntity)
    suspend fun deleteDoor(doorEntity: DoorEntity) {
        db.doorDao().deleteDoor(doorEntity)
    }

    suspend fun getDBDoors(): List<DoorEntity> = db.doorDao().getAll()
    suspend fun clearAllDoors() = db.doorDao().clearAll()


}
