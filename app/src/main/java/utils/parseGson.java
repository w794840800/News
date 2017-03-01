package utils;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import comn.example.administrator.news.bean;
import comn.example.administrator.news.jean.weixinjinxuan;

/**
 * Created by Administrator on 2017/2/24.
 */

public class parseGson {

    public static ArrayList<weixinjinxuan.NewslistBean> parseDate(String date){
        ArrayList<String>arrayList=new ArrayList<>();
        Gson gson=new Gson();
           weixinjinxuan weixinjinxuan=gson.fromJson(date, weixinjinxuan.class);

        return (ArrayList<comn.example.administrator.news.jean.weixinjinxuan.NewslistBean>) weixinjinxuan.getNewslist();
    }


}
