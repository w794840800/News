package comn.example.administrator.news.sp;

import android.app.Activity;

import android.content.Context;

/**
 * Created by Administrator on 2017/2/27.
 */

public class SharedPreferences {
    public SharedPreferences( ){

    }
  public static void wirteSharedPreference(Activity context,String content){
  android.content.SharedPreferences sharedPreferences= context.
          getSharedPreferences("jsonDate",Context.MODE_PRIVATE);
      android.content.SharedPreferences.Editor editor=sharedPreferences.
              edit();
      editor.putString("json",content);
      editor.commit();
  }
   public static String readSp(Context context){
       android.content.SharedPreferences sharedPreferences
               =context.getSharedPreferences("jsonDate",Context.MODE_PRIVATE);
            String json=sharedPreferences.getString("json","");

       return json;
   }

}
