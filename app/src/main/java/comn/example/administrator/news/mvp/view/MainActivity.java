package comn.example.administrator.news.mvp.view;

import android.app.Fragment;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.Window;

import comn.example.administrator.news.BasicActivity;
import comn.example.administrator.news.R;
import comn.example.administrator.news.fragment.BasicFragment;
import comn.example.administrator.news.fragment.mainFragment;

public class MainActivity extends BasicActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

    }
//加载主fragment
    @Override
    protected Fragment createFragment() {

        BasicFragment fragment=new mainFragment();
        return fragment;
    }
}
