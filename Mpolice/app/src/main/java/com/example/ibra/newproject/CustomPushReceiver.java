package com.example.ibra.newproject;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.parse.ParsePushBroadcastReceiver;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by lucie on 12/4/15.
 */
public class CustomPushReceiver extends ParsePushBroadcastReceiver {
    private final String TAG = CustomPushReceiver.class.getSimpleName();
    private NotificationUtils notifUtils;
    private Intent parseIntent;

    public CustomPushReceiver(){
        super();
    }

    @Override
    protected void onPushReceive(Context context, Intent intent) {
        super.onPushReceive(context, intent);

        if (intent == null){
            try {
                JSONObject json;
                json = new JSONObject(intent.getExtras().getString("com.parse.Data"));
                parseIntent = intent;
                parsePushJson(context,json);

            } catch (JSONException e) {
                e.printStackTrace();
                Log.e(TAG, "Push message json exception: " + e.getMessage());
            }
        }
        return;
    }

    @Override
    protected void onPushDismiss(Context context, Intent intent) {
        super.onPushDismiss(context, intent);
    }

    @Override
    protected void onPushOpen(Context context, Intent intent) {
        super.onPushOpen(context, intent);
    }

    private void parsePushJson(Context context, JSONObject json){
        try {
            boolean isBackground = json.getBoolean("is_background");
            JSONObject data = json.getJSONObject("data");
            String title = data.getString("title");
            String message = data.getString("message");

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, "Push message json exception: " + e.getMessage());
        }
    }

    private void showNotifMessage(Context context, String title, String message, Intent intent){
        notifUtils = new NotificationUtils(context);
        intent.putExtras(parseIntent.getExtras());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        notifUtils.showNotifMsg(title, message, intent);
    }
}
