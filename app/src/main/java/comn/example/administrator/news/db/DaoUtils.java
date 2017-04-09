package comn.example.administrator.news.db;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import comn.example.administrator.news.jean.weixinjinxuan;

/**
 * Created by Administrator on 2017/2/26.
 */

public class DaoUtils{
   public static DaoUtils daoUtils;
    SQLiteDatabase datebase;
    Activity mActivity;
    dateBaseSQLite dateBaseSQLite1;
    public DaoUtils(Activity activity,dateBaseSQLite dateBaseSQLite){
            mActivity = activity;
            this.dateBaseSQLite1 = dateBaseSQLite;
            datebase = this.dateBaseSQLite1.getReadableDatabase();

    }
    public void insert(String sql,String[]a){
      datebase.execSQL(sql,a);
       //datebase.rawQuery()

    }
    public Cursor read(String sql){
        Cursor cursor=datebase.rawQuery(sql,null);
return cursor;
    }
    public ArrayList getDateFromCursor(Cursor cursor){
ArrayList<weixinjinxuan.NewslistBean>arrayBean=new ArrayList<>();
        if (cursor!=null) {
            while (cursor.moveToNext()) {
                weixinjinxuan.NewslistBean bean = new weixinjinxuan.NewslistBean();
                bean.setCtime(cursor.getString(0));
                bean.setDescription(cursor.getString(2));
                bean.setTitle(cursor.getString(1));
                bean.setPicUrl(cursor.getString(3));
                bean.setUrl(cursor.getString(4));
                // Toast.makeText(activity, ""+cursor.getString(5), Toast.LENGTH_SHORT).show();
                arrayBean.add(bean);


            }}
        return arrayBean;

    }


    public boolean queryTime(String time,String tableName){
        boolean isQuery=false;
        Cursor cursor=datebase.rawQuery("select * from "+tableName+" where title=?",
                 new String[]{time});
        if (cursor.getCount()!=0){
            isQuery=true;
        }
        cursor.close();
        return isQuery;
    }




}
