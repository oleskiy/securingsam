package com.example.securingsam.view

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.securingsam.R
import kotlinx.android.synthetic.main.web_view.*

class WebView : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.web_view)

        var webview = web_view

        if(intent.hasExtra("url")){
            webview.setWebViewClient( WebViewClient());
            webview.loadUrl(intent.getStringExtra("url"))
        }


    }
}