package com.example.youpart7sep.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.youpart7sep.database.Onitemclick
import com.example.youpart7sep.R
import com.example.youpart7sep.database.Task

class TasksAdapter(val context: Context, val taskList:MutableList<Task>, var listener: Onitemclick)
    : RecyclerView.Adapter<TasksAdapter.TaskViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
       val inflater=LayoutInflater.from(context)
       val view1:View=inflater.inflate(R.layout.task_item_row,parent,false)
       return TaskViewHolder(view1)

    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {

        val task=taskList[position]

        holder.title.text= task.income.toString()

        holder.balance.text= (task.income-task.Exp).toString()

        holder.desc.text= task.Exp.toString()





        holder.delete.setOnClickListener {

            listener.onDeleteClicked(task)
        }



    }

    override fun getItemCount(): Int {

       return taskList.size

    }








    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var title : TextView = itemView.findViewById(R.id.tvTaskTitle)

        var desc:TextView = itemView.findViewById(R.id.tvDesc)

        var balance:TextView=itemView.findViewById(R.id.tvbalance)


        var delete:TextView = itemView.findViewById(R.id.deleteTv)


    }


}