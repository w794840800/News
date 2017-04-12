package comn.example.administrator.news.fragment.mvp.view;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Calendar;

import comn.example.administrator.news.R;
import comn.example.administrator.news.adapter.zhihuRecyclerAdapter;
import comn.example.administrator.news.fragment.mvp.constract.zhihuConstract;
import comn.example.administrator.news.fragment.mvp.presenter.zhihuPresenter;
import comn.example.administrator.news.jean.zhihu;
import comn.example.administrator.news.service.CacheService;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/4/8.
 */

public class ZhihuhuFragment extends Fragment implements zhihuConstract.View {
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    zhihuRecyclerAdapter zhihuRecyclerAdapter;
    zhihuConstract.Presenter presenter;
    CacheService cacheService;
    View mainView;
    int year = Calendar.getInstance().get(Calendar.YEAR);
    int month = Calendar.getInstance().get(Calendar.MONTH);
    int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.basic_recycleview, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            boolean isSlidingToLast = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    LinearLayoutManager manager = (LinearLayoutManager)
                            recyclerView.getLayoutManager();
                    //获取最后一个可见的
                    int LastCompletelyVisibleItemPosition = manager.
                            findLastCompletelyVisibleItemPosition();
                    //获取所有item
                    int totalCount = manager.getItemCount();
                    if (LastCompletelyVisibleItemPosition == totalCount - 1 && isSlidingToLast) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year, month, --day);
                        presenter.loadMore(calendar.getTimeInMillis());
                    }


                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    isSlidingToLast = true;
                }
            }
        });
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent,
                R.color.colorPrimaryDark, R.color.colorPrimary);
        new zhihuPresenter(getActivity(), this);
        presenter.start();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.refresh();
            }
        });
        mainView = view;
        return view;
    }

    @Override
    public void setpresenter(zhihuConstract.Presenter presenter) {
        if (this.presenter == null) {
            this.presenter = presenter;
        }
    }

    @Override
    public void showError() {
        Snackbar.make(mainView, "error", Snackbar.LENGTH_SHORT)
                .show();
    }

    @Override
    public void ShowLoading() {
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
    public void showResult(ArrayList<zhihu.StoriesBean> arrayList) {
        if (zhihuRecyclerAdapter == null) {
            zhihuRecyclerAdapter = new zhihuRecyclerAdapter(getActivity(), arrayList);
            recyclerView.setAdapter(zhihuRecyclerAdapter);
        } else {
            zhihuRecyclerAdapter.notifyDataSetChanged();

        }

    }

    @Override
    public void showDatePicker() {

    }
}
