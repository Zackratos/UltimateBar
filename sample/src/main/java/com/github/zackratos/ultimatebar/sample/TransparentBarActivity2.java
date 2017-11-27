package com.github.zackratos.ultimatebar.sample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.github.zackratos.ultimatebar.library.UltimateBar;

public class TransparentBarActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
//        UltimateBar ultimateBar = new UltimateBar(this);
//        ultimateBar.setTransparentBar(Color.GREEN, 100, Color.RED, 100);
        UltimateBar.newTransparentBuilder()
                .statusColor(Color.GREEN)
                .statusAlpha(100)
                .applyNav(true)
                .navColor(Color.RED)
                .navAlpha(100)
                .build(this)
                .apply();

        ((ImageView) findViewById(R.id.image_view))
                .setImageResource(R.drawable.yurisa_3);
    }

/*    @Override
    protected void initBar() {
        setTransparentStatusBar(Color.GREEN, 50);
    }*/
}
