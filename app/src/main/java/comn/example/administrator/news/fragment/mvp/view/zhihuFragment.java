package comn.example.administrator.news.fragment.mvp.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

import comn.example.administrator.news.adapter.zhihuRecyclerAdapter;
import comn.example.administrator.news.R;
import comn.example.administrator.news.db.DaoUtils;
import comn.example.administrator.news.db.dateBaseSQLite;
import comn.example.administrator.news.fragment.mvp.presenter.IPresenter;
import comn.example.administrator.news.jean.zhihu;

/**
 * Created by Administrator on 2017/4/6.
 */

public class zhihuFragment extends Fragment implements IUserView{
   // zhihuRecyclerAdapter
    DaoUtils daoUtils;
    ArrayList<zhihu.StoriesBean>arrayList;
    RecyclerView recyclerView;
    zhihuRecyclerAdapter zhihuRecyclerAdapter;
    IPresenter ipresent;
    dateBaseSQLite dateBaseSQLite;
    SwipeRefreshLayout swipeRefreshLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
    View view=inflater.inflate(R.layout.basic_recycleview,container,false);
    dateBaseSQLite=new dateBaseSQLite(getActivity(),null);
        daoUtils=new DaoUtils(getActivity(),dateBaseSQLite);
        recyclerView= (RecyclerView) view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        swipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh);
             ipresent=new IPresenter(this,daoUtils);
              arrayList=new ArrayList<>();
        initDate();
     //   return super.onCreateView(inflater, container, savedInstanceState);}
   return  view;
    }

    private void initDate() {
        //ipresent.parseJson("latest");
        //arrayList= ipresent.getDateFromDB(daoUtils);
       // Collections.reverse(arrayList);
       // Log.d("DateFromDBSize",arrayList.size()+"");
zhihuRecyclerAdapter=new zhihuRecyclerAdapter(getActivity(),arrayList);
    ipresent.setRecyclerAdapter();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Toast.makeText(mainActivity,"refresh",Toast.LENGTH_SHORT).show();
                ipresent.parseJson("latest");
                arrayList.clear();
               arrayList=ipresent.getDateFromDB(daoUtils);
                zhihuRecyclerAdapter.setArrayList(arrayList);
                zhihuRecyclerAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        //判断数据库是否为空，不为空就进行数据库读取
        //为空就进行网络加载
        //直接读取给集合

        if (daoUtils.read("select * from zhihu").getCount()!=0){
           // readFromDB(daoUtils.read("select * from qb"));
            arrayList.clear();
            arrayList=ipresent.getDateFromDB(daoUtils);
            Collections.reverse(arrayList);
            zhihuRecyclerAdapter.setArrayList(arrayList);
            zhihuRecyclerAdapter.notifyDataSetChanged();
        }
        //否则进行网络连接
        else {
            swipeRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
               swipeRefreshLayout.setRefreshing(true);
                }
            });
          //  swipeRefreshLayout.setRefreshing(true);
            Toast.makeText(getActivity(), "refe", Toast.LENGTH_SHORT).show();
            ipresent.parseJson("latest");
            //]ipresent.parseJson("latest");
            arrayList.clear();
            arrayList=ipresent.getDateFromDB(daoUtils);
            Collections.reverse(arrayList);
            zhihuRecyclerAdapter.setArrayList(arrayList);
            zhihuRecyclerAdapter.notifyDataSetChanged();
            swipeRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
               swipeRefreshLayout.setRefreshing(false);
                }
            });
        }


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void setRecyclerAdapter() {
      recyclerView.setAdapter(zhihuRecyclerAdapter);
    }

    @Override
    public void cancelRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }
}

