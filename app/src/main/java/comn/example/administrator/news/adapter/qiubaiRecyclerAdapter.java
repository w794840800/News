package comn.example.administrator.news.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import comn.example.administrator.news.OpenqbImageActivity;
import comn.example.administrator.news.R;
import comn.example.administrator.news.jean.qiushibaike;
import comn.example.administrator.news.webViewActivity;

/**
 * Created by Administrator on 2017/3/31.
 */

public class qiubaiRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_LOADMORE = 0;
    private static final int TYPE_NORMAL = 1;
    Context context;
    ArrayList<qiushibaike> qiushibaikeArrayList;
public qiubaiRecyclerAdapter(Context context,ArrayList<qiushibaike> qiushibaikeArrayList){
this.context=context;
    this.qiushibaikeArrayList=qiushibaikeArrayList;


}

public void setArrayList(ArrayList<qiushibaike>ArrayList){
    qiushibaikeArrayList=ArrayList;
//    notifyDataSetChanged();
}
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       if (viewType==TYPE_NORMAL) {
           View view = LayoutInflater.from(context).inflate(R.layout.item_news_qb, parent, false);

           return new MyViewHolder(view);
       }
       else {
           View v=LayoutInflater.from(context).inflate(R.layout.list_footer,parent,false);
           return new MyViewHolder.LoadMoreHolder(v);
       }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
     if (holder instanceof MyViewHolder){
         ((MyViewHolder) holder).qb_text.setText(qiushibaikeArrayList.get(position).getTitle());
        Glide.with(context).load(qiushibaikeArrayList.get(position).getPicurl())
                .into(((MyViewHolder) holder).qb_img);
         ((MyViewHolder) holder).qb_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenqbImageActivity.newIntent(context,qiushibaikeArrayList.get(position).getPicurl());

            }
        });

         ((MyViewHolder) holder).qb_text.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               //Toast.makeText(context,""+qiushibaikeArrayList.get(position).getUrl(),Toast.LENGTH_SHORT).show();
               webViewActivity.newIntent(context,qiushibaikeArrayList.get(position).getUrl());
           }
       });}
    }

    @Override
    public int getItemViewType(int position) {
        if (position==getItemCount()-1){
            return TYPE_LOADMORE;
        }
        return TYPE_NORMAL;
    }

    @Override
    public int getItemCount() {
        return qiushibaikeArrayList.size()+1;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
TextView qb_text;
        ImageView qb_img;

    public MyViewHolder(View itemView) {
        super(itemView);
    qb_text= (TextView) itemView.findViewById(R.id.qb_text);
        qb_img= (ImageView) itemView.findViewById(R.id.qb_image);

    }
    static class LoadMoreHolder extends RecyclerView.ViewHolder{
        public LoadMoreHolder(View itemView) {
            super(itemView);
        }
    }
}

}
