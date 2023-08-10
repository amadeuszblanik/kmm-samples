package me.blanik.sample.network

import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.plugins.cache.*
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class WolfieApi {
    private val client by lazy {
        HttpClient {
            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.ALL
                sanitizeHeader { header -> header == HttpHeaders.Authorization }
            }
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
                    useAlternativeNames = true
                })
            }
            install(HttpCache)
        }
    }

    suspend fun authSignIn(payload: ApiAuthSignInPayload): ApiResponse<ApiAuthSignInResponse> {
        var response = client.post("$API_BASE_URL$AUTH_SIGN_IN") {
            contentType(ContentType.Application.Json)
            setBody(payload)
        }

        return if (response.status.value in 200..299) {
            ApiResponse(response.body(), null)
        } else {
            ApiResponse(null, response.body())
        }
    }

    companion object {
        private const val API_BASE_URL = "https://api-x.wolfie.app/v2"
        private const val AUTH_SIGN_IN = "/auth/sign-in"
    }
}
