package org.zackratos.ultimatesample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import org.zackratos.ultimatebar.UltimateBar;

public class HintBarActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        ((ImageView) findViewById(R.id.image_view)).setImageResource(R.drawable.yurisa_2);
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            UltimateBar ultimateBar = new UltimateBar(this);
            ultimateBar.setHintBar();
        }
    }


    /*    @Override
    protected void initBar() {
        setHintBar();
    }*/
}
