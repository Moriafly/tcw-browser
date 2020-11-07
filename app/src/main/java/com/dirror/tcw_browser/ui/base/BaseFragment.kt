package com.dirror.tcw_browser.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_web.*
import kotlinx.android.synthetic.main.include_home.view.*

abstract class BaseFragment(private val layoutId: Int): Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
        initView()
        initListener()
    }

    protected open fun initData() {

    }

    protected open fun initView() {

    }

    protected open fun initListener() {

    }


}