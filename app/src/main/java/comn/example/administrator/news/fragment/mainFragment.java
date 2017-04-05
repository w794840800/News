package comn.example.administrator.news.fragment;

import android.app.usage.NetworkStatsManager;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;

import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import comn.example.administrator.news.R;
import comn.example.administrator.news.adapter.ViewPagerAdapter;
import comn.example.administrator.news.db.DaoUtils;
import comn.example.administrator.news.db.dateBaseSQLite;
import comn.example.administrator.news.jean.weixinjinxuan;
import comn.example.administrator.news.mvp.presenter.UserPresent;
import comn.example.administrator.news.fragment.BasicFragment;
import comn.example.administrator.news.mvp.view.IUserView;
import comn.example.administrator.news.pages.BasicPages;

import comn.example.administrator.news.sp.SharedPreferences;
import comn.example.administrator.news.view.NoScrollViewPager;

/**
 * Created by Administrator on 2017/2/22.
 */

public class mainFragment extends BasicFragment implements IUserView {
    UserPresent userPresent;
    DaoUtils daoUtils;
    dateBaseSQLite dateBaseSQLite;
    ArrayList<BasicPages> basicPagesArrayList = new ArrayList<>();
    ArrayList<String> tabTitle = new ArrayList<>();
    TabLayout main_tabLayout;
    NoScrollViewPager main_viewPager;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    public View initView() {
        View view = LayoutInflater.from(mainActivity).inflate(R.layout.main_fragment, null);
        userPresent = new UserPresent(this);
        viewPagerAdapter = new ViewPagerAdapter(basicPagesArrayList, tabTitle);
        main_tabLayout = (TabLayout) view.findViewById(R.id.main_tabLayout);
        main_viewPager = (NoScrollViewPager) view.findViewById(R.id.main_viewPager);
        dateBaseSQLite = new dateBaseSQLite(getActivity(), null);
        daoUtils = new DaoUtils(getActivity(), dateBaseSQLite);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initDate();
    }

    @Override
    public void initDate() {
        userPresent.addTabItem();
    }

    //Iuser接口中方法 Present里更新
    @Override
    public void updateDate(int position) {

        basicPagesArrayList.get(position)
                .updateDate();
    }

    @Override
    public void addTabItem() {
        BasicPages weixin = new BasicPages(getActivity(), "https://api.tianapi.com/wxnew/?" +
                "key=4e3754ff60bd8b8f9ae918f5b8fd3797&num=10", "page1", daoUtils, "wxnew");
        basicPagesArrayList.add(weixin);
        BasicPages secondPages = new BasicPages(getActivity(), "https://api.tianapi.com/social/?key=4e3754ff60bd8b8f9ae918f5b8fd3797&num=10", "page2", daoUtils, "social");
        basicPagesArrayList.add(secondPages);
        //secondPages.updateDate();
        basicPagesArrayList.add(new BasicPages(getActivity(), "https://api.tianapi.com/guonei/" +
                "?key=4e3754ff60bd8b8f9ae918f5b8fd3797&num=10", "page3", daoUtils, "guonei"));
        basicPagesArrayList.add(new BasicPages(getActivity(), "https://api.tianapi.com/tiyu/" +
                "?key=4e3754ff60bd8b8f9ae918f5b8fd3797&num=10\t", "page4", daoUtils, "tiyu"));
        tabTitle.add("微信精选");
        tabTitle.add("社会新闻");
        tabTitle.add("国内新闻");
        tabTitle.add("体育新闻");
        main_tabLayout.setupWithViewPager(main_viewPager);
        main_viewPager.setAdapter(viewPagerAdapter);

    }


}
