package com.github.zackratos.ultimatebar.sample

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.NestedScrollView
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.github.zackratos.ultimatebar.UltimateBarUtils
import com.github.zackratos.ultimatebar.ultimateBarBuilder
import kotlinx.android.synthetic.main.activity_slide.*

/**
 *  @Author:  zackratos
 *  @Date:    2018/11/15
 *  @Time:    下午8:23
 *  @Email:   zhangwenchao@xiangwushuo.com
 */
class SlideActivity: AppCompatActivity() {

//    private var imageHeight = 0
//    private var toolBarHeight = 0
    private var heightDiff = 0

    private val navigationView by lazy {
        UltimateBarUtils.createNavigationBarView(this,
                ContextCompat.getDrawable(this, R.drawable.yurisa_5))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slide)
        toolbarWrap.setPadding(0, UltimateBarUtils.getStatusBarHeight(this), 0, 0)
        setSupportActionBar(toolbar)
        ultimateBarBuilder().applyNavigation(true).create().immersionBar()
        nsv.setOnScrollChangeListener { _: NestedScrollView?, _, scrollY, _, oldScrollY ->
            Log.d("TAG", "scrollY = $scrollY")
            if (heightDiff in oldScrollY..(scrollY - 1)) {
                toolbarWrap.visibility = View.VISIBLE
                navigationView.setBackgroundColor(Color.WHITE)
                ultimateBarBuilder().statusDark(true)
                        .statusDrawable2(ColorDrawable(Color.parseColor("#55000000")))
                        .applyNavigation(true)
                        .navigationDark(true)
                        .navigationDrawable2(ColorDrawable(Color.parseColor("#55000000")))
                        .create()
                        .immersionBar()
            } else if (heightDiff in scrollY..(oldScrollY - 1)) {
                toolbarWrap.visibility = View.INVISIBLE
                navigationView.setBackgroundResource(R.drawable.yurisa_5)
                ultimateBarBuilder().applyNavigation(true).create().immersionBar()
            }
        }

        if (UltimateBarUtils.navigationBarExist(this)) {
            root.addView(navigationView)
            val param = nsv.layoutParams as FrameLayout.LayoutParams
            param.bottomMargin = UltimateBarUtils.getNavigationBarHeight(this)
            nsv.layoutParams = param
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            val imageHeight = image.height
            val toolBarHeight = toolbarWrap.height
            heightDiff = imageHeight - toolBarHeight
        }
    }

}