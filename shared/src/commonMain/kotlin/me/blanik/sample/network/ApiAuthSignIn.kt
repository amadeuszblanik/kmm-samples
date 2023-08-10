package me.blanik.sample.network

import kotlinx.serialization.*

@Serializable
data class ApiAuthSignInResponse(
    val accessToken: String,
    val refreshToken: String? = null
)

@Serializable
data class ApiAuthSignInPayload(
    val username: String,
    val password: String,
    val keepSignIn: Boolean? = null,
    val device: String? = null
)
