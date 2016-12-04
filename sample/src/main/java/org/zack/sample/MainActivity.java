package org.zack.sample;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import org.zack.library.UltimateBar;
import org.zack.library.UltimateBarActivity;

public class MainActivity extends UltimateBarActivity implements View.OnClickListener {


    private Button colorBar, immersionBar1, immersionBar2,
            immersionBar3, hintBar1, hintBar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        tb.setBackgroundColor(ContextCompat.getColor(this, R.color.DeepSkyBlue));
        setSupportActionBar(tb);

        colorBar = (Button) findViewById(R.id.color_bar);
        immersionBar1 = (Button) findViewById(R.id.immersion_bar_1);
        immersionBar2 = (Button) findViewById(R.id.immersion_bar_2);
        immersionBar3 = (Button) findViewById(R.id.immersion_bar_3);
        hintBar1 = (Button) findViewById(R.id.hint_bar_1);
        hintBar2 = (Button) findViewById(R.id.hint_bar_2);

        colorBar.setOnClickListener(this);
        immersionBar1.setOnClickListener(this);
        immersionBar2.setOnClickListener(this);
        immersionBar3.setOnClickListener(this);
        hintBar1.setOnClickListener(this);
        hintBar2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.color_bar:
                startActivity(new Intent(this, ColorBarActivity.class));
                break;

            case R.id.immersion_bar_1:
                startActivity(new Intent(this, ImmersionBarActivity1.class));
                break;

            case R.id.immersion_bar_3:
                startActivity(new Intent(this, ImmersionBarActivity3.class));
                break;

            case R.id.immersion_bar_2:
                startActivity(new Intent(this, ImmersionBarActivity2.class));
                break;

            case R.id.hint_bar_1:
                startActivity(new Intent(this, HintBarActivity1.class));
                break;

            case R.id.hint_bar_2:
                startActivity(new Intent(this, HintBarActivity2.class));
        }
    }


    @Override
    protected void initBar() {
        UltimateBar ub = new UltimateBar(this);
        ub.setColorBar(ContextCompat.getColor(this, R.color.DeepSkyBlue));
    }
}
