package com.example.ibra.newproject;

import android.util.Log;

/**
 * Created by lucie on 12/3/15.
 */
public class PinGenerator {
    int randomPIN = (int) ((Math.random()*9000)+1000);

    String PINString = String.valueOf(randomPIN);
}
