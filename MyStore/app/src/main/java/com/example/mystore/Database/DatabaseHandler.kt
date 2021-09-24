package com.example.mystore.Database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.mystore.RecyclerView.Item

class DatabaseHandler(val context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        private val DB_NAME = "itemApp"
        private val DB_VERSION = 1
        private val TABLE_NAME = "item"
        private val ID = "id"
        private val NAME = "name"
        private val DESC = "description"
        private val PRICE = "price"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE =
            "create table $TABLE_NAME " +
                    "($ID integer primary key, " +
                    "$NAME text," +
                    " $DESC text, " +
                    "$PRICE integer)"
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insertToTable(item: Item) {
        val db = writableDatabase

        val contentValue = ContentValues()
        //contentValue.put(ID, item.id)
        contentValue.put(NAME, item.name)
        contentValue.put(DESC, item.description)
        contentValue.put(PRICE, item.price)

        val insertValue = db.insert(TABLE_NAME, null, contentValue)

        if (insertValue.toInt() == -1) {
            Toast.makeText(context, "Failed to Insert", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Successfully Inserted", Toast.LENGTH_SHORT).show()

        }
    }


    fun getItem(name: String): MutableList<Item> {
        val db = readableDatabase

        val list = mutableListOf<Item>()
        val query = "Select * from $TABLE_NAME where $NAME ='$name' or $DESC ='$name'"
        val cursor = db?.rawQuery(query, null)
        if (cursor != null && cursor.count > 0) {
            cursor.moveToFirst()
            do {
                val dId = cursor.getInt(cursor.getColumnIndex(ID))
                val dName = cursor.getString(cursor.getColumnIndex(NAME))
                val dDesc = cursor.getString(cursor.getColumnIndex(DESC))
                val dPrice = cursor.getString(cursor.getColumnIndex(PRICE))

                val item = Item()
                item.id = dId
                item.name = dName
                item.description = dDesc
                item.price = dPrice

                list.add(item)

            } while (cursor.moveToNext())
        }
        return list
    }
}