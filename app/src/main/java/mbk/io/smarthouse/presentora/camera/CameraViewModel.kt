package mbk.io.smarthouse.presentora.camera

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import mbk.io.smarthouse.data.resoure.Resource
import mbk.io.smarthouse.data.response.camera.CameraModel
import dagger.hilt.android.lifecycle.HiltViewModel
import mbk.io.smarthouse.data.local.model.CameraEntity
import mbk.io.smarthouse.domain.usecases.GetCameraUseCase
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(private val cameraUseCase: GetCameraUseCase) :
    ViewModel() {
     fun getCameras(): LiveData<Resource<CameraModel>> = cameraUseCase.getCameras()

    suspend fun getDBCameras(): List<CameraEntity> = cameraUseCase.getDBCameras()

    suspend fun clearAll() = cameraUseCase.clearAll()

    suspend fun deleteCamera(cameraEntity: CameraEntity) = cameraUseCase.deleteCamera(cameraEntity)

    suspend fun insertCamera(cameraEntity: CameraEntity) = cameraUseCase.insert(cameraEntity)
}