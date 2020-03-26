package com.example.securingsam.api

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.securingsam.model.ItemModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ViewModelNews(application: Application) :AndroidViewModel(application){

    var dataList: ArrayList<ItemModel> = ArrayList()
    private val mutableListNews: MutableLiveData<ArrayList<ItemModel>> = MutableLiveData()

    public fun getEverything(page: Int):LiveData<ArrayList<ItemModel>>{

        ApiService.initApiService(getApplication()).getEverything()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {  it.localizedMessage }
            .subscribe{it->
                if(it.status.equals("ok")) {
                    if(page==0) {
                        dataList.clear()
                    }
                    dataList.addAll(it.articles)
                    mutableListNews.postValue(dataList)
                }
            }
        return mutableListNews
    }

    public fun getTopHeadlines(page: Int):LiveData<ArrayList<ItemModel>>{

        ApiService.initApiService(getApplication()).getTopHeadlines()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{it->
                if(it.status.equals("ok")) {
                    if(page==0) {
                        dataList.clear()
                    }
                        dataList.addAll(it.articles)
                    mutableListNews.postValue(dataList)
                }
            }
        return mutableListNews
    }

}
