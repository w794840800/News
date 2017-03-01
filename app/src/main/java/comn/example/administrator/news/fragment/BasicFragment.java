package comn.example.administrator.news.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import comn.example.administrator.news.R;

/**
 * Created by Administrator on 2017/2/22.
 */

public abstract class BasicFragment extends Fragment {
  Activity mainActivity;
    View rootView;
//Fragment Frament;


  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mainActivity=getActivity();

  }

  @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      View view=initView();
        return view;
    }

    public abstract View initView() ;


    public void initDate(){


    }

}
