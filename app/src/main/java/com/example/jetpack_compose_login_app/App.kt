package com.example.jetpack_compose_login_app

import android.app.Application
import com.example.jetpack_compose_login_app.data.database.createRoomInstance
import com.example.jetpack_compose_login_app.data.database.createRoomDao
import com.example.jetpack_compose_login_app.data.netowrk.buildOkHttpClient
import com.example.jetpack_compose_login_app.data.netowrk.buildRetrofit
import com.example.jetpack_compose_login_app.data.netowrk.createHomeService
import com.example.jetpack_compose_login_app.domain.home.HomeRepository
import com.example.jetpack_compose_login_app.domain.login.LoginRepository
import com.example.jetpack_compose_login_app.domain.signup.SignupRepository
import com.example.jetpack_compose_login_app.presentation.home.HomeViewModel
import com.example.jetpack_compose_login_app.presentation.login.LoginViewModel
import com.example.jetpack_compose_login_app.presentation.signup.SignupViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val roomDatabaseModule = module {
    single { createRoomInstance(get()) }
    single { createRoomDao(get()) }
}

val retrofitModule = module {
    single { buildOkHttpClient() }
    single { buildRetrofit(get()) }
    single { createHomeService(get()) }
}
val domainModule = module {
    factory { HomeRepository(get()) }
    factory { LoginRepository(get()) }
    factory { SignupRepository(get()) }
}

val viewModels = module {
    viewModel { HomeViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { SignupViewModel(get()) }
}

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    private fun startKoin() {
        startKoin {
            androidContext(this@App)
            modules(
                roomDatabaseModule,
                retrofitModule,
                viewModels,
                domainModule
            )
        }
    }
}



