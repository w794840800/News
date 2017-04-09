package comn.example.administrator.news.fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;

import comn.example.administrator.news.R;
import comn.example.administrator.news.adapter.qiubaiRecyclerAdapter;
import comn.example.administrator.news.db.DaoUtils;
import comn.example.administrator.news.db.dateBaseSQLite;
import comn.example.administrator.news.jean.qiushibaike;

/**
 * Created by Administrator on 2017/3/31.
 */

public class qiubaiFragment extends BasicFragment {
  ArrayList<qiushibaike>qiushibaikeArrayList;
    DaoUtils daoUtils;
    dateBaseSQLite dateBaseSQLite;
   SQLiteDatabase sqLiteDatabase;
    Timer timer;
    RecyclerView qiubai_recycler;
    SwipeRefreshLayout qiubai_swipe;
    qiubaiRecyclerAdapter qiubaiRecyclerAdapter;
    @Override
    public View initView() {

        dateBaseSQLite=new dateBaseSQLite(mainActivity,null);
        daoUtils=new DaoUtils(getActivity(),dateBaseSQLite);
        //daoUtils=new DaoUtils(mainActivity)
        qiushibaikeArrayList=new ArrayList<>();
        View view= LayoutInflater.from(mainActivity).
                inflate(R.layout.qiubaifragment,null);
           qiubai_recycler= (RecyclerView) view.findViewById(R.id.recycler);
        qiubai_swipe= (SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh);
        qiubai_swipe.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        qiubaiRecyclerAdapter=new qiubaiRecyclerAdapter(mainActivity,qiushibaikeArrayList);
        qiubai_recycler.setLayoutManager(new LinearLayoutManager(mainActivity));
        qiubai_recycler.setAdapter(qiubaiRecyclerAdapter);
        qiubai_swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
               // Toast.makeText(mainActivity,"refresh",Toast.LENGTH_SHORT).show();
                int i= (int) ((Math.random()*35)+1);

                parseHTML(i);
            }
        });
        //判断数据库是否为空，不为空就进行数据库读取
        //为空就进行网络加载
        //直接读取给集合

        if (daoUtils.read("select * from qb").getCount()!=0){
            readFromDB(daoUtils.read("select * from qb"));
        }
        //否则进行网络连接
        else {
             qiubai_swipe.setRefreshing(true);


             parseHTML(5);

        }





        return view;
    }

    private void readFromDB(Cursor cursor) {
       qiushibaike qb;
        qiushibaikeArrayList.clear();
        while (cursor.moveToNext()){
            qb=new qiushibaike();
            String title=cursor.getString(cursor.getColumnIndex("title"));
            qb.setTitle(title);
            String picUrl=cursor.getString(cursor.getColumnIndex("Picurl"));
            qb.setPicurl(picUrl);
            String url=cursor.getString(cursor.getColumnIndex("url"));
            qb.setUrl(url);
          qiushibaikeArrayList.add(qb);
            Collections.reverse(qiushibaikeArrayList);
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
       initDate();

    }

    @Override
    public void initDate() {
       // super.initDate();
     //   parseHTML(4);

    }
   /* public void getInfoFromNet(){
        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .build();
        Request request=new Request.Builder()
                .url("http://www.qiushibaike.com/hot/")

                .build();

    okHttpClient.newCall(request).enqueue(new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {

        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
           parseHTML(response.body().string());
        }
    });


    }*/

    private void parseHTML(final int i) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document document=Jsoup.connect("http://www.qiushibaike.com/hot/page/"+i+"/?s=4970033")
                            .get();
                    //  Elements content=document.getElementsByClass("col1");

                    //Log.d("element content",""+content.size());

                    Elements elements=document.getElementsByClass("article block untagged mb15");
           qiushibaike qb;
                    for (int i=0;i<elements.size();i++){
                        qb=new qiushibaike();

                        Elements href=elements.get(i).getElementsByClass("contentHerf");
                        //Toast.makeText(getApplicationContext(),"href"+href.size(),Toast.LENGTH_SHORT).show();
                        Log.d("hrref",""+href.size());
                        if (href.size()!=0){
                            for (Element element4:href){
                            qb.setUrl("http://www.qiushibaike.com"+element4.attr("href"));
                               // Log.d("hrref",""+element4.attr("href"));
                          //
                            }
                        }


                        Elements elements1=elements.get(i).getElementsByClass("content");
                        if (elements1.size()!=0) {
                            for (Element element : elements1) {
                                qb.setTitle(element.text());
                                Log.d("element text",element.text());
                            }
                        }
                        Elements elements_thumb=elements.get(i).getElementsByClass("thumb");
                        if (elements_thumb!=null){
                            for (Element element:elements_thumb){
                                Elements elements_img=element.getElementsByTag("img");
                                for (Element element1:elements_img){
                                    Log.d("element url",element1.attr("src"));
                             qb.setPicurl(element1.attr("src"));

                                }


                            }

                        }

                        daoUtils.insert("insert into qb values(?,?,?)",new String[]{qb.getPicurl()
                        ,qb.getTitle(),qb.getUrl()});
             qiushibaikeArrayList.add(qb);


                    }

                    mainActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //倒序
                            Collections.reverse(qiushibaikeArrayList);
                            qiubaiRecyclerAdapter.setArrayList(qiushibaikeArrayList);
                            qiubaiRecyclerAdapter.notifyDataSetChanged();
                            qiubai_swipe.setRefreshing(false);
                        }
                    });
                    // Elements elements=document.select("a.contentHerf");
                    // Log.d("element size",elements.size()+" ");

//                    Log.d("thumb size", document.getElementsByTag("img").size()+" ");

                  /*     for (int i=0;i<elements.size();i++){
                           //Elements elements1=document.select(".thumb img[src$=img]");
                           Element element=elements.get(i);
                               element.
                           Log.d("content",""+element.text());
                          for (Element element2:elements1){

                               Log.d("img", "" + element2.attr("src"));

                           }
                       }
*/


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

}
