package com.darkknight.base.util

/**
 *
 * Created by Rooparsh Kalia on 2020-02-23
 *
 **/
sealed class State {
    object LoadingState : State()
    data class SuccessState(val data: Any? = null) : State()
    data class FailedState(val code: Int? = null, val message: String? = null) : State()
}