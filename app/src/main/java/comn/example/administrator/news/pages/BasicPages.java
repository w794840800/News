package comn.example.administrator.news.pages;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.DraweeView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

import comn.example.administrator.news.R;
import comn.example.administrator.news.db.DaoUtils;
import comn.example.administrator.news.db.dateBaseSQLite;
import comn.example.administrator.news.file.FileDao;
import comn.example.administrator.news.jean.weixinjinxuan;
import comn.example.administrator.news.sp.SharedPreferences;
import comn.example.administrator.news.webViewActivity;
import utils.parseGson;
import utils.urlConnection;

/**
 * Created by Administrator on 2017/2/22.
 */

public class BasicPages {
   // DaoUtils daoUtils;
    String tableName;
   String url="";
    SQLiteDatabase sqLiteDatabase1;
    dateBaseSQLite dataBaseSQLite=null;
    MyAsynTask myAsynTask=null;
    // public static BasicPages mBasicPages;
  //  private  ArrayAdapter recyclerAdapter;
   MyAdapter myAdapter;
    DaoUtils daoUtils;
    SwipeRefreshLayout swipeRefreshLayout;
    ArrayList<weixinjinxuan.NewslistBean> arrayBean=new ArrayList<>();
    RecyclerView basic_recycler;
        View addView;
    Activity mainActivity;

    public BasicPages(Activity activity,String u,String name,DaoUtils daoUtils1)
    {
       // arrayBean
        daoUtils=daoUtils1;
        tableName=name;
         Cursor cursor=daoUtils.read("select * from "+tableName);
          while (cursor.moveToNext()){
              weixinjinxuan.NewslistBean  bean=new weixinjinxuan.NewslistBean();
              bean.setCtime(cursor.getString(0));
              bean.setDescription(cursor.getString(2));
              bean.setTitle(cursor.getString(1));
              bean.setPicUrl(cursor.getString(3));
              bean.setUrl(cursor.getString(4));
             // Toast.makeText(activity, ""+cursor.getString(5), Toast.LENGTH_SHORT).show();
              arrayBean.add(bean);
              //cursor.moveToNext();

          }

        //dataBaseSQLite=new dateBaseSQLite(mainActivity,name);
     //   daoUtils=new DaoUtils(mainActivity,dataBaseSQLite);
       //sqLiteDatabase1=dataBaseSQLite.getWritableDatabase();
        url=u;

//        new dateBaseSQLite(mainActivity,name).getWritableDatabase();
  //new dateBaseSQLite(mainActivity,"first")
    //      .getWritableDatabase();
        /*array.add("index1");

        for(int i=0;i<40;i++){
        array.add("index");*/
        //}
        mainActivity=activity;




        addView=initView();
       // updateDate();


    }
    public void updateDate(){
       /* if (myAsynTask.getStatus()== AsyncTask.Status.RUNNING) {
            myAsynTask.cancel(true);
        }*/
       // Toast.makeText(mainActivity, "begin", Toast.LENGTH_SHORT).show();
      /* new Thread(new Runnable() {
           @Override
           public void run() {



               arrayBean= (ArrayList<weixinjinxuan.NewslistBean>) urlConnection.
                       getJsonDate
                               (myAdapter,"https://api.tianapi.com/wxnew/?" +
                                       "key=4e3754ff60bd8b8f9ae918f5b8fd3797&num=10");

           }
       }).start();*/
       /* MyAsynTask myAsynTask= (MyAsynTask) new MyAsynTask().execute("https://api.tianapi.com/wxnew/?" +
                "key=4e3754ff60bd8b8f9ae918f5b8fd3797&num=10");*/
        /*new MyAsynTask().execute("https://www.baidu.com");*/
        //Toast.makeText(mainActivity, "end", Toast.LENGTH_SHORT).show();
  new MyAsynTask().execute(url);
        //myAsynTask.execute(url);

        myAdapter.notifyDataSetChanged();
            // myAsynTask.cancel(true);
//        myAdapter.notifyDataSetChanged();
 // myAsynTask.cancel(true);
    }

    public View initView() {
        View view=View.inflate(mainActivity, R.layout.basic_recycleview,null);
        basic_recycler= (RecyclerView)view.findViewById(R.id.recycler);
        swipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);

        //设置一进入就刷新
       // swipeRefreshLayout.measure(0,0);
        swipeRefreshLayout.setRefreshing(true);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
         new Thread(new Runnable() {
             @Override
             public void run() {
                 try {
                     Thread.sleep(8000);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
             }
         }).start();
                mainActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateDate();

                    }
                });
            }
        });


      //  myAsynTask=new MyAsynTask();
           //  basic_recycler.setAdapter();
        basic_recycler.setLayoutManager(new LinearLayoutManager(mainActivity));
        myAdapter=new MyAdapter();
    //  basic_recycler.setAdapter(new MyAdapter());
        basic_recycler.setAdapter(myAdapter);
//myAsynTask=new MyAsynTask();
        return view;
    }
    public void setArraylist(ArrayList<weixinjinxuan.NewslistBean>a){
 // Toast.makeText(mainActivity,a.get(0).getTitle(),Toast.LENGTH_SHORT).show();

        arrayBean=a;
        myAdapter.notifyDataSetChanged();

    }

  public class MyViewHolder extends RecyclerView.ViewHolder {
      TextView textView;
      SimpleDraweeView draweeView;
      public MyViewHolder(View itemView) {
          super(itemView);
          textView = (TextView) itemView.findViewById(R.id.tv_news_detail_title);
        draweeView= (SimpleDraweeView) itemView.findViewById(R.id.draweeView);
      }
  }
   public class  MyAdapter extends RecyclerView.Adapter<MyViewHolder>{
       @Override
       public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
           View view=View.inflate(mainActivity,R.layout.item_news_detail,null);
           return new MyViewHolder(view);
       }

       @Override
       public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    Intent intent=new Intent(mainActivity,webView)
                webViewActivity.newIntent(mainActivity,
                        arrayBean.get(position).getUrl());

            }
        });
           holder.textView.setText(arrayBean.get(position).getTitle());
holder.draweeView.setImageURI(arrayBean.get(position).getPicUrl());
       }

       @Override
       public int getItemCount() {
          int size= ((arrayBean == null) ? 0 : arrayBean.size());

           return size;
       }
   }


    class MyAsynTask extends AsyncTask<Object,String,String>{


        @Override
        protected String doInBackground(Object... params) {
            String html= (String) params[0];
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
                    publishProgress(strRead);
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
            new FileDao().write(tableName,mainActivity,newslistBeen);
            // daoUtils.insert("insert into "+tableName+ " values("+newslistBeen+")");
            Gson gson=new Gson();
            weixinjinxuan weixin=gson.fromJson(newslistBeen,weixinjinxuan.class);
                SharedPreferences.wirteSharedPreference(mainActivity,newslistBeen);
swipeRefreshLayout.setRefreshing(false);
            /*for (int i=0;i<weixin.getNewslist().size();i++){

                Toast.makeText(mainActivity,weixin.getNewslist().get(i).getTitle(),Toast.LENGTH_SHORT)
                        .show();

            }*/
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

        //arrayBean

    }


    //Toast.makeText(mainActivity,"read sp"+SharedPreferences.readSp(mainActivity),Toast.LENGTH_SHORT).show();
}

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


        }
    }
}

