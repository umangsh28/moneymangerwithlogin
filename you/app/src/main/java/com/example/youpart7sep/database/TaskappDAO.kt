package com.example.youpart7sep.database

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface TaskappDAO {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTask(task: Task)




    @Query("select * from tasks")
    fun getTasks(): LiveData<List<Task>>

    @Update
    fun updateTask(task: Task)

    @Delete
    fun delete(task: Task)


    @Query("delete FROM tasks")
    fun deleteall()



}