package com.example.securingsam.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.securingsam.R
import com.example.securingsam.api.ViewModelNews
import com.example.securingsam.model.ItemModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.news_fragment.view.*

class MVVMFragment :Fragment(){

    lateinit var adapter:Adapter
    private lateinit var mainViewModel: ViewModelNews
    var page=0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity!!.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        mainViewModel = ViewModelNews(activity!!.application)
        return inflater.inflate(R.layout.news_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var rvNews = view.recyclerView
        var linearLayoutManager = LinearLayoutManager(context)
        rvNews.layoutManager = linearLayoutManager

        adapter = Adapter(view.context ,ArrayList<ItemModel>())
        rvNews.adapter = adapter

        rvNews.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )

        view.architecture_name.text ="MVVM architecture"

        var everythingBtn = view.everything
        everythingBtn.setOnClickListener{
            page=0
            subscribeDataEverything()
        }

        var topHeadlinesBtn = view.top_headlines
        topHeadlinesBtn.setOnClickListener{
            page=0
            subscribeDataTopHeadlines()
        }

    }

    private fun subscribeDataEverything() {

        mainViewModel.getEverything(page)?.observe(this.viewLifecycleOwner, Observer<ArrayList<ItemModel>> {

            if (it != null) {
                adapter.updateList(mainViewModel.dataList)
                adapter.notifyDataSetChanged()
            }

        })
        page++

    }

    private fun subscribeDataTopHeadlines() {

        mainViewModel.getTopHeadlines(page)?.observe(this.viewLifecycleOwner, Observer<ArrayList<ItemModel>> {

            if (it != null) {
                adapter.updateList(mainViewModel.dataList)
                adapter.notifyDataSetChanged()
            }

        })
        page++
    }
}