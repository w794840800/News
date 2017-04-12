package comn.example.administrator.news.service;

import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.icu.text.IDNA;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;

import comn.example.administrator.news.api.API;
import comn.example.administrator.news.db.DaoUtils;
import comn.example.administrator.news.db.dateBaseSQLite;
import comn.example.administrator.news.jean.zhihu;
import comn.example.administrator.news.jean.zhihuContent;
import comn.example.administrator.news.net.RetrofitManager1;
import comn.example.administrator.news.net.RetrofitManager2;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/4/9.
 */

public class CacheService extends Service {
  DaoUtils daoUtils;
    dateBaseSQLite dateBaseSQLite;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public void srartZhihuCache(int id){

     RetrofitManager2.getRetrofitManager
             (this,API.ZHIHU_CONTENT).getInfoService()
             .getZhihuContentJson(id+"")
             .subscribeOn(Schedulers.io())
     .observeOn(AndroidSchedulers.mainThread())
     .subscribe(new Action1<zhihuContent>() {
         @Override
         public void call(zhihuContent zhihu) {
             if (daoUtils.queryTime(zhihu.getTitle(),"zhihuhu")){
                 daoUtils.update("update zhihuhu set content=? where id=?",
                         new String[]{zhihu.getBody(),zhihu.getId()+""});
                  Toast.makeText(getApplicationContext(),"后台插入数据成功",Toast.LENGTH_SHORT)
                          .show();
              }

         }
     })
     ;

    }

    @Override
    public void onCreate() {
        super.onCreate();
    dateBaseSQLite=new dateBaseSQLite(this,"zhihuhu");
        daoUtils=new DaoUtils(this,dateBaseSQLite);
        IntentFilter intentFilter=new IntentFilter("com.localBroadCast.zhihuCache");
        LoalReceiver loalReceiver=new LoalReceiver();
        LocalBroadcastManager.getInstance(getApplicationContext())
                .registerReceiver(loalReceiver,intentFilter);

    }


    public class LoalReceiver extends BroadcastReceiver{
       @Override
       public void onReceive(Context context, Intent intent) {
              int zhihu_id=intent.getIntExtra("zhihu_id",0);
           srartZhihuCache(zhihu_id);
       }
   }
}
