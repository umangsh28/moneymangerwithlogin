package com.masai.taskmanagerapp.repositary






import androidx.lifecycle.LiveData
import com.example.youpart7sep.database.Task
import com.example.youpart7sep.database.TaskappDAO
import com.masai.taskmanagerapp.main.remote.Network
import com.masai.taskmanagerapp.models.remote.NetworkP.Resource
import com.masai.taskmanagerapp.models.remote.NetworkP.ResponseHandler
import com.masai.taskmanagerapp.models.remote.TasksAPI
import com.masai.taskmanagerapp.models.remote.requests.LoginRequestModel
import com.masai.taskmanagerapp.models.remote.responses.LoginResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskRepo(val taskDAO: TaskappDAO) {


    private val token =
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2MGE0YmI3OTAzMjdlN2MwNmE2MTk1ODYiLCJpYXQiOjE2MzIxMzg2ODR9.cTxpYQrTfvramIOSPih6b1hJO_x1G-V2GmaRnTYSjU0"



    private val api: TasksAPI =Network.retrofit.create(TasksAPI::class.java)
    private val responseHandler= ResponseHandler()

    suspend fun login (loginRequestModel: LoginRequestModel): Resource<LoginResponse> {
        return try {
            val response:LoginResponse=api.login(loginRequestModel)
            return responseHandler.handleSuccess(response)

        }catch (e:Exception){
            responseHandler.handleException(e)
        }

    }

    suspend fun getRemoteTasks(){
        CoroutineScope(Dispatchers.IO).launch {
            val response: GetTasksResponseModel = api.getTasksFromAPI(token =token)

            saveToDB(response)
        }
    }

    private fun saveToDB(response: GetTasksResponseModel) {
        val listoftask=ArrayList<Task>()
        response.forEach{
            val newTask=Task(it.title!!.toInt()!!,it.description!!.toInt()!!)
            listoftask.add(newTask)
        }
        taskDAO.deleteall()

    }

    fun addTaskToRoom(task: Task){
        CoroutineScope(Dispatchers.IO).launch {
            taskDAO.addTask(task)
        }
    }


    fun getAllTasks(): LiveData<List<Task>>{

        return taskDAO.getTasks()

    }

    fun updateTask(task: Task){

        CoroutineScope((Dispatchers.IO)).launch {
             taskDAO.updateTask(task)
        }

    }

    fun deleteTask(task: Task){

        CoroutineScope((Dispatchers.IO)).launch {
            taskDAO.delete(task)
        }

    }





}