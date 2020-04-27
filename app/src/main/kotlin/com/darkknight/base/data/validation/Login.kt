package com.darkknight.base.data.validation

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import com.darkknight.base.BR
import com.darkknight.base.R


/**
 *
 * Created by Rooparsh Kalia on 12/04/20
 *
 **/

data class LoginField(var email: String? = null, var password: String? = null)
class LoginErrorField(var email: Int? = null, var password: Int? = null)

data class OnBoardingField(
    var email: String? = null,
    var password: String? = null,
    var name: String? = null,
    var phone: String? = null
)

class OnBoardingErrorField(
    var email: Int? = null,
    var password: Int? = null,
    var name: String? = null,
    var phone: String? = null
)

class LoginForm : BaseObservable() {
    private val field = LoginField()
    private val errorField = LoginErrorField()

    private val isValid = MutableLiveData<Boolean>()


    @Bindable
    fun isValid(): Boolean {
        var valid: Boolean = isEmailValid(false)
        valid = isPasswordValid(false) && valid
        notifyPropertyChanged(BR.emailError)
        notifyPropertyChanged(BR.passwordError)
        return valid
    }


    fun isEmailValid(setMessage: Boolean): Boolean {
        // Minimum a@b.c
        val email: String? = field.email
        if (email != null && email.length > 5) {
            val indexOfAt = email.indexOf("@")
            val indexOfDot = email.lastIndexOf(".")
            return if (indexOfAt in 1 until indexOfDot && indexOfDot < email.length - 1) {
                errorField.email = null
                notifyPropertyChanged(BR.valid)
                true
            } else {
                if (setMessage) {
                    errorField.email = R.string.error_format_invalid
                    notifyPropertyChanged(BR.valid)
                }
                false
            }
        }
        if (setMessage) {
            errorField.email = R.string.error_too_short
            notifyPropertyChanged(BR.valid)
        }
        return false
    }

    fun isPasswordValid(setMessage: Boolean): Boolean {
        val password: String? = field.password
        return if (password != null && password.length > 5) {
            errorField.password = null
            notifyPropertyChanged(BR.valid)
            true
        } else {
            if (setMessage) {
                errorField.password = R.string.error_too_short
                notifyPropertyChanged(BR.valid)
            }
            false
        }
    }

    fun onClick() {
        isValid.value = isValid()
    }

    fun getIsLoginDataValid(): MutableLiveData<Boolean> {
        return isValid
    }

    fun getField(): LoginField {
        return field
    }

    @Bindable
    fun getEmailError(): Int? {
        return errorField.email
    }

    @Bindable
    fun getPasswordError(): Int? {
        return errorField.password
    }
}

class OnBoardingForm : BaseObservable() {
    private val field = OnBoardingField()
    private val errorField = OnBoardingErrorField()
    private val isValid = MutableLiveData<Boolean>()


    @Bindable
    fun isValid(): Boolean {
        val valid: Boolean = isEmailValid(false)
                && isPasswordValid(false)
                && isNameValid(false)
        notifyPropertyChanged(BR.emailError)
        notifyPropertyChanged(BR.passwordError)
        return valid
    }


    fun isEmailValid(setMessage: Boolean): Boolean {
        // Minimum a@b.c
        val email: String? = field.email
        if (email != null && email.length > 5) {
            val indexOfAt = email.indexOf("@")
            val indexOfDot = email.lastIndexOf(".")
            return if (indexOfAt in 1 until indexOfDot && indexOfDot < email.length - 1) {
                errorField.email = null
                notifyPropertyChanged(BR.valid)
                true
            } else {
                if (setMessage) {
                    errorField.email = R.string.error_format_invalid
                    notifyPropertyChanged(BR.valid)
                }
                false
            }
        }
        if (setMessage) {
            errorField.email = R.string.error_too_short
            notifyPropertyChanged(BR.valid)
        }
        return false
    }

    fun isPasswordValid(setMessage: Boolean): Boolean {
        val password: String? = field.password
        return if (password != null && password.length > 5) {
            errorField.password = null
            notifyPropertyChanged(BR.valid)
            true
        } else {
            if (setMessage) {
                errorField.password = R.string.error_too_short
                notifyPropertyChanged(BR.valid)
            }
            false
        }
    }

    fun isNameValid(setMessage: Boolean): Boolean {
        val name: String? = field.name

        return if (name != null && name.length > 5) {
            errorField.name = null
            notifyPropertyChanged(BR.valid)
            true
        } else {
            if (setMessage) {
                errorField.password = R.string.error_too_short
                notifyPropertyChanged(BR.valid)
            }
            false
        }
    }

    fun onClick() {
        isValid.value = isValid()
    }

    fun getIsLoginDataValid(): MutableLiveData<Boolean> {
        return isValid
    }

    fun getField(): OnBoardingField {
        return field
    }

    @Bindable
    fun getEmailError(): Int? {
        return errorField.email
    }

    @Bindable
    fun getPasswordError(): Int? {
        return errorField.password
    }
}