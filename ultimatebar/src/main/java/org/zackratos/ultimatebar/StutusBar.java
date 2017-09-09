package org.zackratos.ultimatebar;

import android.app.Activity;
import android.support.annotation.ColorInt;

/**
 *
 * Created by Administrator on 2017/9/9.
 */

public class StutusBar implements Bar {

    private Activity activity;

    public StutusBar(Activity activity) {
        this.activity = activity;
    }


    @Override
    public void setColorBar(@ColorInt int color) {

    }
}
