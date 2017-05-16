package org.zack.library;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

/**
 * Created by zack on 17-5-14.
 */

public class UltimateBar {


    public static final int COLOR = 1;
    public static final int TRANSPARENT = 2;
    public static final int IMMERSION = 3;
    public static final int HINT = 4;



    private int type;

    @ColorInt
    private int color;

    private int alpha;


    private UltimateBar(Builder builder) {
        this.type = builder.type;
        this.color = builder.color;
        this.alpha = builder.alpha;

    }




    public static Builder newBuilder() {
        return new Builder();
    }



    public void into(Activity activity) {
        if (type == COLOR) {
            setColorBar(activity, color, alpha);
        } else if (type == TRANSPARENT) {
            setTransparentBar(activity, color, alpha);
        } else if (type == IMMERSION) {
            setImmersionBar(activity);
        } else if (type == HINT) {
            setHintBar(activity);
        } else {
            throw new NullPointerException("you must set a correct type");
        }
    }


    public static class Builder {

        private int type;

        @ColorInt
        private int color;

        private int alpha;

        private Builder() {}

        public Builder type(int type) {
            this.type = type;
            return this;
        }



        public Builder color(@ColorInt int color) {
            this.color = color;
            return this;
        }

        
        public Builder alpha(int alpha) {
            this.alpha = alpha;
            return this;
        }


        public UltimateBar build() {
            return new UltimateBar(this);
        }

    }







    /**
     * 自定义状态栏和导航栏的颜色和透明度
     *
     * @param color 色值
     * @param alpha 不透明度
     */
    public void setColorBar(Activity activity, @ColorInt int color, int alpha) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            int alphaColor = alpha == 0 ? color : calculateColor(color, alpha);
            window.setStatusBarColor(alphaColor);
            window.setNavigationBarColor(alphaColor);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            int alphaColor = alpha == 0 ? color : calculateColor(color, alpha);
            ViewGroup decorView = (ViewGroup) window.getDecorView();
            decorView.addView(createStatusBarView(activity, alphaColor));
            if (navigationBarExist(activity))
                decorView.addView(createNavBarView(activity, alphaColor));
            setRootView(activity, true);
        }
    }


    public void setColorBar(Activity activity, @ColorInt int color) {
        setColorBar(activity, color, 0);
    }



    /**
     * 半透明效果的状态栏和导航栏
     *
     * @param color 色值
     * @param alpha 透明度
     */
    public void setTransparentBar(Activity activity, @ColorInt int color, int alpha) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            View decorView = window.getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            if (alpha != Color.TRANSPARENT) {
                int alphaColor = Color.argb(alpha, Color.red(color), Color.green(color), Color.blue(color));
                window.setNavigationBarColor(alphaColor);
                window.setStatusBarColor(alphaColor);
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            ViewGroup decorView = (ViewGroup) window.getDecorView();

            int alphaColor = Color.argb(alpha, Color.red(color), Color.green(color), Color.blue(color));
            decorView.addView(createStatusBarView(activity, alphaColor));
            if (navigationBarExist(activity))
                decorView.addView(createNavBarView(activity, alphaColor));
        }

    }



    /**
     * 沉浸状态栏和导航栏
     */
    public void setImmersionBar(Activity activity) {
        setTransparentBar(activity, Color.TRANSPARENT, 0);
    }



    /**
     * 隐藏状态栏和导航栏
     */
    public void setHintBar(Activity activity) {
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





    private View createStatusBarView(Context context, int color) {
        View mStatusBarTintView = new View(context);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams
                (FrameLayout.LayoutParams.MATCH_PARENT, getStatusBarHeight(context));
        params.gravity = Gravity.TOP;
        mStatusBarTintView.setLayoutParams(params);
        mStatusBarTintView.setBackgroundColor(color);
        return mStatusBarTintView;
    }

    private View createNavBarView(Context context, int color) {
        View mNavBarTintView = new View(context);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams
                (FrameLayout.LayoutParams.MATCH_PARENT, getNavigationHeight(context));
        params.gravity = Gravity.BOTTOM;
        mNavBarTintView.setLayoutParams(params);
        mNavBarTintView.setBackgroundColor(color);
        return mNavBarTintView;
    }





    /**
     * 判断是否存在导航栏
     *
     * @param activity
     * @return
     */
    private boolean navigationBarExist(Activity activity) {
        WindowManager windowManager = activity.getWindowManager();
        Display d = windowManager.getDefaultDisplay();

        DisplayMetrics realDisplayMetrics = new DisplayMetrics();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            d.getRealMetrics(realDisplayMetrics);
        }

        int realHeight = realDisplayMetrics.heightPixels;
        int realWidth = realDisplayMetrics.widthPixels;

        DisplayMetrics displayMetrics = new DisplayMetrics();
        d.getMetrics(displayMetrics);

        int displayHeight = displayMetrics.heightPixels;
        int displayWidth = displayMetrics.widthPixels;

        return (realWidth - displayWidth) > 0 || (realHeight - displayHeight) > 0;
    }


    /**
     * 计算状态栏颜色
     *
     * @param color color值
     * @param alpha alpha值
     * @return 最终的状态栏颜色
     */
    @ColorInt
    private int calculateColor(@ColorInt int color, int alpha) {
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
     * 设置根布局参数
     */
    private void setRootView(Activity activity, boolean fit) {
        ViewGroup parent = (ViewGroup) activity.findViewById(android.R.id.content);
        for (int i = 0, count = parent.getChildCount(); i < count; i++) {
            View childView = parent.getChildAt(i);
            if (childView instanceof ViewGroup) {
                childView.setFitsSystemWindows(fit);
                ((ViewGroup)childView).setClipToPadding(fit);
            }
        }
    }



    /**
     * 获取状态栏高度
     * @return 状态栏高度
     */
    private int getStatusBarHeight(Context context) {
        // 获得状态栏高度
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }


    /**
     * 获取导航栏高度
     * @return 导航栏高度
     */
    public int getNavigationHeight(Context context) {

        int resourceId = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }

}
