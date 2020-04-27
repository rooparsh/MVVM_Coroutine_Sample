package com.darkknight.base.ui.spash

import android.os.Bundle
import androidx.lifecycle.Observer
import com.darkknight.base.R
import com.darkknight.base.databinding.ActivitySplashBinding
import com.darkknight.base.ui.base.BaseActivity
import com.darkknight.base.ui.main.MainActivity
import com.darkknight.base.util.State
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getViewModel().startApp()
    }

    override val mViewModel: SplashViewModel by viewModel()

    override fun layoutId(): Int = R.layout.activity_splash

    override fun addObserver() {
        getViewModel().getViewState().observe(this, Observer {
            it?.let { state ->
                when (state) {
                    State.LoadingState -> showLoading(true)
                    is State.SuccessState -> {
                        showLoading(false)
                        startActivity(MainActivity.openMainScreen(this))
                        finish()
                    }
                    is State.FailedState -> showLoading(false)
                }
            }
        })
    }

    private fun showLoading(toShow: Boolean) {
        if (toShow) {
            getBinding().progressBar.show()
        } else {
            getBinding().progressBar.hide()
        }
    }
}


