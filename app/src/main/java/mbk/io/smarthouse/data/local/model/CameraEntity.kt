package mbk.io.smarthouse.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "camera")
data class CameraEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val favorites: Boolean? = false,
    val name: String? = "",
    val rec: Boolean? = false,
    val room: String? = "",
    val snapshot: String? = "",
) : Serializable