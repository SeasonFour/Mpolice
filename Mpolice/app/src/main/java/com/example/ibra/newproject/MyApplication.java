package com.example.ibra.newproject;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;

/**
 * Created by lucie on 11/16/15.
 */
public class MyApplication extends Application {

    //global variables and statements can be stored here
    @Override
    public void onCreate() {
        super.onCreate();
        //Parse.enableLocalDatastore(this);
        Parse.initialize(this, "7uE5i8t1LBqdC3f5lmlsuCOaXgrZR4iBDtHXtVCO", "Uqp5HP8zWmKJB8S5N6EzhDzdL8rW6EVZ9emkEAeP");
        ParseInstallation.getCurrentInstallation().saveInBackground();
        // Enable Local Datastore.
    }


}
