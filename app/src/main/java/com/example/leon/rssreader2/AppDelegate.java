package com.example.leon.rssreader2;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Intent;
import android.text.format.DateUtils;

import com.example.leon.rssreader2.service.SyncService;

/**
 * Created by Leon on 18.11.2015.
 */
public class AppDelegate extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        final AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC, 5000, 5 * DateUtils.MINUTE_IN_MILLIS, PendingIntent
                .getService(getApplicationContext(), 100500,
                        new Intent(getApplicationContext(), SyncService.class),
                        PendingIntent.FLAG_CANCEL_CURRENT));

    }
}
