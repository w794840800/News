package comn.example.administrator.news.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import comn.example.administrator.news.db.dateBaseSQLite;

/**
 * Created by Administrator on 2017/4/11.
 */

public class MyProvider extends ContentProvider {
    dateBaseSQLite dateBaseSQLite;
    SQLiteDatabase sqLiteDatabase;
    String authority="com.example.news";
    Uri uri=Uri.parse("content://com.example.news");
    UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    {
        uriMatcher.addURI(authority,"haha",0);
     //   uriMatcher.addURI(authority,"zhihuhu",1);
      //  uriMatcher.addURI(authority,"zhihuhu",2);
      //  uriMatcher.addURI(authority,"zhihuhu",3);

    }


    @Override
    public boolean onCreate() {
        dateBaseSQLite=new dateBaseSQLite(getContext(),null);
      sqLiteDatabase=dateBaseSQLite.getWritableDatabase();
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
switch (uriMatcher.match(uri)) {
    case 0:
    Cursor cursor1 = sqLiteDatabase.
            query("zhihuhu", projection, selection, selectionArgs, null, null, sortOrder);
cursor1.setNotificationUri(getContext().getContentResolver(),uri);
    return cursor1;
}
return null;


    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {

return null;
       // return "vnd.android.cursor.dir/vnd.com.example.news.zhihuhu";
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

switch (uriMatcher.match(uri)){
    case 0:

long id=sqLiteDatabase.insert("zhihuhu",null,values);
getContext().getContentResolver().notifyChange(uri,null);
        return ContentUris.withAppendedId(uri,id);
}
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {

     switch (uriMatcher.match(uri)){
         case 2:
       int id =sqLiteDatabase.delete("zhihuhu",selection,selectionArgs);
return id;
     }
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
     switch (uriMatcher.match(uri)){

         case 3:
             int id=sqLiteDatabase.update("zhihuhu",values,selection,selectionArgs);

             return id;

     }

        return 0;
    }
}
