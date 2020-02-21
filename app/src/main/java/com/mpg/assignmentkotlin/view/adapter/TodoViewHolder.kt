package com.mpg.assignmentkotlin.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mpg.assignmentkotlin.R
import com.mpg.assignmentkotlin.data.model.TodoModel
import com.mpg.assignmentkotlin.databinding.TodoListItemBinding

class TodoViewHolder(private val itemBinding: TodoListItemBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(delivery: TodoModel?) {
        itemBinding.viewModel = delivery
    }

    companion object {
        fun create(parent: ViewGroup): TodoViewHolder {
            //list_item layout inflated
            val binding =
                DataBindingUtil.inflate<TodoListItemBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.todo_list_item,
                    parent,
                    false
                )
            return TodoViewHolder(binding)
        }
    }
}
