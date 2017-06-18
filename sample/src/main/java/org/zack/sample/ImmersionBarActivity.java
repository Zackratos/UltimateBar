package org.zack.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import org.zack.library.UltimateBar;

public class ImmersionBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        UltimateBar ultimateBar = new UltimateBar(this);
        ultimateBar.setImmersionBar();

        ((ImageView) findViewById(R.id.image_view)).setImageResource(R.drawable.yurisa_1);
    }

/*    @Override
    protected void initBar() {
        setImmersionBar();
    }*/
}
