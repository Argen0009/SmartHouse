package mbk.io.smarthouse.domain.usecases

import androidx.lifecycle.LiveData
import mbk.io.smarthouse.data.Resource
import mbk.io.smarthouse.data.local.model.camera.CameraModel
import mbk.io.smarthouse.data.local.model.camera.CameraEntity
import mbk.io.smarthouse.domain.repositories.CamerasRepository
import javax.inject.Inject

class GetCameraUseCase @Inject constructor(private val camerasRepository: CamerasRepository) {
    fun getCameras(): LiveData<Resource<CameraModel>> = camerasRepository.getCameras()

    suspend fun getDBCameras(): List<CameraEntity> = camerasRepository.getDBCameras()

    suspend fun clearAll() = camerasRepository.clearAll()

    suspend fun deleteCamera(cameraEntity: CameraEntity) = camerasRepository.deleteCamera(cameraEntity)

    suspend fun insert(cameraEntity: CameraEntity) = camerasRepository.insert(cameraEntity)
}