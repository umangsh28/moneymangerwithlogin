package com.example.mystore

import androidx.lifecycle.MutableLiveData

class LiveDataApp {

    companion object{

        val liveData = MutableLiveData<String>()

    }

    fun getData(count:Int){
        liveData.value = "Search Item count = $count"
    }



    fun getcountData(): MutableLiveData<String> {
        return liveData
    }
}