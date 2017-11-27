package com.github.zackratos.ultimatebar.sample;

import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.github.zackratos.ultimatebar.UltimateBar;


public class ColorBarActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        UltimateBar.newColorBuilder()
                .statusColor(color(R.color.SpringGreen))  // 状态栏颜色
                .statusDepth(50)                          // 状态栏颜色深度
                .applyNav(true)                           // 是否应用到导航栏
                .navColor(color(R.color.DoderBlue))       // 导航栏颜色
                .navDepth(50)                             // 导航栏颜色深度
                .build(this)
                .apply();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.SpringGreen));

    }

    @ColorInt
    private int color(@ColorRes int colorRes) {
        return ContextCompat.getColor(this, colorRes);
    }


}
