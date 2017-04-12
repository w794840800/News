package comn.example.administrator.news.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

import comn.example.administrator.news.R;
import comn.example.administrator.news.mvp.view.MainActivity;

/**
 * Created by Administrator on 2017/4/10.
 */

public class foreNotificationService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Intent intent=new Intent(this, MainActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,1,intent,0);
        Notification notification=new Notification.Builder(getApplicationContext())
                .setContentText("i am contentText")
                .setContentTitle("i am contentTitle")
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.ic_launcher)
                .build();
            startForeground(1,notification);
    }

    @Override
    public int onStartCommand(Intent intent,  int flags, int startId) {


        return START_STICKY;

    }

    @Override
    public void onDestroy() {

        super.onDestroy();

    }
}
