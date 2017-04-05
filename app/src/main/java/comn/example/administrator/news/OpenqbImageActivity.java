package comn.example.administrator.news;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.DiffUtil;
import android.util.Log;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by Administrator on 2017/3/28.
 */

public class OpenqbImageActivity extends AppCompatActivity {
   PhotoView imageView;
    PhotoViewAttacher photoViewAttacher;
String url;
   // int current=0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
  setContentView(R.layout.open_qbimage);
       // getParent().get
//
// DiffUtil.DiffResult diffResult=DiffUtil.calculateDiff()
     //  url=getIntent().getStringExtra("img");


        // img_list.remove(0);;

//myPhotoViewPagerAdapter=new MyPhotoViewPagerAdapter();
   imageView= (PhotoView) findViewById(R.id.image_photoView);
        photoViewAttacher=new PhotoViewAttacher(imageView);
      //  Toast.makeText(getApplicationContext(),""+getIntent().getStringExtra("img"),Toast.LENGTH_SHORT).show();
   Glide.with(getApplicationContext()).load(getIntent().getStringExtra("img"))
               .into(imageView);


//viewPager.setCurrentItem(getIntent());
    }

public static void newIntent(Context context,String url){
    Intent intent=new Intent(context,OpenqbImageActivity.class);
    intent.putExtra("img",url);
    context.startActivity(intent);
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
