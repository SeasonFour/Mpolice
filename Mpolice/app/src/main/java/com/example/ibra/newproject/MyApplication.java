package com.example.ibra.newproject;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;

/**
 * Created by lucie on 11/16/15.
 */
public class MyApplication extends Application {

    private static MyApplication mInstance;
    @Override
    public void onCreate() {
        mInstance = this;
        super.onCreate();

        ParseUtils.registerParse(this);
    }

    public static synchronized MyApplication getInstance(){
        return mInstance;
    }
}
