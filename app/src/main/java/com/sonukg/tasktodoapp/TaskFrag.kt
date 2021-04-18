package com.sonukg.tasktodoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sonukg.tasktodoapp.`interface`.RecycleViewDeleteListener
import com.sonukg.tasktodoapp.`interface`.RecyclerViewClickListener
import com.sonukg.tasktodoapp.data.adapter.TaskTodoAdapter
import com.sonukg.tasktodoapp.data.adapter.UpcomingTaskTodoAdapter
import com.sonukg.tasktodoapp.data.model.TaskTodo
import com.sonukg.tasktodoapp.databinding.TaskFragmentBinding
import com.sonukg.tasktodoapp.viewmodel.AddTaskViewModel
import com.sonukg.tasktodoapp.viewmodel.AddTaskViewModelFactory


class TaskFrag : Fragment() {
    lateinit var binding: TaskFragmentBinding
    private lateinit var viewModel: AddTaskViewModel
    private lateinit var taskTodoAdapter: TaskTodoAdapter
    private lateinit var upcomingTaskTodoAdapter: UpcomingTaskTodoAdapter
    private var todoList: ArrayList<TaskTodo> = arrayListOf()
    //private val listener: RecyclerViewClickListener<TaskTodo> = this
    //private val deleteListener: RecycleViewDeleteListener<TaskTodo> = this
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = TaskFragmentBinding.inflate(inflater, container, false)
        val addTodoViewModelFactory= AddTaskViewModelFactory(requireActivity().application)
        viewModel= ViewModelProvider(requireActivity(), addTodoViewModelFactory).get(AddTaskViewModel::class.java)
        setUpRecycler()
        setUpRecyclerTwo()
        setObservable()
        setObservableTwo()
        binding.addTodo.setOnClickListener {
            requireActivity().supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace<AddTask>(com.sonukg.tasktodoapp.R.id.fragment_container_view)
            }
        }
        return binding.root
    }

    private fun setObservable() {
        viewModel.getTodoList().observe(requireActivity(), Observer { todos ->
            taskTodoAdapter.todos = todos
            taskTodoAdapter.notifyDataSetChanged()
        })
    }
    private fun setObservableTwo() {
        viewModel.getTodoList().observe(requireActivity(), Observer { todos ->
            upcomingTaskTodoAdapter.todos = todos
            upcomingTaskTodoAdapter.notifyDataSetChanged()
        })
    }

    private fun setUpRecycler()= binding.todayTaskListRv.apply {requireActivity()
        taskTodoAdapter= TaskTodoAdapter()
        adapter=taskTodoAdapter
        //layoutManager = LinearLayoutManager(requireActivity())
        setLayoutManager(LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, true))
    }
    private fun setUpRecyclerTwo()= binding.upcomingTaskListRv.apply {requireActivity()
        upcomingTaskTodoAdapter= UpcomingTaskTodoAdapter()
        adapter=upcomingTaskTodoAdapter
        layoutManager = LinearLayoutManager(requireActivity())
        //setLayoutManager(LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, true))
    }

    /*override fun onClick(`object`: TaskTodo, position: Int) {
        (requireActivity() as MainActivity).loaddataFragment(`object`)
    }*/

    /*override fun onDeleteClick(`object`: TaskTodo, position: Int) {
        viewModel.deleteTodo(`object`)
        upcomingTaskTodoAdapter.notifyDataSetChanged()
    }*/
}