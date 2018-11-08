package com.github.zackratos.ultimatebar.sample;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.github.zackratos.ultimatebar.UltimateBar;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button colorBar, immersionBar, transparentBar1,
            transparentBar2, hideBar1, hideBar2, drawerColorBar, multipleSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        @ColorInt
        int color = ContextCompat.getColor(this, R.color.DeepSkyBlue);

        Drawable drawable = new ColorDrawable(color);

        UltimateBar.Companion.with(this)
                .statusDark(false)                  // 状态栏灰色模式(Android 6.0+)，默认 flase
                .statusDrawable(drawable)           // 状态栏背景，默认 null
                .applyNavigation(true)              // 应用到导航栏，默认 flase
                .navigationDark(false)              // 导航栏灰色模式(Android 8.0+)，默认 false
                .navigationDrawable(drawable)       // 导航栏背景，默认 null
                .create()
                .drawableBar();

        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        tb.setBackgroundColor(ContextCompat.getColor(this, R.color.DeepSkyBlue));
        setSupportActionBar(tb);

        colorBar = (Button) findViewById(R.id.drawable_bar);
        immersionBar = (Button) findViewById(R.id.immersion_bar);
        transparentBar1 = (Button) findViewById(R.id.transparent_bar_1);
        transparentBar2 = (Button) findViewById(R.id.transparent_bar_2);
        hideBar1 = (Button) findViewById(R.id.hide_bar_1);
        hideBar2 = (Button) findViewById(R.id.hide_bar_2);
        drawerColorBar = (Button) findViewById(R.id.drawer_color_bar);
        multipleSet = findViewById(R.id.fragment);

        colorBar.setOnClickListener(this);
        immersionBar.setOnClickListener(this);
        transparentBar1.setOnClickListener(this);
        transparentBar2.setOnClickListener(this);
        hideBar1.setOnClickListener(this);
        hideBar2.setOnClickListener(this);
        drawerColorBar.setOnClickListener(this);
        multipleSet.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.drawable_bar:
                startActivity(new Intent(this, DrawableBarActivity.class));
                break;

            case R.id.immersion_bar:
                startActivity(new Intent(this, ImmersionBarActivity.class));
                break;

            case R.id.transparent_bar_1:
                startActivity(new Intent(this, TransparentBarActivity1.class));
                break;

            case R.id.transparent_bar_2:
                startActivity(new Intent(this, TransparentBarActivity2.class));
                break;

            case R.id.hide_bar_1:
                startActivity(new Intent(this, HideBarActivity1.class));
                break;

            case R.id.hide_bar_2:
                startActivity(new Intent(this, HideBarActivity2.class));
                break;

            case R.id.drawer_color_bar:
                startActivity(new Intent(this, DrawerActivity.class));
                break;
            case R.id.fragment:
                startActivity(new Intent(this, FragmentActivity.class));
                break;
            default:
                break;
        }
    }

}
