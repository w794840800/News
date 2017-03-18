package comn.example.administrator.news.fragment;

import android.content.Context;
import android.os.Bundle;

import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import comn.example.administrator.news.R;
import comn.example.administrator.news.db.DaoUtils;
import comn.example.administrator.news.db.dateBaseSQLite;
import comn.example.administrator.news.mvp.presenter.UserPresent;
import comn.example.administrator.news.fragment.BasicFragment;
import comn.example.administrator.news.mvp.view.IUserView;
import comn.example.administrator.news.pages.BasicPages;
import comn.example.administrator.news.sp.SharedPreferences;

/**
 * Created by Administrator on 2017/2/22.
 */

public class mainFragment extends BasicFragment implements IUserView {
  UserPresent userPresent;
    DaoUtils daoUtils;
    dateBaseSQLite dateBaseSQLite;
   ArrayList<BasicPages>basicPagesArrayList=new ArrayList<>();
    ArrayList<String>tabTitle=new ArrayList<>();
    TabLayout main_tabLayout;
    ViewPager main_viewPager;
    @Override
    public View initView() {
        View view= LayoutInflater.from(mainActivity).inflate(R.layout.main_fragment,null);
   userPresent=new UserPresent(this);
       main_tabLayout= (TabLayout) view.findViewById(R.id.main_tabLayout);
            main_viewPager= (ViewPager) view.findViewById(R.id.main_viewPager);

        dateBaseSQLite=new dateBaseSQLite(getActivity(),null);
        daoUtils=new DaoUtils(getActivity(),dateBaseSQLite);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initDate();
    }

    @Override
    public void initDate() {
addTabDate();


main_viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){

    @Override
    public void onPageSelected(int position) {
        //super.onPageSelected(position);
  userPresent.updateDate(position);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        super.onPageScrolled(position, positionOffset, positionOffsetPixels);

     if (position==0){
         userPresent.updateDate(0);

     }
    }
});
        /*main_viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
               userPresent.updateDate(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
*/
    }


//Iuser接口中方法 Present里更新
    @Override
    public void updateDate(int position) {

        basicPagesArrayList.get(position)
                .updateDate();

    }



    class MyViewPager extends PagerAdapter{


        @Override
        public int getCount() {
            return basicPagesArrayList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BasicPages basicPages=basicPagesArrayList.get(position);
            View recyclerView=  basicPages.initView();
            container.addView(recyclerView);
            return recyclerView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
              container.removeView((View) object);

        }

        @Override
        public CharSequence getPageTitle(int position) {
int i=0;
            return tabTitle.get(position);

        }
    }
public void addTabDate(){

    BasicPages weixin=new BasicPages(getActivity(),"https://api.tianapi.com/wxnew/?" +

            "key=4e3754ff60bd8b8f9ae918f5b8fd3797&num=10","page1",daoUtils,"wxnew");

    basicPagesArrayList.add(weixin);
    //weixin.updateDate();

    BasicPages secondPages=new BasicPages(getActivity(),"https://api.tianapi.com/social/?key=4e3754ff60bd8b8f9ae918f5b8fd3797&num=10","page2",daoUtils,"social");

    basicPagesArrayList.add(secondPages);
    //secondPages.updateDate();
    basicPagesArrayList.add(new BasicPages(getActivity(),"https://api.tianapi.com/guonei/" +
            "?key=4e3754ff60bd8b8f9ae918f5b8fd3797&num=10","page3",daoUtils,"guonei"));
    basicPagesArrayList.add(new BasicPages(getActivity(),"https://api.tianapi.com/tiyu/" +
            "?key=4e3754ff60bd8b8f9ae918f5b8fd3797&num=10\t","page4",daoUtils,"tiyu"));
    tabTitle.add("微信精选");
    tabTitle.add("社会新闻");
    tabTitle.add("娱乐新闻");
    tabTitle.add("军事新闻");
    main_tabLayout.setupWithViewPager(main_viewPager);
    main_viewPager.setAdapter(new MyViewPager());
    weixin.updateDate();
  // SharedPreferences sharedPreferences= (SharedPreferences) mainActivity.getSharedPreferences("first_enter", Context.MODE_PRIVATE);


}

}
