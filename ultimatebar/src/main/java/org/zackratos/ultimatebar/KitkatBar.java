package org.zackratos.ultimatebar;

import android.support.annotation.ColorInt;

/**
 *
 * Created by Administrator on 2017/9/9.
 */

public class KitkatBar implements Bar {
    private Bar bar;

    public KitkatBar(Bar bar) {
        this.bar = bar;
    }

    @Override
    public void setColorBar(@ColorInt int color) {
        bar.setColorBar(color);
    }
}
