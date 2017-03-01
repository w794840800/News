package comn.example.administrator.news.db;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Administrator on 2017/2/26.
 */

public class DaoUtils {
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
