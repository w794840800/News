package comn.example.administrator.news.pages;

import android.app.Activity;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;


import comn.example.administrator.news.R;

import comn.example.administrator.news.adapter.HeadViewPagerAdapter;
import comn.example.administrator.news.adapter.pageRecyclerAdapter;
import comn.example.administrator.news.db.DaoUtils;
import comn.example.administrator.news.file.FileDao;
import comn.example.administrator.news.jean.weixinjinxuan;
import comn.example.administrator.news.net.RetrofitManager;
import comn.example.administrator.news.sp.SharedPreferences;
import comn.example.administrator.news.webViewActivity;
import rx.Observer;

/**
 * Created by Administrator on 2017/2/22.
 */

public class BasicPages implements SwipeRefreshLayout.OnRefreshListener{
    ViewPager viewPager_header;
    pageRecyclerAdapter pageRecyclerAdapter;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.arg1 == -1) {
                viewPager_header.setCurrentItem(0);
            } else {
                viewPager_header.setCurrentItem(msg.arg1);
            }
        }
    };
    String tableName;
    HeadViewPagerAdapter headViewPagerAdapter;
    int currentIndex;
    String url = "";
    Timer timer;
    ArrayList<ImageView> imageViews = new ArrayList<>();
    DaoUtils daoUtils;
    SwipeRefreshLayout swipeRefreshLayout;
    ArrayList<weixinjinxuan.NewslistBean> arrayBean = new ArrayList<>();
    //head 集合
    ArrayList<weixinjinxuan.NewslistBean> headList = new ArrayList<>();
    RecyclerView basic_recycler;
    View addView;
    Activity mainActivity;
    String type;

    public BasicPages(Activity activity, String u, String name, DaoUtils daoUtils1, String type) {
        headViewPagerAdapter = new HeadViewPagerAdapter(activity, headList);
        this.type = type;
        daoUtils = daoUtils1;
        tableName = name;
        url = u;
        mainActivity = activity;
        //返回recylerView給適配器
        addView = initView();
    }

    public void updateDate() {

        //进行网络请求前给当前Head下标设为第一个

        currentIndex = 0;
        //進行網絡請求
        RetrofitManager.getRetrofitManager(mainActivity,"https://api.tianapi.com/").get(type, new Observer<weixinjinxuan>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(weixinjinxuan weixinjinxuan) {
                insertDate(weixinjinxuan);
             mainActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                      //  Toast.makeText(mainActivity,"refresh",Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
        //myAdapter.notifyDataSetChanged();
    }

    public View initView() {
        View view = View.inflate(mainActivity, R.layout.basic_recycleview, null);
        basic_recycler = (RecyclerView) view.findViewById(R.id.recycler);
        basic_recycler.setItemAnimator(new DefaultItemAnimator());
        timer = new Timer();
        pageRecyclerAdapter=new pageRecyclerAdapter(mainActivity,handler,headViewPagerAdapter);
        viewPager_header=pageRecyclerAdapter.getHeadViewPager();
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorAccent);

        //一打开就先进行数据库读取进行判断
        //如果不为空就加载数据库内容
        Cursor cursor = daoUtils.read("select * from " + tableName);
        if (cursor.getCount() != 0) {
            arrayBean = daoUtils.getDateFromCursor(cursor);
            pageRecyclerAdapter.setArraylist(arrayBean);
            //设置头viewPager内容为所有数据的前三条
            headList.clear();
            for (int i = 0; i < 3; i++) {
                headList.add(arrayBean.get(i));
            }
            headViewPagerAdapter.setArrayList(headList);
            headViewPagerAdapter.notifyDataSetChanged();
        }
        //如果为空
        //设置一进入就刷新
        else {
            swipeRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    swipeRefreshLayout.setRefreshing(true);
                    updateDate();
                }
            });
        }
        swipeRefreshLayout.setOnRefreshListener(this);
        basic_recycler.setLayoutManager(new LinearLayoutManager(mainActivity));
        //myAdapter = new MyAdapter();

     //
        basic_recycler.setAdapter(pageRecyclerAdapter);
        return view;
    }

    @Override
    public void onRefresh() {
        updateDate();
    }


    //AsyncTask网络请求
    class MyAsynTask extends AsyncTask<Object, String, String> {


        @Override
        protected String doInBackground(Object... params) {
            String html = (String) params[0];
            BufferedReader reader = null;
            String result = null;
            StringBuffer sbf = new StringBuffer();
            // httpUrl = httpUrl + "?" + httpArg;

            try {
                URL url = new URL(html);
                HttpURLConnection connection = (HttpURLConnection) url
                        .openConnection();
                connection.setRequestMethod("GET");
                // 填入apikey到HTTP header
                // connection.setRequestProperty("apikey",  "您自己的apikey");
                connection.connect();
                InputStream is = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                String strRead = null;
                while ((strRead = reader.readLine()) != null) {
                    sbf.append(strRead);
                    sbf.append("\r\n");
                    //                publishProgress(strRead);
                }
                reader.close();
                result = sbf.toString();
                // daoUtils.insert("insert into "+tableName+"(content) values("+result+")");

//Log.d("result123",result);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            //super.onProgressUpdate(values);
            //       Toast.makeText(mainActivity,"yfghj",Toast.LENGTH_SHORT).show();

        }

        @Override
        protected void onPostExecute(String newslistBeen) {
            //super.onPostExecute(newslistBeen);
// Toast.makeText(mainActivity,newslistBeen,Toast.LENGTH_SHORT).show();
            new FileDao().write(tableName, mainActivity, newslistBeen);
            // daoUtils.insert("insert into "+tableName+ " values("+newslistBeen+")");
            Gson gson = new Gson();
            weixinjinxuan weixin = gson.fromJson(newslistBeen, weixinjinxuan.class);
            SharedPreferences.wirteSharedPreference(mainActivity, newslistBeen);
            swipeRefreshLayout.setRefreshing(false);
/*

if (weixin!=null) {
    ArrayList<weixinjinxuan.NewslistBean>linshiArrayList
            = (ArrayList<weixinjinxuan.NewslistBean>) weixin.getNewslist();



    for (weixinjinxuan.NewslistBean a : linshiArrayList) {
           if (!(daoUtils.queryTime(a.getTitle(),tableName))){

               daoUtils.insert("insert into " + tableName + " values(?,?,?,?,?)",
                       new String[]{
                               a.getCtime(), a.getTitle(),
                               a.getDescription(), a.getPicUrl(),
                               a.getUrl()});
           }

    }}

            arrayBean.clear();
            Cursor cursor=daoUtils.read("select *from "+tableName);
            while (cursor.moveToNext()){
                weixinjinxuan.NewslistBean aa=new weixinjinxuan.NewslistBean();
                aa.setUrl(cursor.getString(cursor.getColumnIndex("url")));
                aa.setPicUrl(cursor.getString(cursor.getColumnIndex("picUrl")));
                aa.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                aa.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                aa.setCtime(cursor.getString(cursor.getColumnIndex("ctime")));
                arrayBean.add(aa);
                //cursor.moveToNext();
            }
            cursor.close();
            setArraylist(arrayBean);
            // String content=new FileDao().read(mainActivity,"page 1.txt");
            myAdapter.notifyDataSetChanged();
*/
            insertDate(weixin);

        }
    }

    public void insertDate(weixinjinxuan wei) {

        swipeRefreshLayout.setRefreshing(false);

        if (wei != null) {
            ArrayList<weixinjinxuan.NewslistBean> linshiArrayList
                    = (ArrayList<weixinjinxuan.NewslistBean>) wei.getNewslist();
            for (weixinjinxuan.NewslistBean a : linshiArrayList) {
              //插入数据前根据标题名判断是否重复
                if (!(daoUtils.queryTime(a.getTitle(), tableName))) {
                    daoUtils.insert("insert into " + tableName + " values(?,?,?,?,?)",
                            new String[]{
                                    a.getCtime(), a.getTitle(),
                                    a.getDescription(), a.getPicUrl(),
                                    a.getUrl()});
                }

            }
        }
        arrayBean.clear();
        Cursor cursor = daoUtils.read("select *from " + tableName);
        while (cursor.moveToNext()) {
            weixinjinxuan.NewslistBean aa = new weixinjinxuan.NewslistBean();
            aa.setUrl(cursor.getString(cursor.getColumnIndex("url")));
            aa.setPicUrl(cursor.getString(cursor.getColumnIndex("picUrl")));
            aa.setDescription(cursor.getString(cursor.getColumnIndex("description")));
            aa.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            aa.setCtime(cursor.getString(cursor.getColumnIndex("ctime")));
            arrayBean.add(aa);
        }
        cursor.close();
        Collections.reverse(arrayBean);
        pageRecyclerAdapter.setArraylist(arrayBean);
       // myAdapter.notifyDataSetChanged();
        pageRecyclerAdapter.notifyDataSetChanged();
    }

    public  class MyTimerTask extends TimerTask {
        String name;
        int mPosition;

        public MyTimerTask(int i, String name1) {

            mPosition = i;
            name = name1;
        }

        public MyTimerTask(int i) {
            mPosition = i;
        }

        @Override
        public void run() {
            Message message = Message.obtain();
            if (currentIndex == 2) {
                message.arg1 = -1;
                Log.d("timeID", name);
            } else {

                message.arg1 = currentIndex + 1;

            }
            handler.sendMessage(message);
        }
    }

}
