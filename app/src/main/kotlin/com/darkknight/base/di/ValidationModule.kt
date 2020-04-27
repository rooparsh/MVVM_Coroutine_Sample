package com.darkknight.base.di

import com.darkknight.base.data.validation.LoginForm
import org.koin.dsl.module

/**
 *
 * Created by Rooparsh Kalia on 12/04/20
 *
 **/

val validationModule = module {
    factory { LoginForm() }
}