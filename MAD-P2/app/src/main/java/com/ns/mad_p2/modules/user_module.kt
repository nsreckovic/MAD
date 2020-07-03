package com.ns.mad_p2.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import com.ns.mad_p2.data.datasources.local.sharedPreferences.SPUserDataSource
import com.ns.mad_p2.data.datasources.local.sharedPreferences.UserDataSource
import com.ns.mad_p2.data.repositories.users.UserRepository
import com.ns.mad_p2.data.repositories.users.UserRepositoryImpl
import com.ns.mad_p2.presentation.viewmodel.UserViewModel

val userModule = module {

    viewModel { UserViewModel(userRepository = get()) }

    single<UserRepository> { UserRepositoryImpl(userDataSource = get(named("shared"))) }

    single<UserDataSource>(named("shared")) { SPUserDataSource(sharedPreferences = get()) }

}