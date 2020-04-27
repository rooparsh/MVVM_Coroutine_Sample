package com.darkknight.base.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.darkknight.base.R
import com.darkknight.base.databinding.ActivityMainBinding
import com.darkknight.base.ui.base.BaseActivity
import com.darkknight.base.util.getText
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.circularreveal.CircularRevealLinearLayout
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    private val mBottomSheetBehavior: BottomSheetBehavior<CircularRevealLinearLayout> by lazy {
        BottomSheetBehavior.from(getBinding().layoutBottomSheet)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override val mViewModel: MainViewModel by viewModel()

    override fun layoutId(): Int = R.layout.activity_main

    companion object {
        fun openMainScreen(callingActivity: Context) =
            Intent(callingActivity, MainActivity::class.java)
    }

    override fun onBackPressed() {
        if (mBottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED) {
            mBottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        } else {
            super.onBackPressed()
        }
    }

    override fun addObserver() {
        getViewModel().getExpandedValue().observe(this, Observer {
            it?.let {
                GlobalScope.launch {
                    delay(500)
                    mBottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                }
            } ?: kotlin.run { mBottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED }
        })


        getViewModel().getOnBoardUser().observe(this, Observer {
            it?.let { isRegisterFlow ->
                if (isRegisterFlow) {
                    val name: String
                    val email: String
                    val password: String
                    val phone: String
                    with(getBinding()) {
                        name = tiName.getText()
                        email = tiEmail.getText()
                        password = tiPassword.getText()
                        phone = tiPhone.getText()
                    }

                    getViewModel().registerUser(name, email, password, phone)
                } else {
                    val email: String
                    val password: String
                    with(getBinding()) {
                        email = tiEmail.getText()
                        password = tiPassword.getText()
                    }

                    getViewModel().loginUser(email, password)
                }
            }
        })

        getViewModel().getLoginField().observe(this, Observer {
            it?.let { isValid ->
                if (isValid) {
                    val email: String
                    val password: String
                    with(getBinding()) {
                        email = tiEmail.getText()
                        password = tiPassword.getText()
                    }
                    getViewModel().loginUser(email, password)
                }
            }
        })
    }

}


