package comn.example.administrator.news;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import comn.example.administrator.news.inter.BlueService;
import comn.example.administrator.news.jean.douban;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SplashActivity extends AppCompatActivity {
ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
       // this.requestWindowFeature(Window.FEATURE_NO_TITLE);


        setContentView(R.layout.activity_splash);

        imageView= (ImageView) findViewById(R.id.imageView);

        final ObjectAnimator valueAnimator=ObjectAnimator.ofFloat(imageView,"rotation",
                0.5f,0.5f);
        valueAnimator.setDuration(2000);
        OkHttpClient okHttpClient=new OkHttpClient();

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://api.douban.com/v2/")
.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
               .client(okHttpClient)
                .build();
        retrofit.create(BlueService.class).getSearchBooks("小王子", "", 0, 3)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<douban>() {
                    @Override
                    public void onCompleted() {
                       // Toast.makeText()
                    }

                    @Override
                    public void onError(Throwable e) {
    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNext(douban douban) {
          Toast.makeText(getApplicationContext(),""+douban.getBooks().get(0).getAuthor(),Toast.LENGTH_LONG).show();
                    //    Toast.makeText(getApplicationContext(),"onnext"+,Toast.LENGTH_LONG).show();
                    }
                });
     /*   Call<douban >call= retrofit.create(BlueService.class).getSearchBooks
                ("小王子", "", 0, 3);*/
        //Observable<douban>observable=Observable.create()

      /*  call.enqueue(new Callback<douban>() {
            @Override
            public void onResponse(Call<douban> call, Response<douban> response) {

                Toast.makeText(getApplicationContext(),response.body().getBooks().get(0).getAlt_title()+" ",Toast.LENGTH_SHORT)
                        .show();
                Log.d("response size",response.body().getBooks().get(0).getAuthor()+"");
                valueAnimator.start();
            }

            @Override
            public void onFailure(Call<douban> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage()+" ",Toast.LENGTH_LONG)
                        .show();
                Log.d("response size",t.getMessage()+" "+" ");
                valueAnimator.start();
            }
        });
*/

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
}
