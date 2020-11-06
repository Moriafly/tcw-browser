package com.dirror.tcw_browser.ui.fragment

import android.annotation.SuppressLint
import android.view.KeyEvent
import android.view.View
import android.webkit.*
import com.dirror.tcw_browser.MainActivity
import com.dirror.tcw_browser.R
import com.dirror.tcw_browser.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_web.*


class WebFragment : BaseFragment(R.layout.fragment_web) {

    companion object {


    }


    override fun initData() {

    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun initView() {

        webView.settings.javaScriptEnabled = true // 支持 JavaScript
        webView.settings.cacheMode = WebSettings.LOAD_DEFAULT // 默认缓存规则
        webView.webViewClient = object : WebViewClient() {
            // 内部跳转
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
//                val intent = Intent(application, WebActivity::class.java)
//                intent.putExtra("extra_webUrlStr", request?.url.toString())
//                // intent.putExtra("extra_webTitleStr", request?.)
//                startActivity(intent)
                return super.shouldOverrideUrlLoading(view, request)
//                return true // 不加载
                // return false
            }

        }

        webView.webChromeClient = object : WebChromeClient() {

            override fun onReceivedTitle(view: WebView, title: String) {
                super.onReceivedTitle(view, title)
                // 获取主 Activity
                val mainActivity = activity as MainActivity?

                mainActivity?.let {
                    it.setWebsiteTitle(title)
                    // 设置网站 URL
                    it.setWebsiteUrl(webView.url?:"")
                    if (canGoBack()) {
                        it.setGoBackAlpha(true)
                    } else {
                        it.setGoBackAlpha(false)
                    }
                    if (canGoForward()) {
                        it.setGoForwardAlpha(true)
                    } else {
                        it.setGoForwardAlpha(false)
                    }
                }


                // webTitleText.text = title
            }

        }

        webView.setOnKeyListener(object : View.OnKeyListener{
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                if (event != null) {
                    if (event.action == KeyEvent.ACTION_DOWN) {
                        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
                            //后退
                            webView.goBack()
                            return true
                        }
                    }
                }
                return false
            }
        })

        // webView.loadUrl(extraWebUrlStr.toString())

    }

    override fun initListener() {

    }

    // 设置网站 URL
    fun setUrl(url: String) {
        webView.loadUrl(url)
    }

    /**
     * 刷新网页，重新加载
     */
    fun reload() {
        webView.reload()
    }

    fun goBack() {
        webView.goBack()
    }

    fun canGoBack(): Boolean {
        return webView.canGoBack()
    }

    fun goForward() {
        webView.goForward()
    }

    fun canGoForward(): Boolean {
        return webView.canGoForward()
    }

}
