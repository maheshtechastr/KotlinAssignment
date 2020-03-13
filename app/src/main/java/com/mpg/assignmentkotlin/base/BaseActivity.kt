package com.mpg.assignmentkotlin.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mpg.assignmentkotlin.presentation.viewmodel.ViewModelFactory

abstract class BaseActivity<VB : ViewDataBinding, VM : ViewModel> : AppCompatActivity() {

    lateinit var viewModel: VM
    lateinit var viewBinding: VB

    abstract fun layoutId(): Int
    abstract fun viewModel(): Class<VM>
    abstract fun onCreate()
    abstract fun viewModelFactory():ViewModelFactory
    abstract fun beforeCreate()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        beforeCreate()
        viewBinding = DataBindingUtil.setContentView(this, layoutId())

        viewModel = ViewModelProvider(this, viewModelFactory()).get(viewModel())

        onCreate()
    }

}