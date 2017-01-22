package org.zack.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.zack.library.UltimateBar;
import org.zack.library.UltimateBarActivity;

public class HintBarActivity2 extends UltimateBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ultimate_bar_3);
    }

    @Override
    protected void hintBar() {
        UltimateBar ub = new UltimateBar(this);
        ub.hintBar();
    }
}
