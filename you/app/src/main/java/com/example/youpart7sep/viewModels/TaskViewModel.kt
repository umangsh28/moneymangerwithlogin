package com.masai.taskmanagerapp.viewmodels


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.youpart7sep.database.Task

import com.masai.taskmanagerapp.models.remote.NetworkP.Resource
import com.masai.taskmanagerapp.models.remote.requests.LoginRequestModel
import com.masai.taskmanagerapp.models.remote.responses.LoginResponse
import com.masai.taskmanagerapp.repositary.TaskRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(private val repo:TaskRepo) : ViewModel() {

    fun userLogin(loginRequestModel: LoginRequestModel) : LiveData<Resource<LoginResponse>>{

        return liveData(Dispatchers.IO) {
           val result = repo.login(loginRequestModel)
            emit(result)
        }
    }

    fun addTask(task: Task){
        repo.addTaskToRoom(task)
    }


    fun getTasks(): LiveData<List<Task>> {
       return repo.getAllTasks()
    }

    fun getTaskFromAPI() {
         repo.getAllTasks()
    }

    fun updateTask(task: Task){
         repo.updateTask(task)
    }

    fun deleteTask(task: Task){
         repo.deleteTask(task)
    }

}