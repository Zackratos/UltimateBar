package org.zackratos.ultimatesample;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.zackratos.ultimatebar.UltimateBar;

public class TransparentBarActivity1 extends AppCompatActivity {

    private View toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        UltimateBar ultimateBar = new UltimateBar(this);
        ultimateBar.setTransparentStatusBar(Color.BLUE, 50);
        toolbar = findViewById(R.id.include_toolbar);
        toolbar.setVisibility(View.GONE);
    }


/*    @Override
    protected void initBar() {
        setTransparentBar(Color.BLUE, 50);
    }*/
}
