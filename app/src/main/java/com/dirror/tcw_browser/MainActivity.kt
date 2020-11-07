package com.dirror.tcw_browser

import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.LinearInterpolator
import com.dirror.music.ui.base.BaseActivity
import com.dirror.tcw_browser.ui.fragment.WebFragment
import com.dirror.tcw_browser.util.FragmentUtil
import com.dirror.tcw_browser.util.dp2px
import com.dirror.tcw_browser.util.toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.include_menu.view.*
import kotlinx.android.synthetic.main.include_title.*
import kotlinx.android.synthetic.main.include_title.view.*

class MainActivity : BaseActivity(R.layout.activity_main) {

    override fun initData() {

    }

    override fun initView() {

        FragmentUtil.addWebFragment()
        replaceFragment(0)

        setOptionsVisibility(false)
    }

    override fun initListener() {
        includeMenu.ivWindows.setOnClickListener {
            toast(FragmentUtil.fragmentArrayList.size.toString())
        }

        // 刷新
        includeTitle.ivReload.setOnClickListener {
            FragmentUtil.fragmentArrayList[0].reload()
        }

        // 选项
        includeMenu.ivMenu.setOnClickListener {
            if (clForeground.visibility == View.GONE) {
                setOptionsVisibility(true)
            } else {
                setOptionsVisibility(false)
            }
        }

        includeMenu.ivBack.setOnClickListener {
            FragmentUtil.fragmentArrayList[0].goBack()
        }

        includeMenu.ivForward.setOnClickListener {
            FragmentUtil.fragmentArrayList[0].goForward()
        }

        clForeground.setOnClickListener {
            setOptionsVisibility(false)
        }

        includeMenu.ivHome.setOnClickListener {
            FragmentUtil.fragmentArrayList[0].showHome()
        }


    }

    private fun replaceFragment(index: Int) {
        supportFragmentManager.beginTransaction().replace(R.id.flFragment, FragmentUtil.fragmentArrayList[index]).commit()

    }

    override fun onStart() {
        super.onStart()
        // FragmentUtil.fragmentArrayList[0].setUrl("https://www.baidu.com")
    }

    /**
     * 设置网站标题
     */
    fun setWebsiteTitle(websiteTitle: String) {
        includeTitle.tvWebsiteTitle.text = websiteTitle
    }

    fun setWebsiteUrl(websiteUrl: String) {
        includeTitle.etWebsiteUrl.setText(websiteUrl)
    }

    /**
     * 新建一个窗体
     */
    fun addNewWebFragment() {
        // FragmentUtil.addWebFragment()
    }

    /**
     * 设置菜单选项界面的可见性
     */
    private fun setOptionsVisibility(visibility: Boolean) {
        if (visibility) {
            clForeground.visibility = View.VISIBLE
            includeOptions.visibility = View.VISIBLE
            ObjectAnimator.ofFloat(includeOptions, "translationY", dp2px(64f), 0f).apply {
                // interpolator = LinearInterpolator()
                interpolator = AccelerateDecelerateInterpolator()
                duration = 150
            }.start()
            ObjectAnimator.ofFloat(clForeground, "alpha", 0f, 0.25f).apply {
                interpolator = AccelerateDecelerateInterpolator()
                duration = 150
            }.start()
        } else {
            clForeground.visibility = View.GONE
            includeOptions.visibility = View.GONE
        }
    }

    fun setGoBackAlpha(can: Boolean) {
        includeMenu.ivBack.alpha = if (can) {
            0.72f
        } else {
            0.28f
        }
    }

    fun setGoForwardAlpha(can: Boolean) {
        includeMenu.ivForward.alpha = if (can) {
            0.72f
        } else {
            0.28f
        }
    }


}