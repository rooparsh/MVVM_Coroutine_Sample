package com.darkknight.base.util

import android.os.Build
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatDelegate

/**
 *
 * Created by Rooparsh Kalia on 07/03/20
 *
 **/

object ThemeHelper {

    enum class Theme {
        DARK_THEME,
        LIGHT_THEME,
        SYSTEM_THEME
    }

    fun applyTheme(@NonNull theme: Theme) {
        when (theme) {
            Theme.DARK_THEME -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            Theme.LIGHT_THEME -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            Theme.SYSTEM_THEME -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY);
                }
            }
        }
    }
}