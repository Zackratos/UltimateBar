package com.github.zackratos.ultimatebar

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.v4.widget.DrawerLayout
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout

/**
 *  @Author:  zackratos
 *  @Date:    2018/11/6
 *  @Time:    下午7:04
 *  @Email:   zhangwenchao@xiangwushuo.com
 */
object UltimateBarUtils {

    /**
     * 给状态栏和导航栏设置背景
     * @param statusDark 状态栏灰色模式
     * @param statusDrawable 状态栏背景
     * @param applyNavigation 是否应用到导航栏
     * @param navigationDark 导航栏灰色模式
     * @param navigationDrawable 导航栏背景
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    internal fun setBarDrawable(activity: Activity,
                       statusDark: Boolean = false,
                       statusDrawable: Drawable? = null,
                       applyNavigation: Boolean = false,
                       navigationDark: Boolean = false,
                       navigationDrawable: Drawable? = null) {
        setBar(activity, statusDark, statusDrawable, applyNavigation, navigationDark, navigationDrawable, true)
    }

    /**
     * 半透明状态栏和导航栏
     * @param statusDark 状态栏灰色模式
     * @param statusDrawable 状态栏背景
     * @param applyNavigation 是否应用到导航栏
     * @param navigationDark 导航栏灰色模式
     * @param navigationDrawable 导航栏背景
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    internal fun setBarTransparent(activity: Activity,
                          statusDark: Boolean = false,
                          statusDrawable: Drawable? = null,
                          applyNavigation: Boolean = false,
                          navigationDark: Boolean = false,
                          navigationDrawable: Drawable? = null) {
        setBar(activity, statusDark, statusDrawable, applyNavigation, navigationDark, navigationDrawable, false, true)
    }

    /**
     * 状态栏和导航栏沉浸
     * @param statusDark 状态栏灰色模式
     * @param applyNavigation 是否应用到导航栏
     * @param navigationDark 导航栏灰色模式
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    internal fun setBarImmersion(activity: Activity,
                        statusDark: Boolean = false,
                        applyNavigation: Boolean = false,
                        navigationDark: Boolean = false) {
        setBarTransparent(activity, statusDark, null, applyNavigation, navigationDark, null)
    }

    /**
     * 隐藏状态栏和导航栏
     * @param applyNavigation 是否应用到导航栏
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    internal fun setBarHide(activity: Activity, applyNavigation: Boolean = false) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val decorView = activity.window.decorView
            var option = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
            if (applyNavigation) {
                option = (option or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
            }
            decorView.systemUiVisibility = option
        }
    }

    /**
     * 针对 DrawerLayout 设置状态栏和导航栏背景
     * @param content DrawerLayout 中的主布局 View
     * @param drawer DrawerLayout 中的抽屉布局 View
     * @param statusDark 状态栏灰色模式
     * @param statusDrawable 状态栏背景
     * @param applyNavigation 是否应用到导航栏
     * @param navigationDark 导航栏灰色模式
     * @param navigationDrawable 导航栏背景
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    internal fun setBarDrawableDrawer(activity: Activity,
                             drawerLayout: DrawerLayout,
                             content: View,
                             drawer: View,
                             statusDark: Boolean = false,
                             statusDrawable: Drawable? = null,
                             applyNavigation: Boolean = false,
                             navigationDark: Boolean = false,
                             navigationDrawable: Drawable? = null) {
        setRootView(drawerLayout, false)
        setRootView(content, true)
        setRootView(drawer, false)
        setBar(activity, statusDark, statusDrawable, applyNavigation, navigationDark, navigationDrawable, false)
    }

    /**
     * 核心方法
     * @param statusDark 状态栏灰色模式
     * @param statusDrawable 状态栏背景
     * @param applyNavigation 是否应用到导航栏
     * @param navigationDark 导航栏灰色模式
     * @param navigationDrawable 导航栏背景
     * @param fitsSystemWindows 跟布局是否设置 fitsSystemWindows
     * @param lastIndex 状态栏和导航栏 view 是否加在 decorView 的最后位置
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private fun setBar(activity: Activity,
                       statusDark: Boolean = false,
                       statusDrawable: Drawable? = null,
                       applyNavigation: Boolean = false,
                       navigationDark: Boolean = false,
                       navigationDrawable: Drawable? = null,
                       fitsSystemWindows: Boolean = false,
                       lastIndex: Boolean = false) {
        setRootView(activity, fitsSystemWindows)
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> {
                val window = activity.window
                val decorView = window.decorView as ViewGroup
                var option = when {
                    Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && statusDark ->
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                    else -> View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                }
                window.statusBarColor = Color.TRANSPARENT
                setStatusBarView(activity, decorView, lastIndex, statusDrawable)
                when {
                    applyNavigation && navigationBarExist(activity) -> {
                        option = option or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        when {
                            Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && navigationDark ->
                                option = option or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
                        }
                        window.navigationBarColor = Color.TRANSPARENT
                        setNavigationBarView(activity, decorView, lastIndex, navigationDrawable)
                    }
                    else -> {
                        window.navigationBarColor = Color.BLACK
//                        setNavigationBarView(activity, decorView, lastIndex, null)
                        removeNavigationBarView(decorView)
                    }
                }
                decorView.systemUiVisibility = option
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT -> {
                val window = activity.window
                val winParams = window.attributes
                if (winParams.flags and WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS == 0) {
                    window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                }
                val decorView = window.decorView as ViewGroup
                while (decorView.childCount < 2) decorView.addView(View(activity))
                setStatusBarView(activity, decorView, lastIndex, statusDrawable)
                when {
                    applyNavigation && navigationBarExist(activity) -> {
                        if (winParams.flags and WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION == 0) {
                            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
                        }
                        setNavigationBarView(activity, decorView, lastIndex, navigationDrawable)
                    }
                    else -> {
                        if (winParams.flags and WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION != 0) {
                            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
                        }
//                        setNavigationBarView(activity, decorView, lastIndex, null)
                        removeNavigationBarView(decorView)
                    }
                }
            }
        }
    }


    // 导航栏是否存在
    fun navigationBarExist(activity: Activity): Boolean {
        val windowManager = activity.windowManager
        val d = windowManager.defaultDisplay

        val realDisplayMetrics = DisplayMetrics()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            d.getRealMetrics(realDisplayMetrics)
        }

        val realHeight = realDisplayMetrics.heightPixels
        val realWidth = realDisplayMetrics.widthPixels

        val displayMetrics = DisplayMetrics()
        d.getMetrics(displayMetrics)

        val displayHeight = displayMetrics.heightPixels
        val displayWidth = displayMetrics.widthPixels

        return realWidth - displayWidth > 0 || realHeight - displayHeight > 0
    }

    // 状态栏高度
    fun getStatusBarHeight(context: Context): Int {
        return getBarHeight(context, "status_bar_height")
    }

    // 导航栏高度
    fun getNavigationBarHeight(context: Context): Int {
        return getBarHeight(context, "navigation_bar_height")
    }

    private fun getBarHeight(context: Context, name: String): Int {
        val resources = context.resources
        val resourceId = resources.getIdentifier(name, "dimen", "android")
        return resources.getDimensionPixelSize(resourceId)
    }


    private fun setStatusBarView(context: Context, decorView: ViewGroup, lastIndex: Boolean, background: Drawable?) {
        var statusBarView: View? = decorView.findViewWithTag("status_bar")
        if (statusBarView == null) {
            statusBarView = createStatusBarView(context, background)
            statusBarView.tag = "status_bar"
            if (lastIndex) decorView.addView(statusBarView)
            else {
                val navigationBarView: View? = decorView.findViewWithTag("navigation_bar")
                val index = when (navigationBarView) {
                    null -> 0
                    else -> 1
                }
                decorView.addView(statusBarView, index)
            }

        } else {
            statusBarView.setBackgroundDrawable(background)

        }
    }

    private fun setNavigationBarView(context: Context, decorView: ViewGroup, lastIndex: Boolean, background: Drawable?) {
        var navigationBarView: View? = decorView.findViewWithTag("navigation_bar")
        if (navigationBarView == null) {
            navigationBarView = createNavigationBarView(context, background)
            navigationBarView.tag = "navigation_bar"
            if (lastIndex) decorView.addView(navigationBarView)
            else {
                val statusBarView: View? = decorView.findViewWithTag("status_bar")
                val index = when (statusBarView) {
                    null -> 0
                    else -> 1
                }
                decorView.addView(navigationBarView, index)
            }
        } else {
            navigationBarView.setBackgroundDrawable(background)
        }
    }

    private fun removeNavigationBarView(decorView: ViewGroup) {
        val navigationBarView: View? = decorView.findViewWithTag("navigation_bar")
        if (navigationBarView != null) decorView.removeView(navigationBarView)
    }


    private fun createStatusBarView(context: Context, background: Drawable?): View {
        val statusBarView = View(context)
        val params = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, getStatusBarHeight(context))
        params.gravity = Gravity.TOP
        statusBarView.layoutParams = params
        statusBarView.setBackgroundDrawable(background)
        return statusBarView
    }


    private fun createNavigationBarView(context: Context, background: Drawable?): View {
        val navBarView = View(context)
        val params = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, getNavigationBarHeight(context))
        params.gravity = Gravity.BOTTOM
        navBarView.layoutParams = params
        navBarView.setBackgroundDrawable(background)
        return navBarView
    }

    private fun setRootView(activity: Activity, fit: Boolean) {
        val root: FrameLayout = activity.findViewById(android.R.id.content)
        val content = root.getChildAt(0)
        setRootView(content, fit)
    }

    private fun setRootView(view: View, fit: Boolean) {
        if (view is ViewGroup) {
            view.fitsSystemWindows = fit
            view.clipToPadding = fit
        }
    }

    private fun romType(): String {
        if (Build.BOOTLOADER == "Xiaomi") {
            return "Xiaomi"
        }
        if (Build.DISPLAY.contains("FLYME")) {
            return "FLYME"
        }
        return "Other"
    }

    @SuppressLint("PrivateApi")
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private fun miuiStatusDark(darkmode: Boolean, activity: Activity) {
        val clazz = activity.window.javaClass
        try {
            val layoutParams = Class.forName("android.view.MiuiWindowManager\$LayoutParams")
            val field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE")
            val darkModeFlag = field.getInt(layoutParams)
            val extraFlagField = clazz.getMethod("setExtraFlags", Int::class.javaPrimitiveType, Int::class.javaPrimitiveType)
            extraFlagField.invoke(activity.window, if (darkmode) darkModeFlag else 0, darkModeFlag)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private fun flymeStatusDark(activity: Activity, dark: Boolean) {
        try {
            val lp = activity.window.attributes
            val darkFlag = WindowManager.LayoutParams::class.java
                    .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON")
            val meizuFlags = WindowManager.LayoutParams::class.java
                    .getDeclaredField("meizuFlags")
            darkFlag.isAccessible = true
            meizuFlags.isAccessible = true
            val bit = darkFlag.getInt(null)
            var value = meizuFlags.getInt(lp)
            value = if (dark) {
                value or bit
            } else {
                value and bit.inv()
            }
            meizuFlags.setInt(lp, value)
            activity.window.attributes = lp
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}