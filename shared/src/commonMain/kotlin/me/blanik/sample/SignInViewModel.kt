package me.blanik.sample

import com.rickclephas.kmm.viewmodel.*
import kotlinx.coroutines.flow.*

open class SignInViewModel: KMMViewModel() {
    private val _email = MutableStateFlow(viewModelScope, "")
    private val _password = MutableStateFlow(viewModelScope, "")

    val email = _email.asStateFlow()
    val password = _password.asStateFlow()

    fun setEmail(email: String) {
        _email.value = email
    }

    fun setPassword(password: String) {
        _password.value = password
    }
}
