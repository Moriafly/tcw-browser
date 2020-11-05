package com.dirror.tcw_browser.util

import androidx.fragment.app.Fragment
import com.dirror.tcw_browser.ui.fragment.WebFragment

object FragmentUtil {

    val fragmentArrayList = ArrayList<WebFragment>()

    fun addWebFragment() {
        fragmentArrayList.add(WebFragment())
    }

    fun test() {

    }

}