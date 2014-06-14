package us.shiroyama.android.myapplication;

import android.app.Application;

import proton.inject.Proton;

/**
 * Created by shiroyama on 2014/06/14.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Proton.initialize(this, new MyModule());
    }

}
