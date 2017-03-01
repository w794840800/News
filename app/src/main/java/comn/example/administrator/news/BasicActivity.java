package comn.example.administrator.news;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public abstract class BasicActivity extends AppCompatActivity {
//TabLayout main_tabLayout;
//    RecyclerView main_recycler;
  //ViewPager main_viewPager;
    //weixinFragment weixinFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
initView();
        //getP


    }

    private void initView() {
        //getFilesDir();
        //getCacheDir();
//weixinFragment=new weixinFragment();
        android.app.FragmentManager fragmentManager=getFragmentManager();
        Fragment basicFragment =fragmentManager.findFragmentById(R.id.main_fragment);
        //FragmentTransaction translation= getFragmentManager().beginTransaction();
       if (basicFragment==null){
           basicFragment=createFragment();


       }
        fragmentManager.beginTransaction().add(R.id.main_fragment,basicFragment)
                .commit();


    //main_tabLayout= (TabLayout) findViewById(R.id.main_tabLayout);
    //main_recycler= (RecyclerView) findViewById(R.id.main_recylcerView);


    }

    protected abstract Fragment createFragment();

   /// public abstract BasicFragment initFragment() ;


}
