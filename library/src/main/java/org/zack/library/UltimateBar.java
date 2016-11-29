package org.zack.library;


import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2016/10/15.
 */
public class UltimateBar {

    private AppCompatActivity activity;

    public UltimateBar(AppCompatActivity activity) {
        this.activity = activity;
    }

    public void setColorBar(@ColorInt int color) {
        setColorBar(color, 0);
    }

    public void setColorBar(@ColorInt int color, int alpha) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            int alphaColor = calculateStatusColor(color, alpha);
            window.setStatusBarColor(alphaColor);
            window.setNavigationBarColor(alphaColor);
//            ViewGroup decorView = (ViewGroup) window.getDecorView();
//            decorView.addView(createStatusBarView(alphaColor));
//            setupStatusBarView(activity, decorView, color);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            int alphaColor = calculateStatusColor(color, alpha);
            ViewGroup decorView = (ViewGroup) window.getDecorView();
            decorView.addView(createStatusBarView(alphaColor));
            decorView.addView(createNavBarView(alphaColor));
//            int count = decorView.getChildCount();
/*            if (count > 0 && decorView.getChildAt(count - 1) instanceof StatusBarView) {
                decorView.getChildAt(count - 1).setBackgroundColor(alphaColor);
            } else {
                StatusBarView statusView = createStatusBarView(color, alpha, getStatusBarHeight());
                StatusBarView nagivationView = createStatusBarView(color, alpha, getNavigationHeight());
                decorView.addView(statusView);
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getNavigationHeight());
                params.gravity = Gravity.BOTTOM;
                nagivationView.setLayoutParams(params);
                decorView.addView(nagivationView);
                decorView.addView(nagivationView);
            }*/
//            setRootView(alphaColor);
            ViewGroup rootView = (ViewGroup) ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
            rootView.setFitsSystemWindows(true);
            rootView.setClipToPadding(true);
//            rootView.setBackgroundColor(alphaColor);
        }
    }


    public void setImmersionBar() {
        setImmersionBar(Color.TRANSPARENT, 0);
    }


    public void setImmersionBar(@ColorInt int color, int alpha) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            View decorView = window.getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
//            int alphaColor = calculateStatusColor(color, alpha);
            int alphaColor = Color.argb(alpha, Color.red(color), Color.green(color), Color.blue(color));
            window.setNavigationBarColor(alphaColor);
            window.setStatusBarColor(alphaColor);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            ViewGroup decorView = (ViewGroup) window.getDecorView();
            int alphaColor = Color.argb(alpha, Color.red(color), Color.green(color), Color.blue(color));
            decorView.addView(createStatusBarView(alphaColor));
            decorView.addView(createNavBarView(alphaColor));
        }
//        setRootView(color);
    }

    public void setIntranslucentBar() {

    }

    public void hintBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            View decorView = activity.getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }



    private View createStatusBarView(int color) {
        View mStatusBarTintView = new View(activity);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, getStatusBarHeight());
        params.gravity = Gravity.TOP;
        mStatusBarTintView.setLayoutParams(params);
        mStatusBarTintView.setBackgroundColor(color);
        return mStatusBarTintView;
    }

    private View createNavBarView(int color) {
        View mNavBarTintView = new View(activity);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, getNavigationHeight());
        params.gravity = Gravity.BOTTOM;
        mNavBarTintView.setLayoutParams(params);
        mNavBarTintView.setBackgroundColor(color);
        return mNavBarTintView;
    }


    /**
     * 设置根布局参数
     */
    private void setRootView() {
        ViewGroup parent = (ViewGroup) activity.findViewById(android.R.id.content);
        for (int i = 0, count = parent.getChildCount(); i < count; i++) {
            View childView = parent.getChildAt(i);
            if (childView instanceof ViewGroup) {
                childView.setFitsSystemWindows(true);
                ((ViewGroup)childView).setClipToPadding(true);
            }
        }
    }

    /**
     * 计算状态栏颜色
     *
     * @param color color值
     * @param alpha alpha值
     * @return 最终的状态栏颜色
     */
    private int calculateStatusColor(@ColorInt int color, int alpha) {
        float a = 1 - alpha / 255f;
        int red = color >> 16 & 0xff;
        int green = color >> 8 & 0xff;
        int blue = color & 0xff;
        red = (int) (red * a + 0.5);
        green = (int) (green * a + 0.5);
        blue = (int) (blue * a + 0.5);
        return 0xff << 24 | red << 16 | green << 8 | blue;
    }




    /**
     * 生成一个和状态栏大小相同的半透明矩形条
     *
     * @param activity 需要设置的activity
     * @param color    状态栏颜色值
     * @param alpha    透明值
     * @return 状态栏矩形条
     */
    private StatusBarView createStatusBarView(@ColorInt int color, int alpha, int height) {
        // 绘制一个和状态栏一样高的矩形
        StatusBarView statusBarView = new StatusBarView(activity);
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
        statusBarView.setLayoutParams(params);
        statusBarView.setBackgroundColor(calculateStatusColor(color, alpha));
        return statusBarView;
    }


    /**
     * 创建半透明矩形 View
     *
     * @param alpha 透明值
     * @return 半透明 View
     */
    private StatusBarView createTranslucentStatusBarView(int alpha) {
        // 绘制一个和状态栏一样高的矩形
        StatusBarView statusBarView = new StatusBarView(activity);
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight());
        statusBarView.setLayoutParams(params);
        statusBarView.setBackgroundColor(Color.argb(alpha, 0, 0, 0));
        return statusBarView;
    }


/*    private StatusBarView createNavigationBarView(@ColorInt int color, int alpha) {
        StatusBarView statusBarView = new StatusBarView(activity);
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getNavigationBarHeight());
        statusBarView.setLayoutParams(params);
        statusBarView.setBackgroundColor(calculateStatusColor(color, alpha));
        return statusBarView;
    }*/


    /**
     * 设置根布局参数
     */
    private void setRootView(int color) {
        ViewGroup rootView = (ViewGroup) ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
        rootView.setFitsSystemWindows(true);
        rootView.setClipToPadding(true);
//        rootView.setBackgroundColor(color);
    }


    /**
     * 获取状态栏高度
     *
     * @param context context
     * @return 状态栏高度
     */
    private int getStatusBarHeight() {
        // 获得状态栏高度
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return activity.getResources().getDimensionPixelSize(resourceId);
    }


    /**
     * 获取导航栏高度
     * @param context
     * @return 导航栏高度
     */
    public int getNavigationHeight() {

        int resourceId = activity.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        return activity.getResources().getDimensionPixelSize(resourceId);
    }


    /**
     * 使状态栏透明
     */
    private void transparentStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
/*            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);*/

            View decorView = window.getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            window.setNavigationBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }


    /**
     * 添加半透明矩形条
     *
     * @param activity       需要设置的 activity
     * @param statusBarAlpha 透明值
     */
    private void addTranslucentView(int statusBarAlpha) {
        ViewGroup contentView = (ViewGroup) activity.findViewById(android.R.id.content);
        if (contentView.getChildCount() > 1) {
            contentView.getChildAt(1).setBackgroundColor(Color.argb(statusBarAlpha, 0, 0, 0));
        } else {
            contentView.addView(createTranslucentStatusBarView(statusBarAlpha));
        }
    }



}
