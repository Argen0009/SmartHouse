package mbk.io.smarthouse.data.repository

import androidx.lifecycle.LiveData
import mbk.io.smarthouse.data.response.door.DoorModel
import mbk.io.smarthouse.api.ApiService
import mbk.io.smarthouse.data.local.model.DoorEntity
import mbk.io.smarthouse.data.local.database.AppDatabase
import mbk.io.smarthouse.data.local.model.CameraEntity
import mbk.io.smarthouse.data.resoure.Resource
import mbk.io.smarthouse.data.response.camera.CameraModel
import mbk.io.smarthouse.base.BaseRepository
import mbk.io.smarthouse.domain.repositories.CamerasRepository
import mbk.io.smarthouse.domain.repositories.DoorsRepository
import javax.inject.Inject

class RMRepository @Inject constructor(private val api: ApiService, private val db: AppDatabase) :
    BaseRepository(),CamerasRepository,DoorsRepository {

    override  fun getCameras(): LiveData<Resource<CameraModel>> = apiRequest {
        api.getCameras().body()!!
    }

    override fun getDoors(): LiveData<Resource<DoorModel>> = apiRequest {
        api.getDoors().body()!!
    }
    override suspend fun getDBCameras(): List<CameraEntity> = db.cameraDao().getAll()

    override suspend fun clearAll() = db.cameraDao().clearAll()
    override suspend fun deleteCamera(cameraEntity: CameraEntity) = db.cameraDao().delete(cameraEntity)


    override suspend fun insert(cameraEntity: CameraEntity) =
        db.cameraDao().insertCamera(cameraEntity)


    override suspend fun insert(doorEntity: DoorEntity) = db.doorDao().insertDoor(doorEntity)
    override suspend fun deleteDoor(doorEntity: DoorEntity) {
        db.doorDao().deleteDoor(doorEntity)
    }

    override suspend fun getDBDoors(): List<DoorEntity> = db.doorDao().getAll()
    override suspend fun clearAllDoors() = db.doorDao().clearAll()


}
