package com.dirror.tcw_browser.ui.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.webkit.*
import com.dirror.tcw_browser.MainActivity
import com.dirror.tcw_browser.R
import com.dirror.tcw_browser.ui.base.BaseFragment
import com.dirror.tcw_browser.util.toast
import kotlinx.android.synthetic.main.fragment_web.*
import kotlinx.android.synthetic.main.include_home.view.*


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
//                startActivity(intent

//                Intent(Intent.ACTION_VIEW, Uri.parse(request?.url.toString())).apply {
//                    startActivity(this)
//                }
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
        includeHome.etSearch.apply {
            setOnEditorActionListener { _, p1, _ ->
                if (p1 == EditorInfo.IME_ACTION_SEARCH) { // 软键盘点击了搜索
                    search()
                }
                false
            }
            addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
                override fun afterTextChanged(s: Editable) {
                    if (etSearch.text.toString() != "") {
                        // btnClear.visibility = View.VISIBLE // 有文字，显示清楚按钮
                    } else {
                        // btnClear.visibility = View.INVISIBLE // 隐藏
                    }
                }
            })
        }
    }

    /**
     * 搜索
     */
    private fun search() {
        hideHome()
        setUrl("https://www.baidu.com")
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
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            // toast("回到主页")
            showHome()
        }
    }

    fun canGoBack(): Boolean {
        return webView.canGoBack()
    }

    fun goForward() {
        if (webView.canGoForward()) {
            hideHome()
            webView.goForward()
        }
    }

    fun canGoForward(): Boolean {
        return webView.canGoForward()
    }

    /**
     * 显示 home
     */
    fun showHome() {
        includeHome.visibility = View.VISIBLE
        webView.visibility = View.GONE
    }

    fun hideHome() {
        // home 消失
        includeHome.visibility = View.GONE
        webView.visibility = View.VISIBLE
    }

}
