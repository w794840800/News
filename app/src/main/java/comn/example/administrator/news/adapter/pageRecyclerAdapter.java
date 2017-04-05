package comn.example.administrator.news.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import comn.example.administrator.news.R;
import comn.example.administrator.news.jean.weixinjinxuan;
import comn.example.administrator.news.pages.BasicPages;
import comn.example.administrator.news.webViewActivity;

import static comn.example.administrator.news.R.id.indicator;

/**
 * Created by Administrator on 2017/4/5.
 */

public class pageRecyclerAdapter  extends RecyclerView.Adapter<pageRecyclerAdapter.MyViewHolder>{
ViewPager headViewPager;
    int currentIndex=0;
    LinearLayout indicator;
    Handler handler1 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.arg1 == -1) {
                headViewPager.setCurrentItem(0);
            } else {
                headViewPager.setCurrentItem(msg.arg1);
            }
        }
    };
    Context mainActivity;
    HeadViewPagerAdapter headViewPagerAdapter;
    Handler handler;
    private ArrayList<weixinjinxuan.NewslistBean> headList=new ArrayList<>();
    private ArrayList<weixinjinxuan.NewslistBean> arrayBean;
    private FrameLayout frameLayout;
    private ImageView imageView1,imageView2,imageView3;
    private ArrayList<ImageView> imageViews=new ArrayList<>();

    public ViewPager getHeadViewPager(){
      return headViewPager;

  }
    public void setArraylist(ArrayList<weixinjinxuan.NewslistBean> a) {
        arrayBean = a;
        headList.clear();
        for (int i = 0; i < 3; i++) {
            headList.add(arrayBean.get(i));

        }

        headViewPagerAdapter.setArrayList(headList);
        headViewPagerAdapter.notifyDataSetChanged();

    }

  public pageRecyclerAdapter(Context c,Handler handler1, final HeadViewPagerAdapter headViewPagerAdapter){
      this.headViewPagerAdapter=headViewPagerAdapter;
      mainActivity=c;
      handler=handler1;


  }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == 0) {
            frameLayout = (FrameLayout) View.inflate(mainActivity, R.layout.head_layout, null);
            indicator = (LinearLayout) frameLayout.findViewById(R.id.indicator);
            imageView1 = (ImageView) indicator.findViewById(R.id.image1);
            imageView2 = (ImageView) indicator.findViewById(R.id.image2);
            imageView3 = (ImageView) indicator.findViewById(R.id.image3);
            imageViews.add(imageView1);
            imageViews.add(imageView2);
            imageViews.add(imageView3);
            return new MyViewHolder(frameLayout, 0);
        } else {
            View view = View.inflate(mainActivity, R.layout.item_news_detail, null);
            return new MyViewHolder(view,1);
        }


    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        if (getItemViewType(position) == 0) {
           // myTimerTask = null;
            holder.viewPager_head.setAdapter(headViewPagerAdapter);
            holder.viewPager_head.setCurrentItem(1);
            holder.viewPager_head.setCurrentItem(0);
            holder.viewPager_head.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    currentIndex = position;

                    for (int i = 0; i < imageViews.size(); i++) {
                        imageViews.get(i).setBackgroundResource(R.drawable.indicator_unselected);
                    }
                    imageViews.get(position).setBackgroundResource(R.drawable.indicator_selected);

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }

        if (getItemViewType(position) == 1) {
            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //    Intent intent=new Intent(mainActivity,webView)
                    webViewActivity.newIntent(mainActivity,
                            arrayBean.get(position).getUrl());

                    // showShare();

                }
            });
            if (arrayBean.size() == position) {
            } else {
                holder.tv_news_detail_author_name.setText(arrayBean.get(position).getDescription());
                holder.textView.setText(arrayBean.get(position).getTitle());
                holder.tv_news_detail_date.setText(arrayBean.get(position).getCtime());
                Glide.with(mainActivity)
                        .load(arrayBean.get(position).getPicUrl())
                        .thumbnail(0.1f)
                        .crossFade()
                        .placeholder(R.drawable.more)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)

                        //毛玻璃效果
                        //  .bitmapTransform(new BlurTransformation(mainActivity))

                        .into(holder.imageView);
            }
        }

    }

    @Override
    public int getItemViewType(int position) {
          if (position==0){
              return 0;
          }
          else {
              return 1;

          }

    }

    @Override
    public int getItemCount() {
        int size = ((arrayBean == null) ? 0 : arrayBean.size());

        return size + 1;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ViewPager viewPager_head;
        TextView textView, tv_news_detail_author_name, tv_news_detail_date;
        ImageView imageView;

        public MyViewHolder(View itemView,int typeView) {
        super(itemView);

            if (typeView == 0) {
                viewPager_head = (ViewPager)
                        itemView.findViewById(R.id.viewPager_header);
                headViewPager = viewPager_head;
                headViewPager.setCurrentItem(0);
                headViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        currentIndex=position;
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
               // myTimerTask = null;
                //myTimerTask = new BasicPages.MyTimerTask(currentIndex, "name1");
        new Timer().schedule(new TimerTask() {
                 @Override
                 public void run() {
                     Message message = Message.obtain();
                     if (currentIndex == 2) {
                         message.arg1 = -1;

                     } else {

                         message.arg1 = currentIndex + 1;

                     }
                     handler1.sendMessage(message);
                 }
                 }, 5000, 5000);
        }
            else {
                textView = (TextView) itemView.findViewById(R.id.tv_news_detail_title);
                tv_news_detail_author_name = (TextView) itemView.
                        findViewById(R.id.tv_news_detail_author_name);
                imageView = (ImageView) itemView.findViewById(R.id.draweeView);
                tv_news_detail_date = (TextView) itemView.findViewById(R.id.tv_news_detail_date);

            }
    }
}
}
