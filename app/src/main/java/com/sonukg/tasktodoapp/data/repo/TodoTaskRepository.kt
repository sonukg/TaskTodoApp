package com.sonukg.tasktodoapp.data.repo

import android.app.Application
import androidx.lifecycle.LiveData
import com.sonukg.tasktodoapp.data.dao.TaskTodoDao
import com.sonukg.tasktodoapp.data.db.TaskTodoDatabase
import com.sonukg.tasktodoapp.data.model.TaskTodo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class TodoTaskRepository(application: Application) {
    private val todoDao:TaskTodoDao
    private val allTodos: LiveData<List<TaskTodo>>

    init {
        val database=TaskTodoDatabase.getDatabase(application.applicationContext)
        todoDao=database!!.TaskTodoDao()
        allTodos=todoDao.getAllTodos()
    }

    fun save(taskTodo:TaskTodo)= runBlocking {
        this.launch (Dispatchers.IO){
            todoDao.insert(taskTodo)
        }
    }

    fun update(taskTodo: TaskTodo) = runBlocking {
        this.launch(Dispatchers.IO){
            todoDao.update(taskTodo)
        }
    }

    fun delete(taskTodo: TaskTodo) = runBlocking {
        this.launch(Dispatchers.IO){
            todoDao.delete(taskTodo)
        }
    }

    fun getAllTodosList():LiveData<List<TaskTodo>>{
        return allTodos
    }
}