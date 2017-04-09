package comn.example.administrator.news.mvp.view;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.drawable.Drawable;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

import comn.example.administrator.news.R;
import comn.example.administrator.news.fragment.mainFragment;
import comn.example.administrator.news.fragment.mvp.view.ZhihuhuFragment;
import comn.example.administrator.news.fragment.mvp.view.zhihuFragment;
import comn.example.administrator.news.fragment.qiubaiFragment;

public class MainActivity extends AppCompatActivity {
RadioGroup radioGroup;
    //FragmentTransaction fragmentTransaction;
    ArrayList<Fragment>fragmentArrayList=new ArrayList<>();
    boolean news_stated=false;
    RadioButton rb_news,rb_qiubai,rb_zhihu;
Drawable news_white,news_red;
    Fragment mainFragment,qiubaiFragment,zhihuFragment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
//fragmentTransaction=getFragmentManager().beginTransaction();
        setContentView(R.layout.activity_main);
        news_white=getResources().getDrawable(R.drawable.ic_action_news_white);
 news_red=getResources().getDrawable(R.drawable.ic_action_news_red);
        //DiffUtil.DiffResult diffResult=DiffUtil.calculateDiff(new MyDifficult())
   radioGroup= (RadioGroup) findViewById(R.id.radioGroup);
        rb_news= (RadioButton) findViewById(R.id.rb_news);
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
                     qiubaiFragment=new qiubaiFragment();
                 fragmentTransaction.add(R.id.main_fragment,qiubaiFragment)
                        // .addToBackStack(null)
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
                                 // .addToBackStack(null)
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
                           //.addToBackStack(null)
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
}
