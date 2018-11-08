package com.github.zackratos.ultimatebar

import android.app.Activity
import android.graphics.drawable.Drawable
import android.support.v4.widget.DrawerLayout
import android.view.View

/**
 *  @Author:  zackratos
 *  @Date:    2018/11/6
 *  @Time:    下午8:08
 *  @Email:   zhangwenchao@xiangwushuo.com
 */
class UltimateBar private constructor(private val activity: Activity) {

    // 状态栏灰色模式
    private var statusDark: Boolean = false
    // 状态栏背景
    private var statusDrawable: Drawable? = null
    // 是否应用到导航栏
    private var applyNavigation: Boolean = false
    // 导航栏灰色模式
    private var navigationDark: Boolean = false
    // 导航栏背景
    private var navigationDrawable: Drawable? = null

    companion object {
        fun with(activity: Activity): Builder {
            return Builder(activity)
        }
    }

    // 设置背景
    fun drawableBar() {
        UltimateBarUtils.setBarDrawable(activity, statusDark, statusDrawable, applyNavigation, navigationDark, navigationDrawable)
    }

    // 半透明
    fun transparentBar() {
        UltimateBarUtils.setBarTransparent(activity, statusDark, statusDrawable, applyNavigation, navigationDark, navigationDrawable)
    }

    // 沉浸
    fun immersionBar() {
        UltimateBarUtils.setBarImmersion(activity, statusDark, applyNavigation, navigationDark)
    }

    // 隐藏
    fun hideBar() {
        UltimateBarUtils.setBarHide(activity, applyNavigation)
    }

    // 针对 DrawerLayout 设置背景
    fun drawableBarDrawer(drawerLayout: DrawerLayout, content: View, drawer: View) {
        UltimateBarUtils.setBarDrawableDrawer(activity, drawerLayout, content, drawer,
                statusDark, statusDrawable, applyNavigation, navigationDark, navigationDrawable)
    }

    class Builder internal constructor(private val activity: Activity){

        private var statusDark: Boolean = false
        private var statusDrawable: Drawable? = null
        private var applyNavigation: Boolean = false
        private var navigationDark: Boolean = false
        private var navigationDrawable: Drawable? = null

        fun statusDark(statusDark: Boolean): Builder {
            this.statusDark = statusDark
            return this
        }

        fun statusDrawable(statusDrawable: Drawable?): Builder {
            this.statusDrawable = statusDrawable
            return this
        }

        fun applyNavigation(applyNavigation: Boolean): Builder {
            this.applyNavigation = applyNavigation
            return this
        }

        fun navigationDark(navigationDark: Boolean): Builder {
            this.navigationDark = navigationDark
            return this
        }

        fun navigationDrawable(navigationDrawable: Drawable?): Builder {
            this.navigationDrawable = navigationDrawable
            return this
        }

        fun create(): UltimateBar {
            return UltimateBar(activity).apply {
                statusDark = this@Builder.statusDark
                statusDrawable = this@Builder.statusDrawable
                applyNavigation = this@Builder.applyNavigation
                navigationDark = this@Builder.navigationDark
                navigationDrawable = this@Builder.navigationDrawable
            }
        }
    }

}