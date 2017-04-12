package comn.example.administrator.news.qiubai.constract.view;

import android.app.Fragment;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import comn.example.administrator.news.adapter.qiubaiRecyclerAdapter;

import java.util.ArrayList;

import comn.example.administrator.news.R;
import comn.example.administrator.news.fragment.BasicFragment;
import comn.example.administrator.news.jean.qiushibaike;
import comn.example.administrator.news.qiubai.constract.constract.qiubaiConstract;
import comn.example.administrator.news.qiubai.constract.presenter.qiubaiPresenter;

/**
 * Created by Administrator on 2017/4/11.
 */

public class qbFragment extends BasicFragment implements qiubaiConstract
        .View {
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    qiubaiRecyclerAdapter qiubaiRecyclerAdapter;
    qiubaiConstract.Presenter presenter;
int i=0;
    @Override
    public void setpresenter(qiubaiConstract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void shoeError() {
        Snackbar.make(recyclerView, "error", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });
    }

    @Override
    public void stopLoading() {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void showResult(final ArrayList<qiushibaike> arrayList) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (qiubaiRecyclerAdapter == null) {
                    qiubaiRecyclerAdapter = new qiubaiRecyclerAdapter(getActivity(), arrayList);

                    recyclerView.setAdapter(qiubaiRecyclerAdapter);
                } else {
                    qiubaiRecyclerAdapter.notifyDataSetChanged();

                }
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View initView() {
        final View view = LayoutInflater.from(getActivity()).
                inflate(R.layout.qiubaifragment, null);
      getActivity().getWindow().setStatusBarColor(R.color.yellow);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
          boolean isScroll=false;
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    int LastCompletelyVisibleItemPosition = manager.findLastCompletelyVisibleItemPosition();
                    int total = manager.getItemCount();
                    if (LastCompletelyVisibleItemPosition==total-1&&isScroll){
                        presenter.loadMore();
                        Log.d("loadMore",""+ ++i);
                    }

                }


            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                isScroll=(dy>0);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.refresh();
            }
        });
        new qiubaiPresenter(getActivity(), this);
        presenter.start();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }
}
