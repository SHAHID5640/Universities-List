package com.example.universitieslist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.webkit.WebView
import android.webkit.WebViewClient

class WebView : AppCompatActivity() {
    private lateinit var webView:WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        webView = findViewById(R.id.webView)

        val urls = intent.getStringExtra("url")



        if (!urls.isNullOrEmpty()) {
                loadUrlsInWebView(webView, urls)
            }
        }



    private fun loadUrlsInWebView(webView: WebView?, urls:String):Boolean {
        webView?.webViewClient = WebViewClient()
        webView?.loadUrl(urls)
        return true

    }


}