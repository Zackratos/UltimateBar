package org.zack.sample;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import org.zack.library.UltimateBar;
import org.zack.library.UltimateBarActivity;

public class ImmersionBarActivity3 extends UltimateBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ultimate_bar_3);

        ((ImageView) findViewById(R.id.image_view))
                .setImageResource(R.drawable.yurisa_3);
    }

    @Override
    protected void initBar() {
        UltimateBar ub = new UltimateBar(this);
        ub.setImmersionBar(Color.GREEN, 50);
    }
}
