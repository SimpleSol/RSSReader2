import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Intent;

import com.example.leon.rssreader2.service.SyncService;

/**
 * Created by Leon on 18.11.2015.
 */
public class AppDelegate extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        final AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC, 5000, 60000, PendingIntent
                .getService(getApplicationContext(), 100500,
                        new Intent(getApplicationContext(), SyncService.class),
                        PendingIntent.FLAG_CANCEL_CURRENT));

    }
}
