package com.example.mystore.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mystore.Database.DatabaseHandler
import com.example.mystore.R
import com.example.mystore.RecyclerView.Item
import kotlinx.android.synthetic.main.fragment_add_item.*


class AddItemFragment : Fragment(R.layout.fragment_add_item) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dbHandler = context?.let { DatabaseHandler(it) }




        btnSave.setOnClickListener {
            val item = Item()
            item.price = etItemPrice.text.toString()
            item.name = etItemName.text.toString()
            item.description = etItemDescription.text.toString()

            dbHandler?.insertToTable(item)

        }
    }

}