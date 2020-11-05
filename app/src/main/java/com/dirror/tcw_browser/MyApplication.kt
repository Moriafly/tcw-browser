package com.dirror.tcw_browser

import android.app.Application
import android.content.Context

/**
 * 自定义 Application
 */
class MyApplication: Application() {

    companion object {
        lateinit var context: Context // 注入懒加载 全局 context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext // 全局 context
    }

}
