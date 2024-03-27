package mbk.io.smarthouse.api

import mbk.io.smarthouse.data.response.camera.CameraModel
import mbk.io.smarthouse.data.response.door.DoorModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("cameras/")
    suspend fun getCameras(): Response<CameraModel>

    @GET("doors/")
    suspend fun getDoors(): Response<DoorModel>
}