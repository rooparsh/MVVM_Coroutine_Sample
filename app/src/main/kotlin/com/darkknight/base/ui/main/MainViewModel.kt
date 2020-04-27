package com.darkknight.base.ui.main

import android.view.View.OnFocusChangeListener
import android.widget.EditText
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.darkknight.base.data.validation.LoginForm
import com.darkknight.base.repository.OnBoardingRepository
import com.darkknight.base.ui.base.BaseViewModel
import com.darkknight.base.util.ResultWrapper
import com.darkknight.base.util.State
import com.darkknight.base.util.io


/**
 *
 * Created by Rooparsh Kalia on 2020-02-23
 *
 **/
class MainViewModel(
    private val loginForm: LoginForm,
    private val repository: OnBoardingRepository
) : BaseViewModel() {

    private val mRegisterFlow = ObservableField<Boolean>()
    private val mExpandBottomSheet = MutableLiveData<Boolean>()
    private val mOnBoardData = MutableLiveData<Boolean>()

    private var mOnFocusEmail: OnFocusChangeListener
    private var mOnFocusPassword: OnFocusChangeListener

    init {

        mOnFocusEmail = OnFocusChangeListener { view, focused ->
            val et = view as EditText
            if (et.text.isNotEmpty() && focused.not()) {
                loginForm.isEmailValid(true)
            }
        }

        mOnFocusPassword = OnFocusChangeListener { view, focused ->
            val et = view as EditText
            if (et.text.isNotEmpty() && focused.not()) {
                loginForm.isPasswordValid(true)
            }
        }
    }

    fun getIsRegisterFlow() = mRegisterFlow

    fun getExpandedValue() = mExpandBottomSheet

    fun getOnBoardUser() = mOnBoardData

    fun onBoard() {
        // loginForm.onClick()
        mOnBoardData.postValue(mRegisterFlow.get())
    }

    fun onLoginClick() {
        mRegisterFlow.set(false)
        mExpandBottomSheet.postValue(true)
    }

    fun onRegisterClick() {
        mRegisterFlow.set(true)
        mExpandBottomSheet.postValue(true)
    }

    fun registerUser(name: String, email: String, password: String, phone: String) {
        launch {
            io {
                mViewState.postValue(State.LoadingState)

                when (val response = repository.registerUser(name, email, phone, password)) {
                    is ResultWrapper.Success -> mViewState.postValue(State.SuccessState(response.value.data))
                    is ResultWrapper.GenericError -> with(response.error) {
                        mViewState.postValue(State.FailedState(statusCode, message))
                    }
                    is ResultWrapper.NetworkError -> mViewState.postValue(
                        State.FailedState(message = response.message)
                    )
                }
            }

        }
    }


    fun loginUser(email: String, password: String) {
        launch {
            io {
                mViewState.postValue(State.LoadingState)

                when (val response = repository.loginUser(email, password)) {
                    is ResultWrapper.Success -> mViewState.postValue(State.SuccessState(response.value.data))
                    is ResultWrapper.GenericError -> mViewState.postValue(
                        State.FailedState(response.error.statusCode, response.error.message)
                    )
                    is ResultWrapper.NetworkError -> mViewState.postValue(
                        State.FailedState()
                    )
                }
            }
        }
    }

    fun getLogin(): LoginForm? {
        return loginForm
    }

    fun getEmailOnFocusChangeListener(): OnFocusChangeListener? {
        return mOnFocusEmail
    }

    fun getPasswordOnFocusChangeListener(): OnFocusChangeListener? {
        return mOnFocusPassword
    }

    fun getLoginField(): MutableLiveData<Boolean> {
        return loginForm.getIsLoginDataValid()
    }
}