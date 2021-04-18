package com.sonukg.tasktodoapp.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sonukg.tasktodoapp.data.model.TaskTodo
import com.sonukg.tasktodoapp.databinding.TodayTaskItemBinding

class TaskTodoAdapter: RecyclerView.Adapter<TaskTodoAdapter.ViewHolder>() {
    private var taskTodoList: MutableList<TaskTodo> = mutableListOf()

    inner class ViewHolder(val binding: TodayTaskItemBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<TaskTodo>() {
        override fun areItemsTheSame(oldItem: TaskTodo, newItem: TaskTodo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TaskTodo, newItem: TaskTodo): Boolean {
            return oldItem.id == newItem.id
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var todos: List<TaskTodo>
        get() = differ.currentList
        set(value) { differ.submitList(value) }

    override fun getItemCount(): Int = todos.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TodayTaskItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.apply {
            val taskTodo = todos[position]
           taskTodayName.setText(taskTodo.name)
            taskTodayTime.setText(taskTodo.todoDate)
        }
    }
}