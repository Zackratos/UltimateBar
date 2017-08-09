package org.zackratos.ultimatebar;

import android.annotation.TargetApi;
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

/*
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

    }*/


    public static final int ONLY_STATUS_BAR = 0x100;

    public static final int ONLY_NAVIGATION_BAR = 0x11;

    public static final int BOTH_STATUS_NAVIGATION = 0x12;

    private int type = BOTH_STATUS_NAVIGATION;


    private Activity activity;
//    public UltimateBar() {}

    public UltimateBar(Activity activity) {
        this.activity = activity;
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void setColorBar(@ColorInt int color, int alpha) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            int alphaColor = alpha == 0 ? color : calculateColor(color, alpha);
            setWindowBarColorWithType(window, alphaColor);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            int alphaColor = alpha == 0 ? color : calculateColor(color, alpha);
            ViewGroup decorView = (ViewGroup) window.getDecorView();
            setWindowBarColorWithTypeKitkat(window, decorView, alphaColor);
            setRootView(activity, true);
        }
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void setColorBar(@ColorInt int color) {
        setColorBar(color, 0);
    }



    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void setColorBarForDrawer(@ColorInt int color, int alpha) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            ViewGroup decorView = (ViewGroup) window.getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            if (navigationBarExist(activity)) {
                option = option | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
            }
            decorView.setSystemUiVisibility(option);
            setWindowBarColorWithType(window, Color.TRANSPARENT);
            int alphaColor = alpha == 0 ? color : calculateColor(color, alpha);
            decorView.addView(createStatusBarView(activity, alphaColor), 0);
            if (navigationBarExist(activity)) {
                decorView.addView(createNavBarView(activity, alphaColor), 1);
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ViewGroup decorView = (ViewGroup) window.getDecorView();
            int alphaColor = alpha == 0 ? color : calculateColor(color, alpha);
            setColorBarForDrawerWithTypeKitkat(window, decorView, alphaColor);
        }
    }



    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void setColorBarForDrawer(@ColorInt int color) {
        setColorBarForDrawer(color, 0);
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void setTransparentBar(@ColorInt int color, int alpha) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            View decorView = window.getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);

            int finalColor = alpha == 0 ? Color.TRANSPARENT :
                    Color.argb(alpha, Color.red(color), Color.green(color), Color.blue(color));

            setWindowBarColorWithType(window, finalColor);

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ViewGroup decorView = (ViewGroup) window.getDecorView();
            int finalColor = alpha == 0 ? Color.TRANSPARENT :
                    Color.argb(alpha, Color.red(color), Color.green(color), Color.blue(color));
          setWindowBarColorWithTypeKitkat(window, decorView, finalColor);
        }

    }



    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void setImmersionBar() {
        setTransparentBar(Color.TRANSPARENT, 0);
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void setHintBar() {
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


    public void setType(int type) {
        this.type = type;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setWindowBarColorWithType(Window window, int color) {
        if (type == ONLY_STATUS_BAR) {
            window.setStatusBarColor(color);
        } else if (type == ONLY_NAVIGATION_BAR) {
            window.setNavigationBarColor(color);
        } else {
            window.setStatusBarColor(color);
            window.setNavigationBarColor(color);
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void setWindowBarColorWithTypeKitkat(Window window, ViewGroup decorView, int color) {
        if (type == ONLY_STATUS_BAR) {
            decorView.addView(createStatusBarView(activity, color));
        } else if (type == ONLY_NAVIGATION_BAR && navigationBarExist(activity)) {
            decorView.addView(createNavBarView(activity, color));
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        } else {
            decorView.addView(createStatusBarView(activity, color));
            if (navigationBarExist(activity)) {
                decorView.addView(createNavBarView(activity, color));
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void setColorBarForDrawerWithTypeKitkat(Window window, ViewGroup decorView, int color) {
        if (type == ONLY_STATUS_BAR) {
            decorView.addView(createStatusBarView(activity, color), 0);
        } else if (type == ONLY_NAVIGATION_BAR && navigationBarExist(activity)) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            decorView.addView(createNavBarView(activity, color), 1);
        } else {
            decorView.addView(createStatusBarView(activity, color), 0);
            if (navigationBarExist(activity)) {
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                decorView.addView(createNavBarView(activity, color), 1);
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
        ViewGroup parent = (ViewGroup) activity.findViewById(android.R.id.content);
        for (int i = 0, count = parent.getChildCount(); i < count; i++) {
            View childView = parent.getChildAt(i);
            if (childView instanceof ViewGroup) {
                childView.setFitsSystemWindows(fit);
                ((ViewGroup)childView).setClipToPadding(fit);
            }
        }
    }



    private int getStatusBarHeight(Context context) {
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }



    public int getNavigationHeight(Context context) {

        int resourceId = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }

}
