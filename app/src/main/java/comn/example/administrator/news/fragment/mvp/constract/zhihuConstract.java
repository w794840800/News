package comn.example.administrator.news.fragment.mvp.constract;

import java.util.ArrayList;

import comn.example.administrator.news.bean;
import comn.example.administrator.news.fragment.BasePresenter;
import comn.example.administrator.news.fragment.mvp.BaseView;
import comn.example.administrator.news.jean.zhihu;


/**
 * Created by Administrator on 2017/4/8.
 */

    public interface zhihuConstract {
    interface View extends BaseView<Presenter>{
//展示错误
    void showError();
//显示正在加载
        void ShowLoading();
//停止加载
void stopLoading();
//成功获取数据，加载
        void showResult(ArrayList<zhihu.StoriesBean>arrayList);
//显示指定日期
        void showDatePicker();
    }

   interface Presenter extends BasePresenter{
      //请求数据
        void loadDate(String date,boolean isContinuteAdd);
   //刷新数据
   void refresh();
       //加载更多
       void loadMore(long date);
       //显示详情
       void showwDetail(int position);
   }

}
