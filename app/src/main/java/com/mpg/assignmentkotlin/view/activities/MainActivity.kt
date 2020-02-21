package com.mpg.assignmentkotlin.view.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mpg.assignmentkotlin.R

import com.mpg.assignmentkotlin.TodoDemoApp
import com.mpg.assignmentkotlin.data.model.NetworkState
import com.mpg.assignmentkotlin.databinding.ActivityMainBinding

import com.mpg.assignmentkotlin.view.adapter.TodoListAdapter
import com.mpg.assignmentkotlin.viewmodel.MainViewModel
import com.mpg.assignmentkotlin.viewmodel.ViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: MainViewModel
    private lateinit var viewBinding: ActivityMainBinding
    private val listAdapter = TodoListAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val demoApp: TodoDemoApp = application as TodoDemoApp
        demoApp.appComponent.inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        viewBinding.recyclerView.adapter = listAdapter

        viewModel.getTodoList()

        viewModel.todoListData.observe(this, Observer {
            listAdapter.submitList(it)
        })

        viewModel.networkState.observe(this, Observer {

            when (it) {
                NetworkState.START -> {
                    viewBinding.recyclerView.visibility = View.GONE
                    viewBinding.errorMsg.visibility = View.GONE
                    viewBinding.dataLookingUp.show()
                }
                NetworkState.SUCCESS -> {
                    viewBinding.dataLookingUp.hide()
                    viewBinding.recyclerView.visibility = View.VISIBLE
                    viewBinding.errorMsg.visibility = View.GONE
                }
                NetworkState.ERROR -> {
                    viewBinding.dataLookingUp.hide()
                    viewBinding.recyclerView.visibility = View.GONE
                    viewBinding.errorMsg.visibility = View.VISIBLE
                }
            }

        })

        viewBinding.errorMsg.setOnClickListener { viewModel.getTodoList() }
    }
}
