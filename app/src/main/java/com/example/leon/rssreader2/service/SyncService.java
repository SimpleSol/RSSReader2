package com.example.leon.rssreader2.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Leon on 30.11.2015.
 */
public class SyncService extends IntentService {

    public SyncService() {
        super("SyncService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("SyncService", "onHandleIntent");
    }
}


