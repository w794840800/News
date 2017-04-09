package comn.example.administrator.news.net;

import android.content.Context;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import comn.example.administrator.news.api.InfoService;
import comn.example.administrator.news.jean.weixinjinxuan;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/3/13.
 */

public class RetrofitManager1 {
   static OkHttpClient okHttpClient=new OkHttpClient.Builder()
            .connectTimeout(5000, TimeUnit.SECONDS)
            .readTimeout(5000,TimeUnit.SECONDS)
            .writeTimeout(5000,TimeUnit.SECONDS)
            .build();
   static Retrofit retrofit;
   static String baseUrl="";
    public static RetrofitManager1 retrofitManager;
    InfoService infoService;
static Context co;
    public RetrofitManager1(String baseUrl1) {

    retrofit=new Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();
      //   retrofit
        //retrofit=createRetrofit(baseUrl);
    infoService=retrofit.create(InfoService.class);

    }
/*public static Retrofit createRetrofit(String baseUrl){

      retrofit=new Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();
return retrofit;
}*/

      public static RetrofitManager1 getRetrofitManager(Context c, String baseUrl1){
             co=c;
          baseUrl=baseUrl1;
         // createRetrofit(baseUrl1);

          if (retrofitManager == null) {
              synchronized (RetrofitManager1.class) {
                  if (retrofitManager == null) {
                      retrofitManager = new RetrofitManager1(baseUrl);
                  }
              }
          }

         // Log.d("url",baseUrl);
          Toast.makeText(co, ""+baseUrl, Toast.LENGTH_SHORT).show();
          return retrofitManager;

      }
    public InfoService getInfoService(){
       // retrofit.baseUrl()
        return infoService;

    }

public void get(String type, Observer<weixinjinxuan> observer){

getInfoService().getInfo(type,"4e3754ff60bd8b8f9ae918f5b8fd3797","5","1")
.observeOn(AndroidSchedulers.mainThread())
.subscribeOn(Schedulers.io())
.subscribe(observer);

}


}




