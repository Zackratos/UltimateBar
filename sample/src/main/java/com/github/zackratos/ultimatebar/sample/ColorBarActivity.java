package com.github.zackratos.ultimatebar.sample;

import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.github.zackratos.ultimatebar.library.UltimateBar;


public class ColorBarActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        UltimateBar.newColorBuilder()
                .statusColor(color(R.color.SpringGreen))
                .statusDepth(50)
                .applyNav(true)
                .navColor(color(R.color.DoderBlue))
                .navDepth(50)
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
