package comn.example.administrator.news.qiubai.constract.model;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import comn.example.administrator.news.jean.qiushibaike;
import comn.example.administrator.news.qiubai.constract.constract.qiubaiConstract;

/**
 * Created by Administrator on 2017/4/11.
 */

public class qiubaiModel {
    private ArrayList<qiushibaike> qiushibaikeArrayList=new ArrayList<>();

    public interface onConnectionFinish {
        void onSuccess(ArrayList<qiushibaike> qiushibaikes);

        void onFailured();
    }

    public void load(String url, final int i, final onConnectionFinish onConnectionFinish, final boolean isRefreshOrLoading) {

        if (isRefreshOrLoading){
            qiushibaikeArrayList.clear();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document document = Jsoup.connect("http://www.qiushibaike.com/hot/page/" + i + "/?s=4970033")
                            .get();
                    if (document == null) {
                        onConnectionFinish.onFailured();

                    } else {
                        Elements elements = document.getElementsByClass("article block untagged mb15");
                        qiushibaike qb;
                     //   for (int i = 0; i < elements.size(); i++) {
                        int index=0;
                        for (int i = 0; i < elements.size(); i++) {
                            qb = new qiushibaike();

Log.d("qiubai","qiubai"+  ++index);
                            Elements href = elements.get(i).getElementsByClass("contentHerf");

                            if (href.size() != 0) {
                                for (Element element4 : href) {
                                    qb.setUrl("http://www.qiushibaike.com" + element4.attr("href"));

                                }
                            }


                            Elements elements1 = elements.get(i).getElementsByClass("content");
                            if (elements1.size() != 0) {
                                for (Element element : elements1) {
                                    qb.setTitle(element.text());
                                    Log.d("element text", element.text());
                                }
                            }
                            Elements elements_thumb = elements.get(i).getElementsByClass("thumb");
                            if (elements_thumb != null) {
                                for (Element element : elements_thumb) {
                                    Elements elements_img = element.getElementsByTag("img");
                                    for (Element element1 : elements_img) {

                                        qb.setPicurl(element1.attr("src"));

                                    }


                                }

                            }

                            qiushibaikeArrayList.add(qb);


                        }
                        Log.d("showResult Pre",""+qiushibaikeArrayList.size());

                        onConnectionFinish.onSuccess(qiushibaikeArrayList);

                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }}