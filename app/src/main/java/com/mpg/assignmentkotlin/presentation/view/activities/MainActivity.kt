package com.mpg.assignmentkotlin.presentation.view.activities

import androidx.lifecycle.Observer
import com.mpg.assignmentkotlin.R
import com.mpg.assignmentkotlin.app.TodoDemoApp
import com.mpg.assignmentkotlin.base.BaseActivity
import com.mpg.assignmentkotlin.databinding.ActivityMainBinding
import com.mpg.assignmentkotlin.presentation.view.adapter.TodoListAdapter
import com.mpg.assignmentkotlin.presentation.viewmodel.MainViewModel
import com.mpg.assignmentkotlin.presentation.viewmodel.ViewModelFactory
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val listAdapter: TodoListAdapter by lazy {
        TodoListAdapter()
    }

    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun viewModelFactory(): ViewModelFactory {
        return viewModelFactory
    }
    override fun viewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun beforeCreate() {
        val demoApp: TodoDemoApp = application as TodoDemoApp
        demoApp.appComponent.inject(this)
    }

    override fun onCreate() {
        viewBinding.recyclerView.adapter = listAdapter

        viewModel.getTodoList()

        viewModel.todoListData.observe(this, Observer {
            listAdapter.submitList(it)
        })
        viewBinding.lifecycleOwner = this
        viewBinding.viewModel = viewModel

        viewBinding.errorMsg.setOnClickListener { viewModel.getTodoList() }
    }

}
