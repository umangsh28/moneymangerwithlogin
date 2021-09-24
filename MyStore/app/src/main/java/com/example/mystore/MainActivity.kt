package com.example.mystore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.mystore.Fragment.AddItemFragment
import com.example.mystore.Fragment.SearchItemFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addItem = AddItemFragment()
        val data=LiveDataApp()

        data.getcountData().observe(this, Observer {
            tvSearchCount.text=it
        })

        supportFragmentManager.beginTransaction()
            .add(R.id.addItemFragment, addItem)
            .commit()

        val searchItem = SearchItemFragment()

        supportFragmentManager.beginTransaction()
            .add(R.id.searchItemFragment, searchItem)
            .commit()


        btnGallery.setOnClickListener {
            val intent = Intent(this, GalleryActivity::class.java)
            startActivity(intent)
        }

    }
}