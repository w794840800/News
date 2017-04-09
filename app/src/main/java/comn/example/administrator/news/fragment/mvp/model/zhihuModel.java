package comn.example.administrator.news.fragment.mvp.model;

import android.content.Context;

import comn.example.administrator.news.jean.zhihu;
import comn.example.administrator.news.net.RetrofitManager;
import comn.example.administrator.news.net.RetrofitManager1;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/4/8.
 */

public class zhihuModel {
    Context c;
    public zhihuModel(Context context){
c=context;
}
    public void load(String baseUrl,String date,Subscriber<zhihu> storiesBeanSubscriber) {
        RetrofitManager1.getRetrofitManager(c,baseUrl).getInfoService().getZhihuJson(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(storiesBeanSubscriber);
    }

}
