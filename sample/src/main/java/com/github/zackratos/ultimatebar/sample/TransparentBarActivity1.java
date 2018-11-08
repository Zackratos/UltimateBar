package com.github.zackratos.ultimatebar.sample;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.github.zackratos.ultimatebar.UltimateBar;


public class TransparentBarActivity1 extends AppCompatActivity {

    private View toolbar;
    private Button theme1, theme2, theme3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

//        Drawable statusBg = new ColorDrawable(Color.parseColor("#550000FF"));
//        Drawable navigationBg = new ColorDrawable(Color.parseColor("#5500FF00"));
//        UltimateBar.Companion.with(this)
//                .statusDrawable(statusBg)
//                .applyNavigation(true)
//                .navigationDrawable(navigationBg)
//                .create()
//                .transparentBar();

        toolbar = findViewById(R.id.include_toolbar);
        toolbar.setVisibility(View.GONE);
        theme1 = findViewById(R.id.theme_1);
        theme2 = findViewById(R.id.theme_2);
        theme3 = findViewById(R.id.theme_3);

        Drawable statusDrawable = ContextCompat.getDrawable(this, R.drawable.red_to_blue_tran);
        Drawable navigationDrawable = ContextCompat.getDrawable(this, R.drawable.bule_to_red_tran);

        theme1.setOnClickListener(view ->
                UltimateBar.Companion.with(this)
                        .statusDrawable(new ColorDrawable(Color.parseColor("#550000FF")))
                        .applyNavigation(true)
                        .navigationDrawable(new ColorDrawable(Color.parseColor("#5500FF00")))
                        .create()
                        .transparentBar());
        theme2.setOnClickListener(view ->
                UltimateBar.Companion.with(this)
                        .statusDrawable(new ColorDrawable(Color.parseColor("#55FF00FF")))
                        .applyNavigation(true)
                        .navigationDrawable(new ColorDrawable(Color.parseColor("#55FFFF00")))
                        .create()
                        .transparentBar());
        theme3.setOnClickListener(view ->
                UltimateBar.Companion.with(this)
                        .statusDrawable(statusDrawable)
                        .statusDark(true)
                        .applyNavigation(true)
                        .navigationDrawable(navigationDrawable)
                        .create()
                        .transparentBar());

        UltimateBar.Companion.with(this)
                .statusDrawable(statusDrawable)
                .applyNavigation(true)
                .navigationDrawable(navigationDrawable)
                .create()
                .transparentBar();

    }



//    private void transparentBar() {
//        UltimateBar.Companion.with(this)
//                .statusDrawable(statusBg)
//                .applyNavigation(true)
//                .navigationDrawable(navigationBg)
//                .create()
//                .transparentBar();
//    }

}
