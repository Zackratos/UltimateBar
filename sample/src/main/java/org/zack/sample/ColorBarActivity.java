package org.zack.sample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;

import org.zack.library.UltimateBar;
import org.zack.library.UltimateBarActivity;

public class ColorBarActivity extends UltimateBarActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setTheme(R.style.UltimateBaseTheme_ColorBar);
        setContentView(R.layout.activity_ultimate_bar);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(Color.GREEN);

    }


    @Override
    protected void initBar() {
        UltimateBar ultimateBar = new UltimateBar(this);
        ultimateBar.setColorBar(Color.GREEN, 100);
    }
}
