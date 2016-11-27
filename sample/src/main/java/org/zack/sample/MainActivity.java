package org.zack.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button colorBar, immersionBar, hintBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colorBar = (Button) findViewById(R.id.color_bar);
        immersionBar = (Button) findViewById(R.id.immersion_bar);
        hintBar = (Button) findViewById(R.id.hint_bar);

        colorBar.setOnClickListener(this);
        immersionBar.setOnClickListener(this);
        hintBar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.color_bar:
                startActivity(new Intent(this, ColorBarActivity.class));
                break;

            case R.id.immersion_bar:
                startActivity(new Intent(this, ImmersionBarActivity.class));
                break;

            case R.id.hint_bar:
                startActivity(new Intent(this, HintBarActivity.class));
                break;
        }
    }


}
