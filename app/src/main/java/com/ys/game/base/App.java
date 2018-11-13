package com.ys.game.base;

import android.support.multidex.MultiDexApplication;

import com.ys.game.util.Constant;

/**
 * @author lh
 * @version 1.0.0
 * @filename App
 * @description -------------------------------------------------------
 * @date 2018/10/23 16:01
 */
public class App extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        Constant.buildFile();
    }
}
