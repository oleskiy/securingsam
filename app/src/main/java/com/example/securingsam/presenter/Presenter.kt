package com.example.securingsam.presenter

import android.content.Context
import android.util.Log
import com.example.securingsam.api.ApiService
import com.example.securingsam.api.EverythingResponse
import com.example.securingsam.api.TopHeadlinesResponse
import com.example.securingsam.model.ItemModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class Presenter(var mvpView: Contract.MvpView, context: Context):Contract.Presenter{
    private var apiService: ApiService

    init {
        apiService = ApiService.initApiService(context)
    }


    override fun getEverythingData() {

        apiService.getEverything()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {  it.localizedMessage }
            .subscribe{it->
                if(it.status.equals("ok")) {
                    mvpView.setData(it.articles as ArrayList<ItemModel>)
                }
            }

    }

    override fun getTopHeadlinesData() {

        apiService.getTopHeadlines()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {  it.localizedMessage }
            .subscribe{it->
                if(it.status.equals("ok")) {
                    mvpView.setData(it.articles as ArrayList<ItemModel>)
                }
            }

    }


}
