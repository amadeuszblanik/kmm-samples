package me.blanik.sample

import com.rickclephas.kmm.viewmodel.*
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import kotlinx.coroutines.flow.*
import me.blanik.sample.network.ApiAuthSignInPayload
import me.blanik.sample.network.WolfieApi

enum class ApiState {
    INIT,
    PENDING,
    SUCCESS,
    FAILURE
}

open class SignInViewModel: KMMViewModel() {
    private val wolfieApi = WolfieApi()

    private val _email = MutableStateFlow(viewModelScope, "")
    private val _password = MutableStateFlow(viewModelScope, "")

    private val _state = MutableStateFlow(viewModelScope, ApiState.INIT)

    private val _errorMessage = MutableStateFlow(viewModelScope, null as String?)

    @NativeCoroutinesState
    val email = _email.asStateFlow()

    @NativeCoroutinesState
    val password = _password.asStateFlow()

    @NativeCoroutinesState
    val state = _state.asStateFlow()

    @NativeCoroutinesState
    val errorMessage = _errorMessage.asStateFlow()

    fun setEmail(email: String) {
        println("Set email: $email")
        _email.value = email
    }

    fun setPassword(password: String) {
        println("Set password: $password")
        _password.value = password
    }

    suspend fun signIn() {
        println("Sign in with email: ${_email.value} and password: ${_password.value}")
        _state.value = ApiState.PENDING

        var response = wolfieApi.authSignIn(
            ApiAuthSignInPayload(
            username = _email.value,
            password = _password.value
        )
        )

        if (response.success != null) {
            _state.value = ApiState.SUCCESS
        } else {
            _state.value = ApiState.FAILURE
            _errorMessage.value = response.failure?.message ?: null
        }
    }
}
