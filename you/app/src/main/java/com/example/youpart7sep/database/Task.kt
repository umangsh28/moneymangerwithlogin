package com.example.youpart7sep.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tasks" )
data class Task (@ColumnInfo(name = "income")
                 var income : Int,
                 @ColumnInfo(name = "Exp")
                 var Exp:Int)
{


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    var ID :Int?=null


}


