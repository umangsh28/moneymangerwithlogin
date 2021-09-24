package com.example.youpart7sep.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Task::class],version=1)
abstract class TaskRoomDatabase : RoomDatabase() {

    abstract fun getTaskDAO(): TaskappDAO

    companion object {

        private var Instance: TaskRoomDatabase? = null


        fun getDatabaseObject(context: Context): TaskRoomDatabase {

            if (Instance == null) {

                val Builder = Room.databaseBuilder(
                    context.applicationContext,
                    TaskRoomDatabase::class.java,
                    "task_db"
                )
                Instance = Builder.build()
                return Instance!!
            } else {
                return Instance!!
            }
        }
    }

}