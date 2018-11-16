package com.github.zackratos.ultimatebar.sample;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import com.github.zackratos.ultimatebar.UltimateBar;


public class DrawableBarActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button theme1, theme2, theme3, theme4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        theme1 = findViewById(R.id.theme_1);
        theme2 = findViewById(R.id.theme_2);
        theme3 = findViewById(R.id.theme_3);
        theme4 = findViewById(R.id.theme_4);

        Drawable bg = ContextCompat.getDrawable(this, R.drawable.red_to_blue);
        drawableBar(bg);

        theme1.setOnClickListener(view -> drawableBar(new ColorDrawable(Color.RED)));
        theme2.setOnClickListener(view -> drawableBar(new ColorDrawable(Color.BLUE)));
        theme3.setOnClickListener(view -> drawableBar(bg));
        theme4.setOnClickListener(view -> {
            UltimateBar.Companion.with(this)
                    .statusDrawable(new ColorDrawable(Color.WHITE))
                    .statusDrawable2(new ColorDrawable(Color.parseColor("#CCCCCC")))
                    .statusDark(true)
                    .applyNavigation(true)
                    .navigationDrawable(new ColorDrawable(Color.WHITE))
                    .navigationDrawable2(new ColorDrawable(Color.parseColor("#CCCCCC")))
                    .navigationDark(true)
                    .create()
                    .drawableBar();
            toolbar.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
            toolbar.setTitleTextColor(Color.BLACK);
        });
    }

    private void drawableBar(Drawable drawable) {
        UltimateBar.Companion.with(this)
                .statusDrawable(drawable)
                .applyNavigation(true)
                .navigationDrawable(drawable)
                .create()
                .drawableBar();
        toolbar.setBackgroundDrawable(drawable);
        toolbar.setTitleTextColor(Color.WHITE);
    }


}
