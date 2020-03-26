package com.example.securingsam.presenter

import android.content.Context
import com.example.securingsam.api.EverythingResponse
import com.example.securingsam.api.TopHeadlinesResponse
import com.example.securingsam.model.Everything
import com.example.securingsam.model.ItemModel
import io.reactivex.Single

interface Contract{

    interface Presenter{
        fun getEverythingData()
        fun getTopHeadlinesData()
    }

    interface MvpView{
        fun setData(result:ArrayList<ItemModel>)
    }


}