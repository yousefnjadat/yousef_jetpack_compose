package com.example.jetpack_compose_signup_app

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalFocusManager
import androidx.navigation.NavHostController
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import com.example.jetpack_compose_login_app.database.UserModelViewModel
import java.util.regex.Pattern

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController: NavHostController, vm: UserModelViewModel) {
    var fullName by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var confirmPassword by remember { mutableStateOf(TextFieldValue("")) }

    var showPassword by remember { mutableStateOf(value = false) }
    var showConfirmPassword by remember { mutableStateOf(value = false) }

    var fullNameError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var confirmPasswordError by remember { mutableStateOf(false) }
    val isNotMatching = remember {
        derivedStateOf {
            password != confirmPassword
        }
    }
    val isEmailValid = remember {
        derivedStateOf {
            if (email.text.isNotEmpty()) {
                email.text.isValidEmail()
            } else {
                true
            }
        }
    }

    val focusManager = LocalFocusManager.current

    Scaffold(topBar = {
        TopAppBar(title = { Text("Sign Up", fontSize = 20.sp) }, navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack() // Navigate back
            }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
            }
        })
    }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp), // Ensures content is spaced well
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = fullName,
                onValueChange = { fullName = it },
                label = { Text(text = "Full Name") },
                isError = fullNameError,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = {
                    focusManager.moveFocus(androidx.compose.ui.focus.FocusDirection.Down)
                })
            )
            if (fullNameError) {
                Text(
                    text = "Full Name cannot be empty",
                    color = Color.Red,
                    modifier = Modifier.padding(top = 5.dp)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(text = "Email") },
                isError = emailError,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = {
                    focusManager.moveFocus(androidx.compose.ui.focus.FocusDirection.Down)
                })
            )
            if (emailError) {
                Text(
                    text = "Email cannot be empty",
                    color = Color.Red,
                    modifier = Modifier.padding(top = 5.dp)
                )
            }
            if (!isEmailValid.value) {
                Text(
                    text = "Enter a valid Email",
                    color = Color.Red,
                    modifier = Modifier.padding(top = 5.dp)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(modifier = Modifier.fillMaxWidth(),
                value = password,
                onValueChange = { newText ->
                    password = newText
                },
                label = {
                    Text(text = "Password")
                },
                placeholder = { Text(text = "Type password here") },
                shape = RoundedCornerShape(percent = 20),
                visualTransformation = if (showPassword) {

                    VisualTransformation.None

                } else {

                    PasswordVisualTransformation()

                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    if (showPassword) {
                        IconButton(onClick = { showPassword = false }) {
                            Icon(
                                imageVector = Icons.Filled.Visibility,
                                contentDescription = "hide_password"
                            )
                        }
                    } else {
                        IconButton(onClick = { showPassword = true }) {
                            Icon(
                                imageVector = Icons.Filled.VisibilityOff,
                                contentDescription = "hide_password"
                            )
                        }
                    }
                })

            if (passwordError) {
                Text(
                    text = "Password cannot be empty",
                    color = Color.Red,
                    modifier = Modifier.padding(top = 5.dp)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text(text = "Confirm Password") },
                isError = confirmPasswordError,
                visualTransformation = if (showPassword) {

                    VisualTransformation.None

                } else {

                    PasswordVisualTransformation()

                },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                keyboardActions = KeyboardActions(onDone = {
                    focusManager.clearFocus()
                }),
                placeholder = { Text(text = "Type confirm password here") },
                shape = RoundedCornerShape(percent = 20),
                trailingIcon = {
                    if (showConfirmPassword) {
                        IconButton(onClick = { showConfirmPassword = false }) {
                            Icon(
                                imageVector = Icons.Filled.Visibility,
                                contentDescription = "hide_password"
                            )
                        }
                    } else {
                        IconButton(onClick = { showConfirmPassword = true }) {
                            Icon(
                                imageVector = Icons.Filled.VisibilityOff,
                                contentDescription = "hide_password"
                            )
                        }
                    }
                })

            if (confirmPasswordError) {
                Text(
                    text = "Confirm password cannot be empty",
                    color = Color.Red,
                    modifier = Modifier.padding(top = 5.dp)
                )
            }
            if (isNotMatching.value) {
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = "Passwords not matching")
            }
            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    fullNameError = fullName.text.isEmpty()
                    emailError = email.text.isEmpty()
                    passwordError = password.text.isEmpty()
                    confirmPasswordError = confirmPassword.text.isEmpty()

                    if (!fullNameError && !emailError && !passwordError && !confirmPasswordError && !isNotMatching.value && isEmailValid.value) {
                        // Perform sign-up logic
                        vm.addData(
                            fullName.text, email.text, password.text
                        )
                        navController.navigate("login") // Navigate to the main screen
                    }
                }, modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Sign Up", modifier = Modifier.padding(vertical = 10.dp))
            }
        }
    }
}

val EMAIL_ADDRESS_PATTERN: Pattern = Pattern.compile(
    "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+"
)

fun String.isValidEmail(): Boolean {
    return EMAIL_ADDRESS_PATTERN.matcher(this).matches()
}