package com.example.jetpack_compose_login_app.presentation.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.ui.platform.LocalContext
import java.util.Locale

import android.app.Activity
import com.example.jetpack_compose_login_app.R


fun setLocale(context: Context, languageCode: String) {
    val locale = Locale(languageCode)
    Locale.setDefault(locale)

    val config = context.resources.configuration
    config.setLocale(locale)
    context.resources.updateConfiguration(config, context.resources.displayMetrics)

    // Recreate the activity to apply the language change
    if (context is Activity) {
        context.recreate()  // This will restart the activity and apply the new locale
    }
}


@Composable
fun LanguageSwitcher() {
    val context = LocalContext.current
    val currentLocale = context.resources.configuration.locales[0]

    Button(onClick = {
        if (currentLocale.language == "ar") {
            setLocale(context, "en")
        } else {
            setLocale(context, "ar")
        }
    }) {
        Text(text = stringResource(id = R.string.switch_to_english))
    }
}


@Composable
fun ProfileScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            Text(text = stringResource(id = R.string.welcome_message))  // Localized string
            LanguageSwitcher()
        }
    }
}
