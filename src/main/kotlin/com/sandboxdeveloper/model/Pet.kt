package com.sandboxdeveloper.model
import kotlinx.serialization.Serializable

@Serializable
data class Pet(
    val id: String,
    val name: String,
    val type: String,
    val ownerId: String
)