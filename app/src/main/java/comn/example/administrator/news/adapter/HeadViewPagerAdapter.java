package comn.example.administrator.news.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import comn.example.administrator.news.R;
import comn.example.administrator.news.jean.weixinjinxuan;
import comn.example.administrator.news.webViewActivity;

/**
 * Created by Administrator on 2017/3/30.
 */

public class HeadViewPagerAdapter extends PagerAdapter {

    int[] img= new int[]{R.drawable.splash_horse_newyear,R.drawable.more,R.drawable.moren};
    Context context;
    ArrayList<weixinjinxuan.NewslistBean>headList;
    public HeadViewPagerAdapter(Context context1, ArrayList<weixinjinxuan.NewslistBean>headList1){
        context=context1;
        headList=headList1;
    }

    public void setArrayList(ArrayList<weixinjinxuan.NewslistBean>arrayList1){
    headList=arrayList1;
       // Toast.makeText(context,"headList"+headList.size(),Toast.LENGTH_SHORT).show();
    }
    @Override
    public int getCount() {
        return headList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        //return super.instantiateItem(container, position);

        ImageView imageView= (ImageView) View.inflate(context,R.layout.viewpager_item,null);

        //imageView.setImageResource(img[position]);
        Glide.with(context)
                .load(headList.get(position).getPicUrl())
                .error(R.drawable.more)
        .into(imageView)
        ;


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webViewActivity.newIntent(context,
                        headList.get(position).getUrl());
            }
        });
       // ViewGroup parent= (ViewGroup) imageView.getParent();

        //  parent.removeAllViews();


        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);

        container.removeView((View) object);
    }
}
