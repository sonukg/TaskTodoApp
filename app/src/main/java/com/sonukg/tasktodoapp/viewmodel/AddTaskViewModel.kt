package com.sonukg.tasktodoapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.sonukg.tasktodoapp.data.model.TaskTodo
import com.sonukg.tasktodoapp.data.repo.TodoTaskRepository

class AddTaskViewModel(application: Application) : AndroidViewModel(application) {
   private val todoTaskRepository:TodoTaskRepository=TodoTaskRepository(application)
    private val allTodoList:LiveData<List<TaskTodo>> = todoTaskRepository.getAllTodosList()

    fun saveTodo(todo:TaskTodo){
        todoTaskRepository.save(todo)
    }

    fun updateTodo(todo: TaskTodo){
        todoTaskRepository.update(todo)
    }

    fun deleteTodo(todo: TaskTodo){
        todoTaskRepository.delete(todo)
    }

    fun getTodoList():LiveData<List<TaskTodo>>{
        return allTodoList
    }
}