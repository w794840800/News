package comn.example.administrator.news.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/4/11.
 */

public class testBroadCast1 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"i am news",Toast.LENGTH_SHORT).show();
    }
}
