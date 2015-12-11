package com.example.leon.rssreader2.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.net.ConnectivityManagerCompat;
import android.util.Log;

import com.example.leon.rssreader2.R;
import com.example.leon.rssreader2.activity.MainActivity;

/**
 * Created by Leon on 04.12.2015.
 */
public class NetworkWatcher extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = ConnectivityManagerCompat.getNetworkInfoFromBroadcast(cm, intent);

        if (networkInfo.isConnectedOrConnecting()) {
            sendNotification(context);
        }
    }

    private void sendNotification(Context context) {
        final Intent intent = new Intent(context, MainActivity.class);
        final PendingIntent activity = PendingIntent.getActivity(context, 100501, intent, PendingIntent.FLAG_ONE_SHOT);
        final Notification notification = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))
                .setContentTitle("Notification Title")
                .setContentText("Notification Text")
                .setTicker("Status Ticker")
                .setStyle(new NotificationCompat.BigTextStyle().bigText("sldkfja;sldkj a;lj asl" +
                        ";j asl jaasdklfal;sdfj aslj aslkj as"))
                .setContentIntent(activity)
                .build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        NotificationManagerCompat.from(context).notify(100500, notification);
    }

}





















