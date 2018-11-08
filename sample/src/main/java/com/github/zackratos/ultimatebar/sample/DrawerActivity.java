package com.github.zackratos.ultimatebar.sample;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.github.zackratos.ultimatebar.UltimateBar;

/**
 *
 * Created by Administrator on 2017/6/25.
 */

public class DrawerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        int color = ContextCompat.getColor(this, R.color.DeepSkyBlue);

//        UltimateBar.newDrawerBuilder()
//                .statusColor(color)     // 状态栏颜色
//                .statusDepth(0)         // 状态栏颜色深度
//                .applyNav(true)         // 是否应用到导航栏
//                .navColor(color)        // 导航栏颜色
//                .navDepth(0)            // 导航栏颜色深度
//                .build(this)
//                .apply();


        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.DeepSkyBlue));
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        View content = findViewById(R.id.content);
        View drawer = findViewById(R.id.drawer);
        Drawable drawable = new ColorDrawable(color);
        UltimateBar.Companion.with(this)
                .statusDrawable(drawable)
                .applyNavigation(true)
                .navigationDrawable(drawable)
                .create()
                .drawableBarDrawer(drawerLayout,
                        content,
                        drawer);

//        UltimateBar.newDrawerBuilder()
//                .statusColor(color)     // 状态栏颜色
//                .statusDepth(0)         // 状态栏颜色深度
//                .applyNav(true)         // 是否应用到导航栏
//                .navColor(color)        // 导航栏颜色
//                .navDepth(0)            // 导航栏颜色深度
//                .build(this)
//                .apply();
    }
}
