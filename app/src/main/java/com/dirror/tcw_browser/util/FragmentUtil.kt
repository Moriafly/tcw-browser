package com.dirror.tcw_browser.util

import androidx.fragment.app.Fragment
import com.dirror.tcw_browser.ui.fragment.WebFragment

object FragmentUtil {

    val fragmentArrayList = ArrayList<Fragment>()

    fun addWebFragment() {
        fragmentArrayList.add(WebFragment())
    }

}