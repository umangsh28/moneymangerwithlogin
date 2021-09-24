package com.example.youpart7sep.adapter

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.youpart7sep.R
import com.example.youpart7sep.database.Task
import com.example.youpart7sep.database.Onitemclick
import com.example.youpart7sep.database.TaskRoomDatabase
import com.example.youpart7sep.database.TaskappDAO
import com.example.youpart7sep.main.DatabaseHandler
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_first_page.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), Onitemclick {


    lateinit var taskAdapter: TasksAdapter
    private var tasksList = mutableListOf<Task>()

    val dbHandler = DatabaseHandler(this)

    lateinit var roomDb: TaskRoomDatabase

    lateinit var taskDao: TaskappDAO


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        roomDb = TaskRoomDatabase.getDatabaseObject(this)
        taskDao = roomDb.getTaskDAO()



        findViewById<FloatingActionButton>(R.id.Btns).setOnClickListener {
            val i = Intent(this, FirstPage::class.java)




            val newTask = Task(Integer.parseInt(IncText.text.toString()),Integer.parseInt(ExpText.text.toString()))

            CoroutineScope(Dispatchers.IO).launch {
                taskDao.addTask(newTask)
            }
            startActivity(i)

        }

        tasksList.addAll(dbHandler.getAllTasks())

        taskAdapter = TasksAdapter(this, tasksList, this)
//        recyclerview.layoutManager = LinearLayoutManager(this)
//        recyclerview.adapter = taskAdapter




        taskDao.getTasks().observe(this@MainActivity, Observer {
            val tasks = it
            tasksList.clear()
            tasksList.addAll(tasks)
            taskAdapter.notifyDataSetChanged()


        })


    }

    override fun onEditClicked(task: Task) {
        TODO("Not yet implemented")
    }

    override fun onDeleteClicked(task: Task) {
        TODO("Not yet implemented")
    }
}
