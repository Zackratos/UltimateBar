package com.github.zackratos.ultimatebar.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.github.zackratos.ultimatebar.UltimateBar;

public class ImmersionBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
//        UltimateBar.newImmersionBuilder()
//                .applyNav(true)         // 是否应用到导航栏
//                .build(this)
//                .apply();

//        ultimateBar3.setStatusBarImmersion(false);
        UltimateBar.Companion.with(this)
                .applyNavigation(true)
                .create()
                .immersionBar();

        ((ImageView) findViewById(R.id.image_view)).setImageResource(R.drawable.yurisa_5);
    }

/*    @Override
    protected void initBar() {
        setImmersionBar();
    }*/
}
