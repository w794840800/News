package comn.example.administrator.news.fragment;

import android.os.Bundle;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import comn.example.administrator.news.R;
import comn.example.administrator.news.db.DaoUtils;
import comn.example.administrator.news.db.dateBaseSQLite;
import comn.example.administrator.news.pages.BasicPages;

/**
 * Created by Administrator on 2017/2/22.
 */

public class mainFragment extends BasicFragment {
  DaoUtils daoUtils;
    dateBaseSQLite dateBaseSQLite;
   ArrayList<BasicPages>basicPagesArrayList=new ArrayList<>();
    ArrayList<String>tabTitle=new ArrayList<>();
    TabLayout main_tabLayout;
    ViewPager main_viewPager;
    @Override
    public View initView() {
        View view= LayoutInflater.from(mainActivity).inflate(R.layout.main_fragment,null);

       main_tabLayout= (TabLayout) view.findViewById(R.id.main_tabLayout);
            main_viewPager= (ViewPager) view.findViewById(R.id.main_viewPager);
       // main_viewPager.setOffscreenPageLimit();
      //initDate();
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
        //super.initDate();
         /*main_tabLayout.addTab(main_tabLayout.newTab().setText("微信精选"));
        main_tabLayout.addTab(main_tabLayout.newTab().setText("社会新闻"));
        main_tabLayout.addTab(main_tabLayout.newTab().setText("娱乐新闻"));
        main_tabLayout.addTab(main_tabLayout.newTab().setText("军事新闻"));*/
       // dateBaseSQLite dateBaseSQLite=new dateBaseSQLite(mainActivity, null)  ;
       // dateBaseSQLite.getWritableDatabase();
        BasicPages weixin=new BasicPages(getActivity(),"https://api.tianapi.com/wxnew/?" +

               "key=4e3754ff60bd8b8f9ae918f5b8fd3797&num=10","page1",daoUtils);

        basicPagesArrayList.add(weixin);
        weixin.updateDate();
        /*  BasicPages weixin=new BasicPages(getActivity(),"https://api.tianapi.com/social/?key=4e3754ff60bd8b8f9ae918f5b8fd3797&num=10","page1",daoUtils);
              basicPagesArrayList.add(weixin);*/
        basicPagesArrayList.add(new BasicPages(getActivity(),"https://api.tianapi.com/social/?key=4e3754ff60bd8b8f9ae918f5b8fd3797&num=10","page2",daoUtils));

        basicPagesArrayList.add(new BasicPages(getActivity(),"https://api.tianapi.com/guonei/" +
                "?key=4e3754ff60bd8b8f9ae918f5b8fd3797&num=10","page3",daoUtils));
        basicPagesArrayList.add(new BasicPages(getActivity(),"https://api.tianapi.com/tiyu/" +
                "?key=4e3754ff60bd8b8f9ae918f5b8fd3797&num=10\t","page4",daoUtils));
        tabTitle.add("微信精选");
        tabTitle.add("社会新闻");
        tabTitle.add("娱乐新闻");
        tabTitle.add("军事新闻");
             main_tabLayout.setupWithViewPager(main_viewPager);
        main_viewPager.setAdapter(new MyViewPager());
       weixin.updateDate();

        main_viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                basicPagesArrayList.get(position)
                        .updateDate();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

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

            return tabTitle.get(position);

        }
    }


}
