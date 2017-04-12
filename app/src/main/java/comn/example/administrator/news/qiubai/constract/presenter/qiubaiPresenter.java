package comn.example.administrator.news.qiubai.constract.presenter;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;

import comn.example.administrator.news.db.DaoUtils;
import comn.example.administrator.news.db.dateBaseSQLite;
import comn.example.administrator.news.jean.qiushibaike;
import comn.example.administrator.news.net.ConnectionUtils;
import comn.example.administrator.news.qiubai.constract.constract.qiubaiConstract;
import comn.example.administrator.news.qiubai.constract.model.qiubaiModel;

/**
 * Created by Administrator on 2017/4/11.
 */

public class qiubaiPresenter implements qiubaiConstract.Presenter {
    qiubaiModel qiubaiModel;
    Context context;
    DaoUtils daoUtils;
    dateBaseSQLite dateBaseSQLite;
    qiubaiConstract.View view;
    ArrayList<qiushibaike> qiushibaikeArrayList;

    public qiubaiPresenter(Context context, qiubaiConstract.View view) {
        qiubaiModel = new qiubaiModel();
        this.view = view;
        qiushibaikeArrayList = new ArrayList<>();
        view.setpresenter(this);
        dateBaseSQLite=new dateBaseSQLite(context,null);
        daoUtils=new DaoUtils(context,dateBaseSQLite);
        this.context = context;

    }

    @Override
    public void start() {
        showLoading(true);
    }

    @Override
    public void showLoading(final boolean isLoadingMore) {

        if (ConnectionUtils.isNetWork(context)){
            if (isLoadingMore) {
                view.showLoading();
            }
            qiubaiModel.load(null, ((int) ((Math.random() * 35) + 1)),
                    new qiubaiModel.onConnectionFinish() {
                        @Override
                        public void onSuccess(ArrayList<qiushibaike>qiushibaikes) {
                              if (isLoadingMore){
                                  qiushibaikeArrayList.clear();
                              }
                          //  Collections.reverse(qiushibaikes);
                                  for (qiushibaike qiushibaike:qiushibaikes){

                                      if (!(daoUtils.queryTime(qiushibaike.getTitle(),"qb"))){

                                         daoUtils.insert("insert into qb " +
                                                 "values(?,?,?)",new String[]{qiushibaike.getPicurl()
                                         ,qiushibaike.getTitle(),qiushibaike.getUrl()});
                                          qiushibaikeArrayList.add(qiushibaike);
                                      }

                                  }

                                view.stopLoading();

                                      view.showResult(qiushibaikeArrayList);

                              }


                        @Override
                        public void onFailured() {
                            view.stopLoading();
                            view.shoeError();
                        }
                    },isLoadingMore);

    }
    else {
if (isLoadingMore){
            qiushibaikeArrayList.clear();
        }
            qiushibaike qiushibaike;
            Cursor cursor=daoUtils.read("select * from qb");
            while (cursor.moveToNext()){
                qiushibaike=new qiushibaike();
                String picUrl=cursor.getString(cursor.getColumnIndex("Picurl"));
                String title=cursor.getString(cursor.getColumnIndex("title"));
                String url=cursor.getString(cursor.getColumnIndex("url"));
                qiushibaike.setUrl(url);
                qiushibaike.setPicurl(picUrl);
                qiushibaike.setTitle(title);
            qiushibaikeArrayList.add(qiushibaike);
            }
        cursor.close();
     view.showResult(qiushibaikeArrayList);
    }

    }

    @Override
    public void refresh() {
    showLoading(true);
    }

    @Override
    public void loadMore() {
showLoading(false);
    }
}
