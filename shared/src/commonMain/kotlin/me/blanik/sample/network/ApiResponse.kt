package me.blanik.sample.network

import io.ktor.util.date.GMTDate
import kotlinx.serialization.Serializable

@Serializable
data class ApiError(
    val statusCode: Int = 400,
    val message: String = "",
    val timestamp: String = GMTDate().toString(),
    val errors: List<ApiErrorErrors>? = null
)

@Serializable
data class ApiErrorErrors(
    val property: String = "",
    val children: List<ApiErrorErrors> = emptyList(),
    val constraints: Map<String, String> = emptyMap()
)

class ApiResponse<T>(success: T?, error: ApiError?) {
    var success: T? = success
    var failure: ApiError? = error
}