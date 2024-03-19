package mbk.io.myhome.data.model.door

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "door")
data class DoorEntity(
    val favorites: Boolean? = false,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String? = "",
    val room: String? = "",
    val snapshot: String? = "",
) : Serializable