package com.dirror.tcw_browser

import com.dirror.music.ui.base.BaseActivity
import com.dirror.tcw_browser.util.FragmentUtil
import com.dirror.tcw_browser.util.toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.include_menu.view.*

class MainActivity : BaseActivity(R.layout.activity_main) {

    companion object {

    }

    override fun initData() {

    }

    override fun initView() {
//        val fragmentManager = supportFragmentManager
//        val fragmentTransaction = fragmentManager.beginTransaction()
        FragmentUtil.addWebFragment()
        replaceFragment(0)
    }

    override fun initListener() {
        includeMenu.ivWindows.setOnClickListener {
            toast(FragmentUtil.fragmentArrayList.size.toString())
        }
    }

    private fun replaceFragment(index: Int) {
        supportFragmentManager.beginTransaction().replace(R.id.flFragment, FragmentUtil.fragmentArrayList[index]).commit()
    }

}