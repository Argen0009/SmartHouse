package mbk.io.myhome.api

import mbk.io.smarthouse.data.local.model.camera.CameraModel
import com.geeks.smarthome.data.model.door.DoorModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("cameras/")
    suspend fun getCameras(): Response<CameraModel>

    @GET("doors/")
    suspend fun getDoors(): Response<DoorModel>
}