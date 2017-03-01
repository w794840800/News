package comn.example.administrator.news.db;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.IntegerRes;
import android.widget.Toast;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by Administrator on 2017/2/26.
 */

public class dateBaseSQLite extends SQLiteOpenHelper {
String TABLE_NAME;
    Context context;
    public dateBaseSQLite(Context context,String tableName) {
        super(context, "dialy.db", null, 1);
        TABLE_NAME=tableName;
    this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       String sql1= "create table "
                     + "page1"
               +" (ctime varchar(100)  ,title,description" +
               ",picUrl,url)";
       db.execSQL(sql1);
        String sql2= "create table "
                + "page2"
                +" (ctime varchar(100) ,title,description" +
                ",picUrl,url)";
        db.execSQL(sql2);

        String sql3= "create table "
                + "page3"
                +" (ctime varchar(100) ,title,description" +
                ",picUrl,url)";
        db.execSQL(sql3);
        //Observable<String>.create(Observable<String>.just())
//Observable.create(new Observable.OnSubscribe<Object>() {
//Observable.create(new Observable




        String sql4= "create table "
                + "page4"
                +" (ctime varchar(100) ,title,description" +
                ",picUrl,url)";
        db.execSQL(sql4);

        Toast.makeText(context,"success",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
