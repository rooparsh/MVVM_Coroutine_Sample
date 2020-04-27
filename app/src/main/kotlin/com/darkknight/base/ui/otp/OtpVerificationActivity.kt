package com.darkknight.base.ui.otp

import android.os.Bundle
import com.darkknight.base.R
import com.darkknight.base.databinding.ActivityOtpVerificationBinding
import com.darkknight.base.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class OtpVerificationActivity : BaseActivity<ActivityOtpVerificationBinding, OtpViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override val mViewModel: OtpViewModel by viewModel()

    override fun addObserver() {
    }

    override fun layoutId(): Int = R.layout.activity_otp_verification
}
