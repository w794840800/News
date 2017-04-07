package comn.example.administrator.news.net;

import com.bumptech.glide.Glide;

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

public class RetrofitManager {
OkHttpClient okHttpClient;
Retrofit retrofit;
    public static RetrofitManager retrofitManager;
    InfoService infoService;
    public RetrofitManager (String baseUrl) {
    okHttpClient=new OkHttpClient.Builder()
            .connectTimeout(5000, TimeUnit.SECONDS)
            .readTimeout(5000,TimeUnit.SECONDS)
            .writeTimeout(5000,TimeUnit.SECONDS)
            .build();
    retrofit=new Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    infoService=retrofit.create(InfoService.class);

    }
      public static RetrofitManager getRetrofitManager(String baseUrl){

          if (retrofitManager==null){
              retrofitManager=new RetrofitManager(baseUrl);

          }
          return retrofitManager;

      }
    public InfoService getInfoService(){
        return infoService;

    }

public void get(String type, Observer<weixinjinxuan> observer){

getInfoService().getInfo(type,"4e3754ff60bd8b8f9ae918f5b8fd3797","5","1")
.observeOn(AndroidSchedulers.mainThread())
.subscribeOn(Schedulers.io())
.subscribe(observer);

}


}




