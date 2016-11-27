package org.zack.sample;


import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;

import org.zack.library.UltimateBar;
import org.zack.library.UltimateBarActivity;

public class ImmersionBarActivity extends UltimateBarActivity {

    private View toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ultimate_bar);

        toolbar = findViewById(R.id.include_toolbar);
        toolbar.setVisibility(View.GONE);
    }



    @Override
    protected void initBar() {
        super.initBar();
        UltimateBar ultimateBar = new UltimateBar(this);
        ultimateBar.setImmersionBar(ContextCompat.getColor(this, R.color.DeepSkyBlue), 0);
    }
}
