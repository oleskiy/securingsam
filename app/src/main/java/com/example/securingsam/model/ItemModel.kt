package com.example.securingsam.model

open interface ItemModel{
    var urlToImage:String
    var title:String
    var description:String
    var url:String
}

class Source(id: String, name: String)