package com.sonukg.tasktodoapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sonukg.tasktodoapp.data.model.TaskTodo
import com.sonukg.tasktodoapp.databinding.FragmentEditTaskBinding
import com.sonukg.tasktodoapp.databinding.TaskFragmentBinding

class EditTask : Fragment() {
    private var todoDetails: TaskTodo?=null
    lateinit var binding:FragmentEditTaskBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentEditTaskBinding.inflate(inflater, container, false)
        arguments?.let { args ->
            todoDetails = args[TODO_DETAILS] as TaskTodo
            Log.e(this@EditTask::class.java.simpleName, "onCreateView: $todoDetails")
        }
        binding.taskNameText.setText(todoDetails?.name)
        binding.taskDescText.setText(todoDetails?.desc)
        binding.taskDateText.setText(todoDetails?.todoDate)
        binding.spinnerText.setText(todoDetails?.category)
        binding.imageButton.setOnClickListener {
            binding.spinnerCategory.visibility=View.VISIBLE
            binding.spinnerText.visibility=View.GONE
            binding.imageButton.visibility=View.GONE
        }
        return binding.root
    }
    companion object {
        const val TODO_DETAILS = "todo_details"
    }
}