package utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.EventListener;

/**
 * Created by Administrator on 2017/2/24.
 */

public class localCache {
    public static final String file = Environment
            .getExternalStorageDirectory().getAbsolutePath() + "firstCache";

    public static void writeCache(Bitmap bitmap,String url) {
        File cachefile = new File(file);
        if(!cachefile
                .exists()){
            cachefile.mkdir();
        }
        try {
            String name=MD5Encoder.encode(url);
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,new FileOutputStream(name));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Bitmap readCache(String url) throws Exception {
        File file1=new File(file,MD5Encoder.encode(url));
        if (file1.exists()){
            Bitmap bitmap= BitmapFactory.decodeStream(new FileInputStream(file1));
        return bitmap;
        }
        return null;
    }
}
