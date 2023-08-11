package me.blanik.sample.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import me.blanik.sample.SignInViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: SignInViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column {
                        SignInScreen(viewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun SignInScreen(
    signInViewModel: SignInViewModel = SignInViewModel()
) {
    val emailState by signInViewModel.email.collectAsState()
    val passwordState by signInViewModel.password.collectAsState()
    val stateState by signInViewModel.state.collectAsState()
    val errorMessage by signInViewModel.errorMessage.collectAsState()

    Column {
        Text("Input")
        OutlinedTextField(
            label = { Text(text = "Email") },
            value = emailState,
            onValueChange = signInViewModel::setEmail
        )
        OutlinedTextField(
            label = { Text(text = "Password") },
            value = passwordState,
            onValueChange = signInViewModel::setPassword,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Text("Output")
        Text(text = emailState)
        Text(text = passwordState)
        Text(text = stateState.name)
        Text(text = errorMessage ?: "—")
        Text("Actions")
        Button(onClick = {
            GlobalScope.async(Dispatchers.Main) {
                signInViewModel.signIn()
            }
        }) {
            Text(text = "Sign in")
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        SignInScreen(signInViewModel = SignInViewModel())
    }
}
