package org.dmfs.tasks;

import android.app.Application;

import me.kiip.sdk.Kiip;

/**
 * Created by yalei on 3/29/2016.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Kiip kiip = Kiip.init(this, "e2faf01fb920a4712749fe54b07eb935", "c537da825d01d40594848c4050e24bdc");
        Kiip.setInstance(kiip);

        
    }
}