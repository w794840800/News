package comn.example.administrator.news.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Administrator on 2017/3/27.
 */

public class ConnectionUtils {

public static boolean isNetWork(Context context){
    ConnectivityManager connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
    if (networkInfo!=null){

        return networkInfo.isAvailable();
    }
    return false;

}

}
