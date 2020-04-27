package com.darkknight.base.ui.spash

import com.darkknight.base.ui.base.BaseViewModel
import com.darkknight.base.util.State
import com.darkknight.base.util.ui
import kotlinx.coroutines.delay

/**
 *
 * Created by Rooparsh Kalia on 07/03/20
 *
 **/
class SplashViewModel : BaseViewModel() {

    companion object {
        private const val DELAY_TIME_IN_MILLIS = 3_000L
    }

    fun startApp() {
        launch {
            ui {
                mViewState.postValue(State.LoadingState)
                delay(DELAY_TIME_IN_MILLIS)
                mViewState.postValue(State.SuccessState())
            }
        }
    }
}