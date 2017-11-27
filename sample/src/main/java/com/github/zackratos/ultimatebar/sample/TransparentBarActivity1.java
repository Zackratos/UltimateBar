package com.github.zackratos.ultimatebar.sample;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.github.zackratos.ultimatebar.UltimateBar;

public class TransparentBarActivity1 extends AppCompatActivity {

    private View toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
//        UltimateBar ultimateBar = new UltimateBar(this);
//        ultimateBar.setTransparentBar(Color.BLUE, 100, Color.GREEN, 100);
        UltimateBar.newTransparentBuilder()
                .statusColor(Color.BLUE)        // 状态栏颜色
                .statusAlpha(100)               // 状态栏透明度
                .applyNav(true)                 // 是否应用到导航栏
                .navColor(Color.GREEN)          // 导航栏颜色
                .navAlpha(100)                  // 导航栏透明度
                .build(this)
                .apply();

        toolbar = findViewById(R.id.include_toolbar);
        toolbar.setVisibility(View.GONE);
    }

}
