package com.mpg.assignmentkotlin.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.mpg.assignmentkotlin.R
import com.mpg.assignmentkotlin.data.model.TodoModel

class TodoListAdapter : ListAdapter<TodoModel, TodoViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return when (viewType) {
            R.layout.todo_list_item -> TodoViewHolder.create(parent)

            else -> throw IllegalStateException("UnKnown view type $viewType")
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.todo_list_item -> (holder as TodoViewHolder).bind(getItem(position))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            -1 -> position
            else ->
                R.layout.todo_list_item
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<TodoModel> =
            object : DiffUtil.ItemCallback<TodoModel>() {
                override fun areItemsTheSame(oldItem: TodoModel, newItem: TodoModel): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: TodoModel, newItem: TodoModel): Boolean {
                    return oldItem.id == newItem.id && oldItem.userId == newItem.userId
                            && oldItem.completed == newItem.completed && oldItem.title == newItem.title
                }
            }
    }
}