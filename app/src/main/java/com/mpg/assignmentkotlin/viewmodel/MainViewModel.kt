package com.mpg.assignmentkotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mpg.assignmentkotlin.data.source.TodoListRepository
import com.mpg.assignmentkotlin.data.model.NetworkState
import com.mpg.assignmentkotlin.data.model.TodoModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val todoListRepository: TodoListRepository) :
    ViewModel() {

    var todoListData: MutableLiveData<List<TodoModel>> = MutableLiveData()
    var networkState: MutableLiveData<NetworkState> = MutableLiveData()

    fun getTodoList() {
        networkState.value = NetworkState.START
        viewModelScope.launch {
            try {
                val result = todoListRepository.getTodo()
                networkState.postValue(NetworkState.SUCCESS)
                todoListData.postValue(result)
            } catch (e: Exception) {
                networkState.postValue(NetworkState.ERROR)
            }

        }

    }
}