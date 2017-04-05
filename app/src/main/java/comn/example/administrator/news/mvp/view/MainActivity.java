package comn.example.administrator.news.mvp.view;

import android.app.Fragment;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.util.DiffUtil;
import android.view.View;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

import comn.example.administrator.news.BasicActivity;
import comn.example.administrator.news.R;
import comn.example.administrator.news.fragment.BasicFragment;
import comn.example.administrator.news.fragment.mainFragment;
import comn.example.administrator.news.fragment.qiubaiFragment;
import comn.example.administrator.news.jean.qiushibaike;

public class MainActivity extends BasicActivity {
RadioGroup radioGroup;
    boolean news_stated=false;
    RadioButton rb_news,rb_qiubai;
Drawable news_white,news_red;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        news_white=getResources().getDrawable(R.drawable.ic_action_news_white);
 news_red=getResources().getDrawable(R.drawable.ic_action_news_red);
        //DiffUtil.DiffResult diffResult=DiffUtil.calculateDiff(new MyDifficult())
   radioGroup= (RadioGroup) findViewById(R.id.radioGroup);
        rb_news= (RadioButton) findViewById(R.id.rb_news);

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
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
               // RadioButton radioButton= (RadioButton) findViewById(group.getCheckedRadioButtonId());
              switch (checkedId){
/*    case R.id*/

                  case R.id.rb_qiubai:
                   //   rb_news.setChecked(!rb_news.isChecked());
                     rb_news.setChecked(false);
                      rb_qiubai.setChecked(true);
                     // rb_news.setChecked(false);
                      qiubaiFragment qiubaiFragment=new qiubaiFragment();
                      getFragmentManager().beginTransaction().
                              replace(R.id.main_fragment,qiubaiFragment)
                              .addToBackStack(null)
                              .commit();
                    //  Toast.makeText(getApplicationContext(),"qiubai",Toast.LENGTH_SHORT).show();
                      break;
                  case R.id.rb_news:
                     /* if (!rb_news.isChecked()) {
//Toast.makeText(getApplicationContext(),""+rb_news.isChecked(),Toast.LENGTH_SHORT).show();
                          rb_news.setCompoundDrawablesWithIntrinsicBounds(null,news_red,null,null);
             rb_news.setChecked(true);
                   // news_stated=true;
                      }
                      else {
                         // Toast.makeText(getApplicationContext(),""+rb_news.isChecked(),Toast.LENGTH_SHORT).show();

                          rb_news.setCompoundDrawablesWithIntrinsicBounds(null,news_white,null,null);
                          rb_news.setChecked(false);
                      }*/


                      mainFragment mainFragment=new mainFragment();
                      getFragmentManager().beginTransaction().
                              replace(R.id.main_fragment,mainFragment)
                             // .addToBackStack(null)
                              .commit();
                      break;

              }

            }
        });

    }
//加载主fragment
    @Override
    protected Fragment createFragment() {

        BasicFragment fragment=new mainFragment();
        return fragment;
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
