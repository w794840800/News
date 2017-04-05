package comn.example.administrator.news.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import comn.example.administrator.news.pages.BasicPages;

/**
 * Created by Administrator on 2017/3/19.
 */

public class ViewPagerAdapter extends PagerAdapter {
    ArrayList<BasicPages>basicPagesArrayList=new ArrayList<>();
    ArrayList<String>tabTitle=new ArrayList<>();
    public ViewPagerAdapter(ArrayList<BasicPages>basicPagesArray, ArrayList<String>tabTitle){
        basicPagesArrayList=basicPagesArray;
        this.tabTitle=tabTitle;
    }

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
        ViewGroup viewGroup= (ViewGroup) recyclerView.getParent();
       if (viewGroup!=null){
           viewGroup.removeAllViews();
       }
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
