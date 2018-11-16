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
    // Android 6.0 以下状态栏灰色模式时，状态栏背景
    private var statusDrawable2: Drawable? = statusDrawable
    // 是否应用到导航栏
    private var applyNavigation: Boolean = false
    // 导航栏灰色模式
    private var navigationDark: Boolean = false
    // 导航栏背景
    private var navigationDrawable: Drawable? = null
    // Android 8.0 以下导航栏灰色模式时，导航栏背景
    private var navigationDrawable2: Drawable? = null

    companion object {
        fun with(activity: Activity): Builder {
            return Builder(activity)
        }
    }

    // 设置背景
    fun drawableBar() {
        UltimateBarUtils.setBarDrawable(activity, statusDark, statusDrawable, statusDrawable2,
                applyNavigation, navigationDark, navigationDrawable, navigationDrawable2)
    }

    // 半透明
    fun transparentBar() {
        UltimateBarUtils.setBarTransparent(activity, statusDark, statusDrawable, statusDrawable2,
                applyNavigation, navigationDark, navigationDrawable, navigationDrawable2)
    }

    // 沉浸
    fun immersionBar() {
        UltimateBarUtils.setBarImmersion(activity, statusDark, statusDrawable2, applyNavigation, navigationDark, navigationDrawable2)
    }

    // 隐藏
    fun hideBar() {
        UltimateBarUtils.setBarHide(activity, applyNavigation)
    }

    // 针对 DrawerLayout 设置背景
    fun drawableBarDrawer(drawerLayout: DrawerLayout, content: View, drawer: View) {
        UltimateBarUtils.setBarDrawableDrawer(activity, drawerLayout, content, drawer, statusDark,
                statusDrawable, statusDrawable2, applyNavigation, navigationDark, navigationDrawable, navigationDrawable2)
    }

    class Builder internal constructor(private val activity: Activity){

        private var statusDark: Boolean = false
        private var statusDrawable: Drawable? = null
        private var statusDrawable2: Drawable? = statusDrawable
        private var statusDrawable2HasSet: Boolean = false
        private var applyNavigation: Boolean = false
        private var navigationDark: Boolean = false
        private var navigationDrawable: Drawable? = null
        private var navigationDrawable2: Drawable? = navigationDrawable
        private var navigationDrawable2HasSet: Boolean = false


        fun statusDark(statusDark: Boolean): Builder {
            this.statusDark = statusDark
            return this
        }

        fun statusDrawable(statusDrawable: Drawable?): Builder {
            this.statusDrawable = statusDrawable
            if (!statusDrawable2HasSet) this.statusDrawable2 = statusDrawable
            return this
        }

        fun statusDrawable2(statusDrawable2: Drawable?): Builder {
            this.statusDrawable2 = statusDrawable2
            statusDrawable2HasSet = true
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
            if (!navigationDrawable2HasSet) this.navigationDrawable2 = navigationDrawable
            return this
        }

        fun navigationDrawable2(navigationDrawable2: Drawable?): Builder {
            this.navigationDrawable2 = navigationDrawable2
            navigationDrawable2HasSet = true
            return this
        }

        fun create(): UltimateBar {
            return UltimateBar(activity).apply {
                statusDark = this@Builder.statusDark
                statusDrawable = this@Builder.statusDrawable
                statusDrawable2 = this@Builder.statusDrawable2
                applyNavigation = this@Builder.applyNavigation
                navigationDark = this@Builder.navigationDark
                navigationDrawable = this@Builder.navigationDrawable
                navigationDrawable2 = this@Builder.navigationDrawable2
            }
        }
    }

}