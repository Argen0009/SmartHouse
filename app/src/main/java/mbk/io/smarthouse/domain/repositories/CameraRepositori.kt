package mbk.io.smarthouse.domain.repositories

import androidx.lifecycle.LiveData
import mbk.io.smarthouse.data.resoure.Resource
import mbk.io.smarthouse.data.response.camera.CameraModel
import mbk.io.smarthouse.data.local.model.CameraEntity

interface CamerasRepository {

    fun getCameras(): LiveData<Resource<CameraModel>>

    suspend fun getDBCameras(): List<CameraEntity>

     suspend fun clearAll()

    suspend fun deleteCamera(cameraEntity: CameraEntity)

    suspend fun insert(cameraEntity: CameraEntity)
}