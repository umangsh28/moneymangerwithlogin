package com.example.mystore.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mystore.Database.DatabaseHandler
import com.example.mystore.LiveDataApp
import com.example.mystore.R
import com.example.mystore.RecyclerView.Item
import com.example.mystore.RecyclerView.ItemAdapter
import kotlinx.android.synthetic.main.fragment_search_item.*


class SearchItemFragment : Fragment(R.layout.fragment_search_item) {

    lateinit var adapter: ItemAdapter
    var list = mutableListOf<Item>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dbHandler = context?.let { DatabaseHandler(it) }

        val data=LiveDataApp()

        btnSearch.setOnClickListener {

            list.clear()

            list.addAll(dbHandler!!.getItem(etSearch.text.toString()))
            data.getData(list.size)
            adapter.notifyDataSetChanged()

        }

        adapter = ItemAdapter(list)
        var layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager
    }

}