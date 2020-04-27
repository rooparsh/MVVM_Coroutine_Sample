package com.darkknight.base.ui.base

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darkknight.base.util.State
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent

/**
 *
 * Created by Rooparsh Kalia on 2020-02-23
 *
 **/
abstract class BaseViewModel : ViewModel(),
    LifecycleObserver, KoinComponent {

    protected val mViewState = MutableLiveData<State>()

    var jobs = mutableListOf<Job>()

    fun launch(code: suspend CoroutineScope.() -> Unit) {
        jobs.add(
            viewModelScope.launch {
                code.invoke(this)
            }
        )
    }

    fun getViewState() = mViewState

    override fun onCleared() {
        super.onCleared()
        jobs.forEach { it.cancel() }
    }

}