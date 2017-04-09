package comn.example.administrator.news.fragment.mvp.presenter;

import android.content.Context;

import java.util.ArrayList;

import comn.example.administrator.news.db.DaoUtils;
import comn.example.administrator.news.fragment.mvp.model.Imodel;
import comn.example.administrator.news.fragment.mvp.view.IUserView;
import comn.example.administrator.news.jean.zhihu;

/**
 * Created by Administrator on 2017/4/6.
 */

public class IPresenter {
    ArrayList<zhihu.StoriesBean>storiesBeen;
    IUserView iUserView;
    Imodel imodel;
    DaoUtils daoUtils;
    Context context;
    public IPresenter(IUserView view, DaoUtils utils, Context c){
        daoUtils=utils;
        context=c;
        iUserView=view;
        storiesBeen=new ArrayList<>();
        imodel=new Imodel();
    }
public ArrayList<zhihu.StoriesBean > parseJson(String id){
   storiesBeen=imodel.saveJson(imodel.getJson(context,id),daoUtils);
   return storiesBeen;
}
public ArrayList<zhihu.StoriesBean>getDateFromDB(DaoUtils utils){
    return imodel.getDateFromDB(utils);
}
public void setRecyclerAdapter(){
    iUserView.setRecyclerAdapter();
}
public void cancelRefresh(){
    iUserView.cancelRefresh();

}

}
