package com.example.securingsam.view

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Transition
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.example.securingsam.R
import com.example.securingsam.model.ItemModel


class Adapter (val context: Context, var list:ArrayList<ItemModel>): RecyclerView.Adapter<Adapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var title:TextView
        var description:TextView
        var image:ImageView

        init {
            title = itemView.findViewById(R.id.title)
            description = itemView.findViewById(R.id.description)
            image = itemView.findViewById(R.id.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item,parent,false)

        var viewHolder = ViewHolder(view)
        return viewHolder
    }

    override fun getItemCount(): Int {
       return list.size
    }

    fun updateList(newList:ArrayList<ItemModel>){
        list.clear()
        list.addAll(newList)
        
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.description.text = list[position].description
       holder.title.text = list[position].title
        Glide.with(context)
            .asBitmap()
            .load(list[position].urlToImage)
            .into(holder.image)

        holder.itemView.setOnClickListener { v->
            AlertDialog.Builder(v?.context)
                .setTitle("Choose web")
                .setPositiveButton("Internal",
                    DialogInterface.OnClickListener { dialogInterface, i ->  v.context.startActivity(Intent(v?.context,WebView::class.java).putExtra("url",list[position].url))
                    })
                .setNegativeButton("External",
                    DialogInterface.OnClickListener { dialogInterface, i ->  v.context.startActivity(Intent(Intent.ACTION_VIEW).setData(
                        Uri.parse(list[position].url)))
                })
                .show()
        }

    }


}