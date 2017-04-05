package comn.example.administrator.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import comn.example.administrator.news.view.PhotoViewPager;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by Administrator on 2017/3/28.
 */

public class OpenImageActivity extends AppCompatActivity{
   PhotoViewPager imageView;
//MyPhotoViewPagerAdapter myPhotoViewPagerAdapter;
    ViewPager viewPager;
    ArrayList<String>img_list=new ArrayList<>();
    ArrayList<Integer>arrayList=new ArrayList<>();

    PhotoViewAttacher photoViewAttacher;
String url;
    int current=0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
  setContentView(R.layout.webpic);
       // getParent().get
        arrayList.add(R.drawable.big);
        arrayList.add(R.drawable.more);
        arrayList.add(R.drawable.moren);
        arrayList.add(R.drawable.splash_horse_newyear);
        img_list=getIntent().getStringArrayListExtra("imags");
       url=getIntent().getStringExtra("img");
        for (int i=0;i<img_list.size();i++){
            if (url.equals(img_list.get(i))){
                current=i;

            }

        }

        // img_list.remove(0);;
         for (int i=0;i<img_list.size();i++){
             Log.d("url1",img_list.get(i));
//小猪佩奇
         }
//myPhotoViewPagerAdapter=new MyPhotoViewPagerAdapter();
 //   imageView= (PhotoView) findViewById(R.id.image_review);
      //  photoViewAttacher=new PhotoViewAttacher(imageView);
   //     Glide.with(getApplicationContext()).load(getIntent().getStringExtra("img"))
     //           .into(imageView);
viewPager= (PhotoViewPager) findViewById(R.id.image_viewPager);
    viewPager.setAdapter(new MyPhotoViewPagerAdapter());

viewPager.setCurrentItem(current);

//viewPager.setCurrentItem(getIntent());
    }

    class MyPhotoViewPagerAdapter extends PagerAdapter{


        @Override
        public int getCount() {
        // Toast.makeText(getApplicationContext(),"test"+img_list.size(),Toast.LENGTH_SHORT).show();

            return img_list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view=View.inflate(getApplicationContext(),R.layout.photoview,null);
            PhotoView photoView= (PhotoView) view.findViewById(R.id.photoview);
            PhotoViewAttacher photoViewAttacher=new PhotoViewAttacher(photoView);

            Glide.with(getApplicationContext()).load(img_list.get(position))
                    .error(R.drawable.big)
                    .thumbnail(0.1f)
                    .into(photoView);
            ((ViewPager)container).addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            ((ViewPager)container).removeView((View) object);
        }
    }

    @Override
    protected void onDestroy() {
        Log.d("onDestory    ","onDestroy       ");
        super.onDestroy();

    }

    @Override
    public void onBackPressed() {
       // finish();
        super.onBackPressed();
    }
}
