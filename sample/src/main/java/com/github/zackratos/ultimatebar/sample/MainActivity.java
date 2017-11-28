package com.github.zackratos.ultimatebar.sample;

import android.content.Intent;
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
            transparentBar2, hideBar1, hideBar2, drawerColorBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UltimateBar ultimateBar = new UltimateBar(this);

        @ColorInt int color = ContextCompat.getColor(this, R.color.DeepSkyBlue);
        ultimateBar.setColorBar(color, color);

        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        tb.setBackgroundColor(ContextCompat.getColor(this, R.color.DeepSkyBlue));
        setSupportActionBar(tb);

        colorBar = (Button) findViewById(R.id.color_bar);
        immersionBar = (Button) findViewById(R.id.immersion_bar);
        transparentBar1 = (Button) findViewById(R.id.transparent_bar_1);
        transparentBar2 = (Button) findViewById(R.id.transparent_bar_2);
        hideBar1 = (Button) findViewById(R.id.hide_bar_1);
        hideBar2 = (Button) findViewById(R.id.hide_bar_2);
        drawerColorBar = (Button) findViewById(R.id.drawer_color_bar);

        colorBar.setOnClickListener(this);
        immersionBar.setOnClickListener(this);
        transparentBar1.setOnClickListener(this);
        transparentBar2.setOnClickListener(this);
        hideBar1.setOnClickListener(this);
        hideBar2.setOnClickListener(this);
        drawerColorBar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.color_bar:
                startActivity(new Intent(this, ColorBarActivity.class));
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
            default:

                break;
        }
    }


/*    @Override
    protected void initBar() {
        setColorBar(ContextCompat.getColor(this, R.color.DeepSkyBlue));
    }*/
}
