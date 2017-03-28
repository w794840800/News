package utils;

import android.content.ComponentName;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Administrator on 2017/3/23.
 */

public class isConnection {
    /**
     *     判断是否有网络
     * @param context
     * @return
     */
    public static boolean isNetWork(Context context){
        ConnectivityManager connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
if (networkInfo!=null){
    return networkInfo.isAvailable();
}
        return  false;
    }

    public static boolean isWifi(Context context){

        ConnectivityManager connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

             if (networkInfo!=null){
             return networkInfo.isAvailable();
             }
        return false;
        }



    }

