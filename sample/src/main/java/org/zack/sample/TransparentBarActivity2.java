package org.zack.sample;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;

import org.zack.library.UltimateBarActivity;

public class TransparentBarActivity2 extends UltimateBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        ((ImageView) findViewById(R.id.image_view))
                .setImageResource(R.drawable.yurisa_3);
    }

    @Override
    protected void initBar() {
        setTransparentBar(Color.GREEN, 50);
    }
}
