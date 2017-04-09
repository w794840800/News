package comn.example.administrator.news.fragment.mvp.model;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import comn.example.administrator.news.db.DaoUtils;
import comn.example.administrator.news.jean.zhihu;
import comn.example.administrator.news.net.RetrofitManager;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/4/6.
 */

public class Imodel {
    Context context;
    public Observable<zhihu> getJson(Context context1,String id){
        context=context1;
       return RetrofitManager.getRetrofitManager(context,"http://news-at.zhihu.com/api/4/news/before/").getInfoService()
                .getZhihuJson(id);
    }
    public ArrayList<zhihu.StoriesBean> saveJson(Observable<zhihu>s, final DaoUtils daoUtils){
final ArrayList<zhihu.StoriesBean>list=new ArrayList<>();
        s.subscribeOn(Schedulers.io())
 .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<zhihu>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(context,"zhiFinisg()",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(zhihu zhihu) {
                        for (int i = 0; i <zhihu.getStories().size();i++) {
                            list.add(zhihu.getStories().get(i));


                        }

                        saveDB(list,daoUtils);
                    }
                });

return list;
    }
    public ArrayList<zhihu.StoriesBean>getDateFromDB(DaoUtils utils){
        zhihu.StoriesBean bean;
        ArrayList<String>images;
        ArrayList<zhihu.StoriesBean>list=new ArrayList<>();
        Cursor cursor=utils.read("select * from zhihu");
        //Log.d("cursor size",cursor.getCount()+"");
        //cursor.moveToFirst();
   while (cursor.moveToNext()){

       bean=new zhihu.StoriesBean();
       String id=cursor.getString(cursor.getColumnIndex("id"));
          String title=cursor.getString(cursor.getColumnIndex("title"));
            images=new ArrayList<>();
       images.add(cursor.getString(cursor.getColumnIndex("images")));
       bean.setId(Integer.parseInt(id));
       bean.setImages(images);
       bean.setTitle(title);
       list.add(bean);

   }
 cursor.close();
  return   list;
}
    public void saveDB(ArrayList<zhihu.StoriesBean> list, DaoUtils daoUtils) {
        Log.d("zhihuSize",""+list.size());
        for (int i=0;i<list.size();i++){
            String images=list.get(i).getImages().get(0).toString();
            String title=list.get(i).getTitle().toString();
            String id= String.valueOf(list.get(i).getId());
            Log.d("zhihuSize",""+list.get(i).getId());
            Log.d("zhihuSize",""+list.get(i).getImages().get(0).toString());
            Log.d("zhihuSize",""+list.get(i).getTitle().toString());
          if (!daoUtils.queryTime(title,"zhihu")){
         daoUtils.insert("insert into zhihu values(?,?,?)",
                 new String[]{images,title,id});
    }
        }
        Log.d("db","success");
    }


}
