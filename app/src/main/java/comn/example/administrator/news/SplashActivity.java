package comn.example.administrator.news;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Environment;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.List;

import comn.example.administrator.news.inter.BlueService;
import comn.example.administrator.news.jean.douban;
import comn.example.administrator.news.mvp.view.MainActivity;
import comn.example.administrator.news.sp.SharedPreferences;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import utils.isConnection;


public class SplashActivity extends AppCompatActivity {

    ImageView imageView;
    Button skip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getContentResolver()
       // this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
         setContentView(R.layout.activity_splash);
        initView();
        AlphaAnimation alphaAnimation=new AlphaAnimation(0,1);
       // ScaleAnimation scaleAnimation=new ScaleAnimation(0,1,0,1, Animation.RELATIVE_TO_SELF)
        ScaleAnimation scaleAnimation=new ScaleAnimation(0,1,0,1);
        //点击跳过进入下一个活动
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
            }
        });



        final ObjectAnimator valueAnimator=ObjectAnimator.ofFloat(imageView,"translationX",
                0,100,1);
        valueAnimator.setDuration(2000);
                 valueAnimator.start();


               //定义动画监听在动画结束后进入新一个页面

        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                try {
                    Thread.sleep(5000);
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }

    private void initView() {
        skip= (Button) findViewById(R.id.button_skip);
        imageView= (ImageView) findViewById(R.id.imageView);
        Subscription observable=Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
         if (isConnection.isNetWork(SplashActivity.this)){
              OkHttpClient okHttpClient=new OkHttpClient.Builder()
                      .build();
                Request request=new Request.Builder()
                        .url("http://guolin.tech/api/bing_pic")
                        .build();
                try {
                    okhttp3.Response response =okHttpClient.newCall(request)
                            .execute();

                     android.content.SharedPreferences sharedPreferences = getSharedPreferences("url", MODE_PRIVATE);
                       android.content.SharedPreferences.Editor editor = sharedPreferences.edit();
                     String url=response.body().string();
                     editor.putString("url",url);
                    editor.commit();
                       subscriber.onNext(url);

                   } catch (IOException e) {
                    e.printStackTrace();
                }

             // Log.d("code",response.code()+"");
                //Toast.makeText(getApplicationContext(),""+response.code(),Toast.LENGTH_SHORT).show();
//                subscriber.onNext(response.body().string());
                }
else {
             android.content.SharedPreferences   sharedPreferences= getSharedPreferences("url",MODE_PRIVATE);
             String url=sharedPreferences.getString("url","");

             subscriber.onNext(url);
         }

            }
        })
               .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Glide.with(getApplicationContext())
                                .load(s)
                                .thumbnail(0.5f)
                                .into(imageView);


    /*  if (url!=null) {
           Glide.with(getApplicationContext()).load(url).into(imageView);
       }*/
             /*       {
           Glide.with(getApplicationContext())
                   .load(s)
                   .into(imageView);


       }*/
                    }
                });

    }

}
