package com.darkknight.base

import android.app.Application
import com.darkknight.base.di.appModule
import com.darkknight.base.di.networkModule
import com.darkknight.base.di.validationModule
import com.darkknight.base.di.viewModelModule
import com.darkknight.base.util.ThemeHelper
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 *
 * Created by Rooparsh Kalia on 2020-02-23
 *
 **/
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ThemeHelper.applyTheme(ThemeHelper.Theme.SYSTEM_THEME)
        startKoin {
            androidLogger()
            androidContext(this@BaseApplication)
            modules(listOf(appModule, networkModule, viewModelModule, validationModule))
        }
    }
}