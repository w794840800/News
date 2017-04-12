package comn.example.administrator.news.qiubai.constract.constract;

import android.view.View;

import java.util.ArrayList;

import comn.example.administrator.news.fragment.mvp.BasePresenter;
import comn.example.administrator.news.fragment.mvp.BaseView;
import comn.example.administrator.news.jean.qiushibaike;

/**
 * Created by Administrator on 2017/4/11.
 */

public interface qiubaiConstract {

   public interface View extends BaseView<Presenter>{


       void shoeError();
       void showLoading();
       void stopLoading();
       void showResult(ArrayList<qiushibaike>arrayList);

   }
   public interface Presenter extends BasePresenter{

   void showLoading(boolean isLoadingMore);
       void refresh();
       void loadMore();
   }
}
