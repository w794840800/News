package comn.example.administrator.news.fragment.mvp.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.webkit.WebView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import comn.example.administrator.news.api.API;
import comn.example.administrator.news.db.DaoUtils;
import comn.example.administrator.news.db.dateBaseSQLite;
import comn.example.administrator.news.fragment.mvp.constract.zhihuConstract;
import comn.example.administrator.news.fragment.mvp.model.zhihuModel;
import comn.example.administrator.news.jean.zhihu;
import comn.example.administrator.news.net.ConnectionUtils;
import comn.example.administrator.news.webViewActivity;
import rx.Subscriber;

/**
 * Created by Administrator on 2017/4/8.
 */

public class zhihuPresenter implements zhihuConstract.Presenter {
    DaoUtils daoUtils;
    Context context;
    dateBaseSQLite dateBaseSQLite;
    zhihuConstract.View view;
    ArrayList<zhihu.StoriesBean> arrayList = new ArrayList<>();
    zhihuModel zhihuModel;

    public zhihuPresenter(Activity context, zhihuConstract.View view1) {
        this.context = context;
        dateBaseSQLite = new dateBaseSQLite(context, null);
        daoUtils = new DaoUtils(context, dateBaseSQLite);
        zhihuModel = new zhihuModel(context);
        view = view1;
        view.setpresenter(this);
Log.d("test","gouzhao"+"");
    }

    @Override
    public void start() {
        loadDate(longDateToString(Calendar.getInstance().getTimeInMillis()),true);
    }

    @Override
    public void loadDate(String date,final boolean isContinuteAdd) {
        if (isContinuteAdd) {
            view.ShowLoading();
        }


        if (ConnectionUtils.isNetWork(context)) {

            zhihuModel.load(API.ZHIHU_URL, date, new Subscriber<zhihu>() {
                @Override
                public void onCompleted() {
                    view.showResult(arrayList);
                    view.stopLoading();
                   // Toast.makeText(context,"huhuFinish",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onError(Throwable e) {
                    Log.d("error",e.getMessage());
                    view.stopLoading();
                    view.showError();

                }

                @Override
                public void onNext(zhihu zhihu) {
                    if (isContinuteAdd) {
                        arrayList.clear();
                    }

                    ArrayList<zhihu.StoriesBean> storiesBeanArrayList =
                            (ArrayList<comn.example.administrator.news.
                                    jean.zhihu.StoriesBean>) zhihu.getStories();
                    for (zhihu.StoriesBean bean : storiesBeanArrayList) {
                        arrayList.add(bean);

                        if (!isIdExitDB(bean.getTitle())) {


                            daoUtils.insert("insert into zhihuhu values(?,?,?,?)",
                                    new String[]{bean.getImages().get(0).toString(),
                                            bean.getTitle(), bean.getId() + "",null}
                            );
                        }
                        Intent intent=new Intent("com.localBroadCast.zhihuCache");
                        intent.putExtra("zhihu_id",bean.getId());
                      LocalBroadcastManager.getInstance(context)
                                .sendBroadcast(intent);
                    }
                }
            });

        } else {
            if (isContinuteAdd) {
                arrayList.clear();
            }
            Cursor cursor = daoUtils.read("select * from zhihuhu");
            zhihu.StoriesBean storiesBean;
            ArrayList<String> images;
            while (cursor.moveToNext()) {
                storiesBean = new zhihu.StoriesBean();

                String id = cursor.getString(cursor.getColumnIndex("id"));
                storiesBean.setId(Integer.parseInt(id));
                images = new ArrayList<>();
                String image = cursor.getString(cursor.getColumnIndex("images"));
                images.add(image);
                storiesBean.setImages(images);
                String title = cursor.getString(cursor.getColumnIndex("title"));
                storiesBean.setTitle(title);
                arrayList.add(storiesBean);
            }
            view.stopLoading();
            view.showResult(arrayList);
            cursor.close();

        }

    }


    public boolean isIdExitDB(String title) {

        return daoUtils.queryTime(title, "zhihuhu");


    }

    @Override
    public void refresh() {
        loadDate(longDateToString(Calendar.getInstance().getTimeInMillis()),true);
    }



    @Override
    public void loadMore(long date) {
        loadDate(longDateToString(date),false);

    }

    @Override
    public void showwDetail(int position) {

        webViewActivity.newIntent(context,API.ZHIHU_CONTENT+arrayList.get(position)
        .getId());
    }
    public String longDateToString(long date){
        Date d=new Date(date+24*60*60*1000);
String end;
        SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");
        end=format.format(d);
        return end;
    }
}
