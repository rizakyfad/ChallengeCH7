package com.rizaki.challengech6Binar.Module

import com.rizaki.challengech6Binar.ViewModel.HomeViewModel
import com.rizaki.challengech6Binar.ViewModel.LoginViewModel
import com.rizaki.challengech6Binar.ViewModel.ProfileViewModel
import com.rizaki.challengech6Binar.ViewModel.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get(), get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { HomeViewModel(get(), get()) }
    viewModel { ProfileViewModel(get(), get()) }
}