package com.darkknight.base.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.darkknight.base.BR
import com.darkknight.base.util.State
import com.darkknight.base.util.showToast

/**
 *
 * Created by Rooparsh Kalia on 2020-02-23
 *
 **/
abstract class BaseActivity<B : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity(),
    LifecycleOwner {

    private lateinit var mBinding: B

    protected abstract val mViewModel: VM

    protected abstract fun addObserver()

    fun getViewModel() = mViewModel

    fun getBinding() = mBinding

    @LayoutRes
    protected abstract fun layoutId(): Int

    private fun bindContentView(layoutId: Int) {
        mBinding = DataBindingUtil.setContentView(this, layoutId)
        mBinding.setVariable(BR.viewModel, mViewModel)
        mBinding.lifecycleOwner = this
        mBinding.executePendingBindings()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindContentView(layoutId())
        addDefaultObserver()
        addObserver()
    }

    private fun addDefaultObserver() {
        getViewModel().getViewState().observe(this, Observer {
            it?.let { state ->
                when (state) {
                    is State.FailedState -> showToast(state.message.orEmpty())
                }
            }
        })
    }
}