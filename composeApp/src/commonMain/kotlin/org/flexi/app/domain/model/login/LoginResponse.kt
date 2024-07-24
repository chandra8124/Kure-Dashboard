package org.flexi.app.domain.model.login

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    @SerialName("status") val status: Boolean,
    @SerialName("message") val message: String,
    @SerialName("data") val data: LoginResponseData
)

@Serializable
data class LoginResponseData(
    @SerialName("token") val token: String,
    @SerialName("is_provider") val is_provider: String,
    @SerialName("isPatient") val isPatient: String
)