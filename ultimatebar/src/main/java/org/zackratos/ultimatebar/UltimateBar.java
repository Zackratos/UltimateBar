package org.zackratos.ultimatebar;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
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
 *
 * Created by zack on 17-5-14.
 */

public class UltimateBar {


    private Activity activity;

    public UltimateBar(Activity activity) {
        this.activity = activity;
    }

    /**
     *
     * @param color
     * @param depth
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void setColorStatusBar(@ColorInt int color, int depth) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            int finalColor = depth == 0 ? color : calculateColor(color, depth);
            window.setStatusBarColor(finalColor);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            int finalColor = depth == 0 ? color : calculateColor(color, depth);
            ViewGroup decorView = (ViewGroup) window.getDecorView();
            decorView.addView(createStatusBarView(activity, finalColor));
            setRootView(activity, true);
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void setColorStatusBar(@ColorInt int color) {
        setColorStatusBar(color, 0);
    }

    /**
     *
     * @param color
     * @param depth
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void setColorNavigationBar(@ColorInt int color, int depth) {
        if (!navigationBarExist(activity)) {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            int finalColor = depth == 0 ? color : calculateColor(color, depth);
            window.setNavigationBarColor(finalColor);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            int finalColor = depth == 0 ? color : calculateColor(color, depth);
            ViewGroup decorView = (ViewGroup) window.getDecorView();
            decorView.addView(createNavBarView(activity, finalColor));
            setRootView(activity, true);
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void setColorNavigationBar(@ColorInt int color) {
        setColorNavigationBar(color, 0);
    }

    /**
     *
     * @param color
     * @param depth
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void setColorBar(@ColorInt int color, int depth) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            int finalColor = depth == 0 ? color : calculateColor(color, depth);
            window.setStatusBarColor(finalColor);
            window.setNavigationBarColor(finalColor);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            int finalColor = depth == 0 ? color : calculateColor(color, depth);
            ViewGroup decorView = (ViewGroup) window.getDecorView();
            decorView.addView(createStatusBarView(activity, finalColor));
            if (navigationBarExist(activity)) {
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                decorView.addView(createNavBarView(activity, finalColor));
            }
            setRootView(activity, true);
        }
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void setColorBar(@ColorInt int color) {
        setColorBar(color, 0);
    }

    /**
     *
     * @param color
     * @param depth
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void setColorStatusBarForDrawer(@ColorInt int color, int depth) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            ViewGroup decorView = (ViewGroup) window.getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            window.setStatusBarColor(Color.TRANSPARENT);
            int finalColor = depth == 0 ? color : calculateColor(color, depth);
            decorView.addView(createStatusBarView(activity, finalColor), 0);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ViewGroup decorView = (ViewGroup) window.getDecorView();
            int finalColor = depth == 0 ? color : calculateColor(color, depth);
            decorView.addView(createStatusBarView(activity, finalColor), 0);
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void setColorStatusBarForDrawer(@ColorInt int color) {
        setColorStatusBarForDrawer(color, 0);
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void setColorBarForDrawer(@ColorInt int color, int depth) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            ViewGroup decorView = (ViewGroup) window.getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            if (navigationBarExist(activity)) {
                option = option | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
            }
            decorView.setSystemUiVisibility(option);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
            int finalColor = depth == 0 ? color : calculateColor(color, depth);
            decorView.addView(createStatusBarView(activity, finalColor), 0);
            if (navigationBarExist(activity)) {
                decorView.addView(createNavBarView(activity, finalColor), 1);
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ViewGroup decorView = (ViewGroup) window.getDecorView();
            int finalColor = depth == 0 ? color : calculateColor(color, depth);
            decorView.addView(createStatusBarView(activity, finalColor), 0);
            if (navigationBarExist(activity)) {
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                decorView.addView(createNavBarView(activity, finalColor), 1);
            }
        }
    }



    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void setColorBarForDrawer(@ColorInt int color) {
        setColorBarForDrawer(color, 0);
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void setTransparentStatusBar(@ColorInt int color, int alpha) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            View decorView = window.getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            int finalColor = alpha == 0 ? Color.TRANSPARENT :
                    Color.argb(alpha, Color.red(color), Color.green(color), Color.blue(color));
            window.setStatusBarColor(finalColor);

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ViewGroup decorView = (ViewGroup) window.getDecorView();
            int finalColor = alpha == 0 ? Color.TRANSPARENT :
                    Color.argb(alpha, Color.red(color), Color.green(color), Color.blue(color));
            decorView.addView(createStatusBarView(activity, finalColor));
        }
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void setTransparentBar(@ColorInt int color, int alpha) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            View decorView = window.getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);

            int finalColor = alpha == 0 ? Color.TRANSPARENT :
                    Color.argb(alpha, Color.red(color), Color.green(color), Color.blue(color));

            window.setNavigationBarColor(finalColor);
            window.setStatusBarColor(finalColor);

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ViewGroup decorView = (ViewGroup) window.getDecorView();
            int finalColor = alpha == 0 ? Color.TRANSPARENT :
                    Color.argb(alpha, Color.red(color), Color.green(color), Color.blue(color));
            decorView.addView(createStatusBarView(activity, finalColor));
            if (navigationBarExist(activity)) {
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                decorView.addView(createNavBarView(activity, finalColor));
            }
        }

    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void setImmersionStatusBar() {
        setTransparentStatusBar(Color.TRANSPARENT, 0);
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void setImmersionBar() {
        setTransparentBar(Color.TRANSPARENT, 0);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void setHideStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            View decorView = activity.getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void setHideBar() {
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





    private View createStatusBarView(Context context, @ColorInt int color) {
        View mStatusBarTintView = new View(context);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams
                (FrameLayout.LayoutParams.MATCH_PARENT, getStatusBarHeight(context));
        params.gravity = Gravity.TOP;
        mStatusBarTintView.setLayoutParams(params);
        mStatusBarTintView.setBackgroundColor(color);
        return mStatusBarTintView;
    }

    private View createNavBarView(Context context, @ColorInt int color) {
        View mNavBarTintView = new View(context);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams
                (FrameLayout.LayoutParams.MATCH_PARENT, getNavigationHeight(context));
        params.gravity = Gravity.BOTTOM;
        mNavBarTintView.setLayoutParams(params);
        mNavBarTintView.setBackgroundColor(color);
        return mNavBarTintView;
    }



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



    private void setRootView(Activity activity, boolean fit) {
        ViewGroup parent = activity.findViewById(android.R.id.content);
        for (int i = 0; i < parent.getChildCount(); i++) {
            View childView = parent.getChildAt(i);
            if (childView instanceof ViewGroup) {
                childView.setFitsSystemWindows(fit);
                ((ViewGroup)childView).setClipToPadding(fit);
            }
        }
    }



    private int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        return resources.getDimensionPixelSize(resourceId);
    }


    private int getNavigationHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        return resources.getDimensionPixelSize(resourceId);
    }

}
