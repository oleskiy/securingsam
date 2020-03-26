package com.example.securingsam.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.securingsam.R
import com.example.securingsam.model.ItemModel
import com.example.securingsam.presenter.Contract
import com.example.securingsam.presenter.Presenter
import kotlinx.android.synthetic.main.news_fragment.view.*

class MVPFragment: Fragment(), Contract.MvpView{


    lateinit var presenter: Contract.Presenter
    lateinit var adapter:Adapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity!!.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        return inflater.inflate(R.layout.news_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = Presenter(this,context!!)

        view.architecture_name.text ="MVP architecture"
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
        var everythingBtn = view.everything
        everythingBtn.setOnClickListener{
            presenter.getEverythingData()
        }

        var topHeadlinesBtn = view.top_headlines
        topHeadlinesBtn.setOnClickListener{
            presenter.getTopHeadlinesData()
        }

    }

    override fun setData(result:ArrayList<ItemModel>) {
        adapter.updateList(result)
        adapter.notifyDataSetChanged()
    }


}