package comn.example.administrator.news;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.mob.commons.SHARESDK;

import cn.sharesdk.framework.ShareSDK;

/**
 * Created by Administrator on 2017/2/27.
 */

public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
       // SHARESDK.setAppKey("4eb3c69e4b253e992b325f82c3034bcd");
        ShareSDK.initSDK(getApplicationContext(),
                "4eb3c69e4b253e992b325f82c3034bcd");
        Fresco.initialize(getApplicationContext());
    }
}
