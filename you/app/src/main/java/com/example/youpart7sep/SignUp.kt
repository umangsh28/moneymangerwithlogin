package com.example.youpart7sep

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youpart7sep.adapter.FirstPage
import com.example.youpart7sep.adapter.MainActivity
import com.example.youpart7sep.adapter.TasksAdapter
import com.example.youpart7sep.database.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.masai.taskmanagerapp.models.remote.NetworkP.Status
import com.masai.taskmanagerapp.models.remote.requests.LoginRequestModel
import com.masai.taskmanagerapp.repositary.TaskRepo
import com.masai.taskmanagerapp.viewmodels.TaskViewModel
import com.masai.taskmanagerapp.viewmodels.TaskViewModelFactory
import kotlinx.android.synthetic.main.activity_login_page.*
import kotlinx.android.synthetic.main.fragment_first_page.*
import okhttp3.internal.userAgent
import org.jetbrains.anko.longToast

class SignUp : AppCompatActivity(){

    lateinit var taskAdapter: TasksAdapter
    private var tasksList = mutableListOf<Task>()

    lateinit var roomDb: TaskRoomDatabase


    lateinit var taskDao: TaskappDAO

    lateinit var viewModel: TaskViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        roomDb = TaskRoomDatabase.getDatabaseObject(this)
        taskDao = roomDb.getTaskDAO()
        val repo= TaskRepo(taskDao)
        val viewModelFactory= TaskViewModelFactory(repo)

        viewModel= ViewModelProviders.of(this,viewModelFactory)
            .get(TaskViewModel :: class.java)




        findViewById<FloatingActionButton>(R.id.Btns1).setOnClickListener { view ->

            val i1 = Intent(this, MainActivity::class.java)



            val loginRequestModel = LoginRequestModel(
                userName = "pradeep1706108@gmail.com",
                password = "dhankhar")

            val username:String=loginRequestModel.userName
            val password:String=loginRequestModel.password



            viewModel.userLogin(loginRequestModel).observe(this, Observer {
                val response = it

                when (response.status) {
                    Status.SUCCESS -> {
                        if(IncMail.text.contentEquals(username) && ExpPass.text.contentEquals(password))
                            startActivity(i1)
                        else {
                            Toast.makeText(this, "inavlid ID", Toast.LENGTH_LONG).show()
                            longToast("$username  and $password")
                        }


                    }
                    Status.ERROR -> {
                        val error = response.message!!
                        Toast.makeText(this, "error is $error", Toast.LENGTH_LONG).show()

                    }
                    Status.LOADING -> {
                        Toast.makeText(this, "Loading", Toast.LENGTH_LONG).show()
                    }
                }
            })
        }

    }
}

