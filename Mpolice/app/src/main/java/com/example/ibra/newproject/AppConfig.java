package com.example.ibra.newproject;

import android.app.Application;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.parse.Parse;
import com.parse.ParseInstallation;

/**
 * Created by lucie on 12/4/15.
 */
public class AppConfig extends Application{
    public static final String PARSE_CHANNEL = "GET PIN";
    public static final int NOTIFICATION_ID = 1000;
    public static final String PARSE_APPLICATION_ID ="7uE5i8t1LBqdC3f5lmlsuCOaXgrZR4iBDtHXtVCO";
    public static final String PARSE_CLIENT_KEY ="Uqp5HP8zWmKJB8S5N6EzhDzdL8rW6EVZ9emkEAeP";


    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);
        Parse.initialize(this);
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }
}
