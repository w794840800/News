package comn.example.administrator.news.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import comn.example.administrator.news.R;
import comn.example.administrator.news.fragment.mvp.constract.zhihuConstract;
import comn.example.administrator.news.jean.zhihu;
import comn.example.administrator.news.webViewActivity;

/**
 * Created by Administrator on 2017/4/6.
 */


public class zhihuRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final int TYPE_FOOTER =0 ;
    private static final int TYPE_LIST = 1;
    ArrayList<zhihu.StoriesBean>storiesBeanArrayList;
   Context context;
    public zhihuRecyclerAdapter(Context c,ArrayList<zhihu.StoriesBean>arrayList){
        storiesBeanArrayList=arrayList;
        context=c;
  }

  public void setArrayList(ArrayList<zhihu.StoriesBean>storiesBeanArrayLis){
      storiesBeanArrayList=storiesBeanArrayLis;

  }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType==TYPE_LIST){
            View view=LayoutInflater.from(context).inflate(R.layout.item_news_detail,parent,false);
            return new zhihuViewHolder(view);
        }
        View view=LayoutInflater.from(context).inflate(R.layout.list_footer,parent,false);
        return new FooterViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        if (storiesBeanArrayList.size()==position){
            return TYPE_FOOTER;
        }
        return TYPE_LIST;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
       // Toast.makeText(context,"instance",Toast.LENGTH_SHORT).show();

        if (holder instanceof zhihuViewHolder){
        ((zhihuViewHolder)holder).textView.setText(storiesBeanArrayList.get(position).getTitle());
        Glide.with(context).load(storiesBeanArrayList.get(position).getImages().get(0))
        .into(  ((zhihuViewHolder)holder).imageView);
      ((zhihuViewHolder)holder).textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(context,"webView",Toast.LENGTH_SHORT).show();
                webViewActivity.newIntent(context,
                        "http://daily.zhihu.com/story/"+storiesBeanArrayList.get(position).getId());
            }
        });}
  }


    @Override
    public int getItemCount() {
        return storiesBeanArrayList.size()+1;
    }

     class zhihuViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;

    public zhihuViewHolder(View itemView) {
        super(itemView);
      textView= (TextView) itemView.findViewById(R.id.tv_news_detail_title);
      imageView= (ImageView) itemView.findViewById(R.id.draweeView);
    }
}
class FooterViewHolder extends RecyclerView.ViewHolder{
    public FooterViewHolder(View itemView) {
        super(itemView);
    }
}

}
