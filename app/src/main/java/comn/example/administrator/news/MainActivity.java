package comn.example.administrator.news;

import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import comn.example.administrator.news.fragment.BasicFragment;
import comn.example.administrator.news.fragment.mainFragment;

public class MainActivity extends BasicActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

    }

    @Override
    protected Fragment createFragment() {
        BasicFragment fragment=new mainFragment();
        //fragment.initDate();
        return fragment;
    }
}
