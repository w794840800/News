package comn.example.administrator.news.file;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by Administrator on 2017/2/26.
 */

public class FileDao {
    Context activity;
    String fileName;
    StringBuffer stringBuffer;
    public String read(Context context,String name){
        activity=context;
        fileName=name;
        BufferedReader bufferedReader;
        try {
            FileInputStream fileInputStream=
                    activity.openFileInput(name);
            bufferedReader=new BufferedReader(new InputStreamReader(fileInputStream));
            String line;
            stringBuffer=new StringBuffer();
            while ((line=bufferedReader.readLine())!=null){
                stringBuffer.append(line);
                stringBuffer.append("\n");

            }



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

return stringBuffer.toString();
    }

public void write(String name,Context c,String content){
    BufferedWriter writer=null;

    try {
        if (content!=null){



        FileOutputStream fileOut=c.openFileOutput(name+".txt",Context.MODE_PRIVATE);
       //if (fileOut)
       // writer=new BufferedWriter(new OutputStreamWriter(fileOut));
        fileOut.write(content.getBytes());
        //writer.write(String.valueOf(content.getBytes()));
        }
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }

}
}
