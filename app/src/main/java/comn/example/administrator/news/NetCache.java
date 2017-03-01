package comn.example.administrator.news;

import android.widget.ImageView;

import utils.DownLoadAsynTask;

/**
 * Created by Administrator on 2017/2/24.
 */

public class NetCache {
public static void disPlayImageView(ImageView imageView,String url){
    new DownLoadAsynTask().execute(imageView,url);

}




}
