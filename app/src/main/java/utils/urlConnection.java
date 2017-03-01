package utils;


import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import comn.example.administrator.news.jean.weixinjinxuan;
import comn.example.administrator.news.pages.BasicPages;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/2/24.
 */

public class urlConnection {
static List<weixinjinxuan.NewslistBean> arrayList=new ArrayList<>();
  public static List<weixinjinxuan.NewslistBean> getJsonDate(BasicPages.MyAdapter myAdapter, final String url1){

             /* try {
                  BufferedReader reader;
                  InputStream inputStream = null;
                  URL url1= null;
                  url1 = new URL(url);
                  HttpURLConnection
                          urlConnection= (HttpURLConnection) url1.openConnection();
                  urlConnection.setConnectTimeout(5000);
                  urlConnection.setReadTimeout(5000);
               //   urlConnection.

                  inputStream = urlConnection.getInputStream();

                      reader = new BufferedReader(new InputStreamReader(inputStream));
                      String jsonD = "";
                      StringBuffer stringBuffer = new StringBuffer();
                      while ((jsonD = reader.readLine()) != null) {
                          stringBuffer.append(jsonD);
                          stringBuffer.append("\r\n");
                      }

                      reader.close();
                      jsonD = stringBuffer.toString();
                  Log.d("json","woshijson "+jsonD);
                  List<weixinjinxuan.NewslistBean> arrayList1 = parseGson.parseDate(jsonD);
                  arrayList=arrayList1;
              }
              catch (MalformedURLException e) {
                  e.printStackTrace();
              } catch (IOException e) {
                  e.printStackTrace();
              }*/
      BufferedReader reader = null;
      String result = null;
      StringBuffer sbf = new StringBuffer();
      //httpUrl = httpUrl + "?" + httpArg;

      try {
          URL url = new URL(url1);
          HttpURLConnection connection = (HttpURLConnection) url
                  .openConnection();
          connection.setRequestMethod("GET");
          InputStream is = connection.getInputStream();
          reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
          String strRead = null;
          while ((strRead = reader.readLine()) != null) {
              sbf.append(strRead);
              sbf.append("\r\n");
          }
          reader.close();
          result = sbf.toString();
          Log.d("json",result);
          List<weixinjinxuan.NewslistBean> arrayList1 = parseGson.parseDate(result);
          arrayList=arrayList1;



      } catch (Exception e) {
          e.printStackTrace();
      }
      return arrayList;
  }







}
