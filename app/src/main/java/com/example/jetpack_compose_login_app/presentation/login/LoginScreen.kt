package com.example.jetpack_compose_login_app.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.jetpack_compose_login_app.R
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navToSignUp: () -> Unit,
    navToMain: () -> Unit,
) {

    val loginViewModel: LoginViewModel = koinViewModel()
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()

    Box(modifier = Modifier
        .fillMaxSize()
        .imePadding() // This ensures padding when the keyboard appears
        .pointerInput(Unit) {
            detectTapGestures(onTap = {
                focusManager.clearFocus() // Close keyboard when clicking outside
            })
        }) {
        // Image with fixed height
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.jclogin),
                contentDescription = "Login Image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(top = 390.dp) // Adjust to position the form correctly
//                .windowInsetsPadding(WindowInsets.safeContent
//                    .only(WindowInsetsSides.Bottom + WindowInsetsSides.Top))
            , horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(text = "Enter Your email") },
                maxLines = 1,
                isError = emailError,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next // "Next" button
                ),
                keyboardActions = KeyboardActions(onNext = {
                    focusManager.moveFocus(androidx.compose.ui.focus.FocusDirection.Down) // Move to the next text field
                })
            )
            if (emailError) {
                Text(
                    text = "email cannot be empty",
                    color = Color.Red,
                    modifier = Modifier.padding(top = 5.dp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "Enter Your password") },
                maxLines = 1,
                isError = passwordError,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done // "Done" button
                ),
                keyboardActions = KeyboardActions(onDone = {
                    focusManager.clearFocus() // Close the keyboard
                })
            )
            if (passwordError) {
                Text(
                    text = "Password must be at least 6 characters",
                    color = Color.Red,
                    modifier = Modifier.padding(top = 5.dp)
                )
            }
            Spacer(modifier = Modifier.height(25.dp))

            Button(modifier = Modifier.width(280.dp), onClick = {
                emailError = email.text.isEmpty()
                passwordError = password.text.isEmpty()
                if (!emailError && !passwordError) {
                    loginViewModel.login(email.text) { res ->
                        if (res != null) {
                            if (res.password == password.text) {
                                navToMain.invoke()
                            } else {
                                //add popup for invalid password
                                print(res)
                            }
                        } else {
                            //add popup for invalid email
                        }
                    }
                }
            }) {
                Text(text = "LogIn", modifier = Modifier.padding(vertical = 10.dp))
            }
            Text(text = "SignUp",
                color = Color.Blue, // Optional: Give it a different color to indicate it's clickable
                textDecoration = TextDecoration.Underline, // Underline the text
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .clickable {
                        navToSignUp.invoke()
                    })
        }
    }
}
