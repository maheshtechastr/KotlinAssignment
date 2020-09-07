package com.mpg.assignmentkotlin.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mpg.assignmentkotlin.base.BaseViewModel
import com.mpg.assignmentkotlin.data.model.NetworkResult

import com.mpg.assignmentkotlin.data.model.TodoModel
import com.mpg.assignmentkotlin.data.remote.TodoListRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val todoListRepository: TodoListRepository) :
    BaseViewModel() {

    val todoListData: MutableLiveData<List<TodoModel>> = MutableLiveData()
    val isError: MutableLiveData<Boolean> = MutableLiveData()


    fun getTodoList() {
        viewModelScope.launch {
            loading.postValue(true)
            isError.postValue(false)
            when (val result = todoListRepository.getTodoList()) {


                is NetworkResult.Success -> {
                    loading.postValue(false)
                    isError.postValue(false)
                    todoListData.postValue(result.data)
                }
                is NetworkResult.Error -> {
                    loading.postValue(false)
                    isError.postValue(true)
                }
            }
        }
    }

}