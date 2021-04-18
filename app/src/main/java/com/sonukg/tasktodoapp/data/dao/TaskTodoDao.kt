package com.sonukg.tasktodoapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sonukg.tasktodoapp.data.model.TaskTodo

@Dao
interface TaskTodoDao {
    @Insert
    suspend fun insert(todo: TaskTodo)

    @Update
    suspend fun update(todo: TaskTodo)

    @Delete
    suspend fun delete(todo: TaskTodo)

    @Query("SELECT * from todo ORDER BY id DESC")
    fun getAllTodos() : LiveData<List<TaskTodo>>

}