package com.example.projekt;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

public class Notification {
    public static final String CHANNEL_ID_LOW="low_channel";
    public static final String CHANNEL_ID_DEFAULT="default_channel";
    public static final String CHANNEL_ID_HIGH="high_channel";
    public static final String CHANNEL_NAME="Kanał";
    public static void createNotificationChannels(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService((Context.NOTIFICATION_SERVICE));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            NotificationChannel channellow = new NotificationChannel(CHANNEL_ID_LOW, CHANNEL_NAME, NotificationManager.IMPORTANCE_LOW);
            NotificationChannel channeldefault = new NotificationChannel(CHANNEL_ID_DEFAULT, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            NotificationChannel channelhigh = new NotificationChannel(CHANNEL_ID_HIGH, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            if(notificationManager != null) {
                notificationManager.createNotificationChannel(channellow);
                notificationManager.createNotificationChannel(channeldefault);
                notificationManager.createNotificationChannel(channelhigh);
            }
        }
    }

    public static void sendNotification(int NOTIFICATION_ID, String CHANNEL_ID, AppCompatActivity activity,String title,String message, int styleType, int largeIconResID){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            if(ContextCompat.checkSelfPermission(activity, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.POST_NOTIFICATIONS}, 100);
                return;
            }
        }
        NotificationManager notificationManager = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(activity,CHANNEL_ID)
                .setSmallIcon(R.drawable.obraz)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        switch (styleType){
            case 1:
                builder.setStyle(new NotificationCompat.BigTextStyle().bigText(message));
                break;
            case 2:
                if(largeIconResID != 0){
                    Bitmap largeicon = BitmapFactory.decodeResource(activity.getResources(), largeIconResID);
                    builder.setLargeIcon(largeicon);
                    builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(largeicon));
                }
                break;
            case 3:
                NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
                inboxStyle.addLine(message);
                inboxStyle.addLine("Dodatkowa dodana linia tekstu");
                builder.setStyle(inboxStyle);
                break;
        }

        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }


}
