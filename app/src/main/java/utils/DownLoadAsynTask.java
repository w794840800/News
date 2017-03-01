package utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2017/2/24.
 */

public class DownLoadAsynTask extends AsyncTask<Object,Void,Bitmap> {
    ImageView imageView;
    URL url=null;
    @Override
    protected Bitmap doInBackground(Object... params) {
        Bitmap bitmap=null;
        try {
             url=new URL((String) params[1]);
             imageView= (ImageView) params[0];
        HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
        httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setConnectTimeout(5000);
               httpURLConnection.connect();
            if (httpURLConnection.getResponseCode()==200){
                //BitmapFactory.decodeFile()

          bitmap=BitmapFactory.decodeStream(httpURLConnection.getInputStream());
           localCache.writeCache(bitmap, (String) params[1]);

            }


        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
       // super.onPostExecute(bitmap);
        imageView.setImageBitmap(bitmap);

    }
}
