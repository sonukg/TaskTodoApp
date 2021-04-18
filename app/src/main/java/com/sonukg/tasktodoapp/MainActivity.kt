package com.sonukg.tasktodoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.sonukg.tasktodoapp.data.model.TaskTodo

class MainActivity : AppCompatActivity() {
    var editFrag=EditTask()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<TaskFrag>(R.id.fragment_container_view)
            }
        }
    }
    fun loaddataFragment(todoDetails: TaskTodo) {
        val bundle = Bundle().apply {
            putParcelable(MainActivity.TODO_DETAILS, todoDetails)
        }
        editFrag.arguments = bundle
        supportFragmentManager.beginTransaction().replace(R.id.container, editFrag).commit()
    }

    companion object {
        const val TODO_DETAILS = "todo_details"
    }
}