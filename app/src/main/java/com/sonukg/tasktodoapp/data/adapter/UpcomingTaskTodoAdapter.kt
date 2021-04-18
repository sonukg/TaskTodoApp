package com.sonukg.tasktodoapp.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sonukg.tasktodoapp.`interface`.RecycleViewDeleteListener
import com.sonukg.tasktodoapp.`interface`.RecyclerViewClickListener
import com.sonukg.tasktodoapp.data.model.TaskTodo
import com.sonukg.tasktodoapp.databinding.UpcomingTaskListBinding

class UpcomingTaskTodoAdapter: RecyclerView.Adapter<UpcomingTaskTodoAdapter.ViewHolder>() {
//private val listener: RecyclerViewClickListener<TaskTodo>
    private var taskTodoList: MutableList<TaskTodo> = mutableListOf()

    inner class ViewHolder(val binding: UpcomingTaskListBinding) : RecyclerView.ViewHolder(binding.root)

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
        val binding = UpcomingTaskListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.apply {
            val taskTodo = todos[position]
            taskUpcomingName.setText(taskTodo.name)
            taskUpcomingTime.setText(taskTodo.todoDate)
            taskUpcomingCategory.setText(taskTodo.category)
            /*delete.setOnClickListener {
                deleteListener.onDeleteClick(taskTodo, position)
            }*/
        }
       /* holder.itemView.setOnClickListener {
            listener.onClick(taskTodo, position)
        }*/
    }
}