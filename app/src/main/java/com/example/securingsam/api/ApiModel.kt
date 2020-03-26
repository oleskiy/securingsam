package com.example.securingsam.api

import com.example.securingsam.model.Everything
import com.example.securingsam.model.TopHeadlines

class EverythingResponse(val status:String, val totalResults:Int, val articles:ArrayList<Everything> )
class TopHeadlinesResponse(val status:String, val totalResults:Int, val articles:ArrayList<TopHeadlines> )