package com.geeks.smarthome.data.model.door


import java.io.Serializable

data class DoorModel(
    val `data`: List<Data>,
    val success: Boolean,
)

data class Data(
    val favorites: Boolean,
    val id: Int,
    val name: String,
    val room: String,
    val snapshot: String,
)