package com.example.ibra.newproject;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;

import java.util.List;

/**
 * Created by lucie on 12/4/15.
 */
public class NotificationUtils {
    private String TAG = NotificationUtils.class.getSimpleName();
    private Context mContext;

    public NotificationUtils(){

    }

    public NotificationUtils(Context mContext){
        this.mContext = mContext;
    }

    public void showNotifMsg(String title, String message,Intent intent){
        if (TextUtils.isEmpty(message))
            return;

        if (ifAppIsInBackground(mContext)){
            int icon = R.mipmap.ic_launcher;
            int mNotifId = AppConfig.NOTIFICATION_ID;

            PendingIntent pendingIntent = PendingIntent.getActivity(
                  mContext,
                    0,
                    intent,
                    PendingIntent.FLAG_CANCEL_CURRENT
            );

            NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mContext);
            Notification notif = mBuilder.setSmallIcon(icon).setTicker(title).setWhen(0)
                    .setAutoCancel(true)
                    .setContentTitle(title)
                    .setStyle(inboxStyle)
                    .setContentIntent(pendingIntent)
                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                    .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), icon))
                    .setContentText(message)
                    .build();

            NotificationManager notifManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
            notifManager.notify(mNotifId, notif);
        } else {
            intent.putExtra("title", title);
            intent.putExtra("message", message);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            mContext.startActivity(intent);
        }
    }

    public static boolean ifAppIsInBackground(Context context){
        boolean isInBackground = true;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH){
            List<ActivityManager.RunningAppProcessInfo> runningProcesses = am.getRunningAppProcesses();
            for (ActivityManager.RunningAppProcessInfo processInfo : runningProcesses){
                if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND){
                    for (String activeproces : processInfo.pkgList){
                        if (activeproces.equals(context.getPackageName())){
                            isInBackground = false;
                        }
                    }
                }
            }
        }else {
            List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
            ComponentName componentName= taskInfo.get(0).topActivity;
            if (componentName.getPackageName().equals(context.getPackageName())){
                isInBackground = false;
            }
        }
        return isInBackground;
    }
}
