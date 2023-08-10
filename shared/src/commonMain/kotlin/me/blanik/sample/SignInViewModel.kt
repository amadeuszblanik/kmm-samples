package me.blanik.sample

import com.rickclephas.kmm.viewmodel.*
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import kotlinx.coroutines.flow.*

open class SignInViewModel: KMMViewModel() {
    private val _email = MutableStateFlow(viewModelScope, "")
    private val _password = MutableStateFlow(viewModelScope, "")

    @NativeCoroutinesState
    val email = _email.asStateFlow()

    @NativeCoroutinesState
    val password = _password.asStateFlow()

    fun setEmail(email: String) {
        println("Set email: $email")
        _email.value = email
    }

    fun setPassword(password: String) {
        println("Set password: $password")
        _password.value = password
    }
}
