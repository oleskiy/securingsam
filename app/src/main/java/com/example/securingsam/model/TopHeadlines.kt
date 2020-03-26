package com.example.securingsam.model

class TopHeadlines(override var urlToImage: String,
                 override var title: String,
                 override var description: String,
                 override var url: String,
                 author:String,
                 publishedAt:String,
                 content:String,
                 source: Source
):ItemModel