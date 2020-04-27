package com.darkknight.base.di

import com.darkknight.base.ui.main.MainViewModel
import com.darkknight.base.ui.otp.OtpViewModel
import com.darkknight.base.ui.spash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


/**
 *
 * Created by Rooparsh Kalia on 2020-02-23
 *
 **/

val viewModelModule = module {
    viewModel { OtpViewModel() }
    viewModel { MainViewModel(get(), get()) }
    viewModel { SplashViewModel() }
}