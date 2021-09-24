package com.example.youpart7sep.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youpart7sep.R
import com.example.youpart7sep.database.Task
import com.example.youpart7sep.database.Onitemclick
import com.example.youpart7sep.database.TaskRoomDatabase
import com.example.youpart7sep.database.TaskappDAO
import com.example.youpart7sep.main.DatabaseHandler
import kotlinx.android.synthetic.main.fragment_first_page.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class FirstPage : AppCompatActivity(), Onitemclick {

    lateinit var roomDb: TaskRoomDatabase

    lateinit var taskDao: TaskappDAO


    lateinit var taskAdapter: TasksAdapter
    private var tasksList = mutableListOf<Task>()

    val dbHandler = DatabaseHandler(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_first_page)
        roomDb = TaskRoomDatabase.getDatabaseObject(this)
        taskDao = roomDb.getTaskDAO()


        var Income = intent.getIntExtra("IncText", 9)

        var Expe = intent.getIntExtra("ExpText", 4)

        var Name1=intent.getStringExtra("NameET")



//        tvbalance.setText(sub)


        tasksList.addAll(dbHandler.getAllTasks())



        taskAdapter = TasksAdapter(this, tasksList, this)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = taskAdapter

        taskDao.getTasks().observe(this, Observer {
            val tasks = it
            tasksList.clear()
            tasksList.addAll(tasks)
            taskAdapter.notifyDataSetChanged()


        })



    }

    override fun onEditClicked(task: Task) {

        val newIncome = 0
        val newExpense = 0

        task.income = newIncome
        task.Exp = newExpense

        CoroutineScope(Dispatchers.IO).launch {
            taskDao.updateTask(task)
        }


    }

    override fun onDeleteClicked(task: Task) {
        CoroutineScope(Dispatchers.IO).launch {
            taskDao.delete(task)
        }
    }


}









