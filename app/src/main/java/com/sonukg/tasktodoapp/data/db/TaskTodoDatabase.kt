package com.sonukg.tasktodoapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sonukg.tasktodoapp.data.Converters
import com.sonukg.tasktodoapp.data.dao.TaskTodoDao
import com.sonukg.tasktodoapp.data.model.TaskTodo

@Database(entities = [TaskTodo::class],version = 1)
//@TypeConverters(Converters::class)
abstract class TaskTodoDatabase:RoomDatabase(){
    abstract fun TaskTodoDao() : TaskTodoDao

    companion object{

        @Volatile
        private var todoRoomInstance:TaskTodoDatabase?=null

        internal fun getDatabase(context: Context) : TaskTodoDatabase?{
            if (todoRoomInstance==null){
                synchronized(TaskTodoDatabase::class.java){
                    todoRoomInstance=Room.databaseBuilder(
                        context.applicationContext,
                        TaskTodoDatabase::class.java, "todo_db"
                    ).build()
                }
            }
            return todoRoomInstance
        }
    }
}