package com.mpg.assignmentkotlin.presentation.view.activities

import androidx.lifecycle.Observer
import com.mpg.assignmentkotlin.R
import com.mpg.assignmentkotlin.app.TodoDemoApp
import com.mpg.assignmentkotlin.base.BaseActivity
import com.mpg.assignmentkotlin.databinding.ActivityMainBinding
import com.mpg.assignmentkotlin.presentation.view.adapter.TodoListAdapter
import com.mpg.assignmentkotlin.presentation.viewmodel.MainViewModel
import com.mpg.assignmentkotlin.presentation.viewmodel.ViewModelFactory
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList
import kotlin.math.max

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
        var abc = arrayOf(intArrayOf(1,3),intArrayOf(8,10),intArrayOf(15,18), intArrayOf(2,6))
        println("Before \n")
        abc.forEach{it.forEach(System.out::println)}
        abc = merge(abc)
        println("After \n")
        abc.forEach{it.forEach(System.out::println)}
    }

    private inner class IntervalComparator : Comparator<IntArray?> {
        override fun compare(a: IntArray?, b: IntArray?): Int {
            return if (a == null || a.isEmpty() || b == null || b.isEmpty())
                0
            else if (a[0] < b[0])
                -1
            else if (a[0] == b[0])
                0
            else 1
        }

    }

    private fun merge(intervals: Array<IntArray>): Array<IntArray> {
        intervals.sortWith(IntervalComparator())
        //Collections.sort(Arrays.asList(intervals), IntervalComparator())
        val merged: LinkedList<IntArray> = LinkedList()
        val aaa: ArrayList<IntArray> = ArrayList()
        for (interval in intervals) {
            // if the list of merged intervals is empty or if the current
            // interval does not overlap with the previous, simply append it.
            if (merged.isEmpty() || merged.last[1] < interval[0]) {
                merged.add(interval)
                aaa.add(interval)
            } else {
                aaa.last()[1]= max(aaa.last()[1],interval[1])
                merged.last[1] =
                    max(merged.last[1], interval[1])
            }
        }
        merged.size
        //println(arrayOfNulls(merged.size))
//        return merged.toArray(arrayOfNulls(merged.size))
        return aaa.toTypedArray()
    }
}
