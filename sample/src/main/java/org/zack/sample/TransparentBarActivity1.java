package org.zack.sample;


import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import org.zack.library.UltimateBarActivity;

public class TransparentBarActivity1 extends UltimateBarActivity {

    private View toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        toolbar = findViewById(R.id.include_toolbar);
        toolbar.setVisibility(View.GONE);
    }



    @Override
    protected void initBar() {
        setTransparentBar(Color.BLUE, 50);
    }
}
