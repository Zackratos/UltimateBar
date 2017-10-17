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

/*

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
    }*/




    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void setColorBar(@ColorInt int statusColor, int statusDepth,
                            @ColorInt int navColor, int navDepth) {
        setColorBar(statusColor, statusDepth, true, navColor, navDepth);
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void setColorBar(@ColorInt int statusColor, @ColorInt int navColor) {
        setColorBar(statusColor, 0, navColor, 0);
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void setColorStatusBar(@ColorInt int statusColor, int statusDepth) {
        setColorBar(statusColor, statusDepth, false, Color.BLACK, 255);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void setColorStatusBar(@ColorInt int statusColor) {
        setColorStatusBar(statusColor, 0);
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void setColorBarForDrawer(@ColorInt int statusColor, int statusDepth,
                                     @ColorInt int navColor, int navDepth) {
        setColorBarForDrawer(statusColor, statusDepth, true, navColor, navDepth);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void setColorBarForDrawer(@ColorInt int statusColor, @ColorInt int navColor) {
        setColorBarForDrawer(statusColor, 0, navColor, 0);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void setColorStatusBarForDrawer(@ColorInt int statusColor, int statusDepth) {
        setColorBarForDrawer(statusColor, statusDepth, false, Color.BLACK, 255);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void setColorStatusBarForDrawer(@ColorInt int statusColor) {
        setColorStatusBarForDrawer(statusColor, 0);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void setTransparentBar(@ColorInt int statusColor, int statusAlpha) {
        setTransparentBar(statusColor, statusAlpha, false, Color.BLACK, 255);
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void setTransparentBar(@ColorInt int statusColor, int statusAlpha,
                                  @ColorInt int navColor, int navAlpha) {
        setTransparentBar(statusColor, statusAlpha, true, navColor, navAlpha);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void setImmersionBar(boolean applyNav) {
        if (applyNav) {
            setTransparentBar(Color.TRANSPARENT, 0, Color.TRANSPARENT, 0);
        } else {
            setTransparentBar(Color.TRANSPARENT, 0);
        }
    }



    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void setHideBar(boolean applyNav) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            View decorView = activity.getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            if (applyNav) {
                option = option | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
            }
            decorView.setSystemUiVisibility(option);
        }
    }







    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void setColorBar(@ColorInt int statusColor, int statusDepth, boolean applyNav,
                             @ColorInt int navColor, int navDepth) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            int finalStatusColor = statusDepth == 0 ? statusColor : calculateColor(statusColor, statusDepth);
            window.setStatusBarColor(finalStatusColor);
            if (applyNav) {
                int finalNavColor = navDepth == 0 ? navColor : calculateColor(navColor, navDepth);
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                window.setNavigationBarColor(finalNavColor);
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            int finalStatusColor = statusDepth == 0 ? statusColor : calculateColor(statusColor, statusDepth);
            ViewGroup decorView = (ViewGroup) window.getDecorView();
            decorView.addView(createStatusBarView(activity, finalStatusColor));
            if (applyNav && navigationBarExist(activity)) {
                int finalNavColor = navDepth == 0 ? navColor : calculateColor(navColor, navDepth);
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                decorView.addView(createNavBarView(activity, finalNavColor));
            }
            setRootView(activity, true);
        }
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void setTransparentBar(@ColorInt int statusColor, int statusAlpha, boolean applyNav,
                                   @ColorInt int navColor, int navAlpha) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            View decorView = window.getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            int finalStatusColor = statusColor == 0 ? Color.TRANSPARENT :
                    Color.argb(statusAlpha, Color.red(statusColor),
                            Color.green(statusColor), Color.blue(statusColor));
            window.setStatusBarColor(finalStatusColor);
            if (applyNav) {
                option = option | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
                int finalNavColor = navColor == 0 ? Color.TRANSPARENT :
                        Color.argb(navAlpha, Color.red(navColor),
                                Color.green(navColor), Color.blue(navColor));
                window.setNavigationBarColor(finalNavColor);
            }
            decorView.setSystemUiVisibility(option);

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ViewGroup decorView = (ViewGroup) window.getDecorView();
            int finalStatusColor = statusColor == 0 ? Color.TRANSPARENT :
                    Color.argb(statusAlpha, Color.red(statusColor),
                            Color.green(statusColor), Color.blue(statusColor));
            decorView.addView(createStatusBarView(activity, finalStatusColor));
            if (applyNav && navigationBarExist(activity)) {
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                int finalNavColor = navColor == 0 ? Color.TRANSPARENT :
                        Color.argb(navAlpha, Color.red(navColor),
                                Color.green(navColor), Color.blue(navColor));
                decorView.addView(createNavBarView(activity, finalNavColor));
            }
        }

    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void setColorBarForDrawer(@ColorInt int statusColor, int statusDepth, boolean applyNav,
                                      @ColorInt int navColor, int navDepth) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            ViewGroup decorView = (ViewGroup) window.getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;

            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
            int finalStatusColor = statusDepth == 0 ? statusColor : calculateColor(statusColor, statusDepth);
            decorView.addView(createStatusBarView(activity, finalStatusColor), 0);
            if (applyNav && navigationBarExist(activity)) {
                int finalNavColor = navDepth == 0 ? navColor : calculateColor(navColor, navDepth);
                decorView.addView(createNavBarView(activity, finalNavColor), 1);
                option = option | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
            }
            decorView.setSystemUiVisibility(option);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ViewGroup decorView = (ViewGroup) window.getDecorView();
            int finalStatusColor = statusDepth == 0 ? statusColor : calculateColor(statusColor, statusDepth);
            decorView.addView(createStatusBarView(activity, finalStatusColor), 0);
            if (applyNav && navigationBarExist(activity)) {
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                int finalNavColor = navDepth == 0 ? navColor : calculateColor(navColor, navDepth);
                decorView.addView(createNavBarView(activity, finalNavColor), 1);
            }
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
