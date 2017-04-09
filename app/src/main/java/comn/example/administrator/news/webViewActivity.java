package comn.example.administrator.news;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.DraweeView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import comn.example.administrator.news.net.ConnectionUtils;

public class webViewActivity extends AppCompatActivity {
ArrayList<String> urlList=new ArrayList<>();
    Toolbar toolbar;
    WebView webView;
    String url;
   // DraweeView draweeView;
    ProgressBar progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_web_view);
        toolbar= (Toolbar) findViewById(R.id.content_title);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back);
    getSupportActionBar().setDisplayShowTitleEnabled(false);
        url=getIntent().getStringExtra("url");

        initView();

        //getSupportActionBar().setSubtitle(content);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.webview_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
  switch (item.getItemId()){
      case android.R.id.home:
          finish();
          break;
      case R.id.share:
          showShare();
          break;

        }
        return true;
    }
    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("标题");
        // titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("News");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

        // 启动分享GUI
        oks.show(this);
    }
    @Override
    protected void onResume() {
       // urlList.clear();
     /*   webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);

                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                String javascript="javascript:function hide(){" +
                        "document.getElementsByClassName('header')[0].r" +
                        "emove();document.getElementsByClassName" +
                        "('share-wrapper')[0].remove();" +
                        "document.getElementsByClassName('related-wrapper')" +
                        "[0].remove();document.getElementsByClassName" +
                        "('comment-wrapper')[0].remove();document.getElem" +
                        "entsByClassName(\"select-wrapper\")[0].remove();}";
                view.loadUrl(javascript);

                view.loadUrl("javascript:hide();");
                view.loadUrl("javascript:(function(){"
                        + "var obj = document.getElementsByTagName(\"img\"); "

                        + "for(var i=0;i<obj.length;i++)  " + "{"

                        + "        window.imageListener.savePicUrl(obj[i].src);  "
                        +  "}" + "})()");

                view.loadUrl("javascript:(function(){"
                        + "var objs = document.getElementsByTagName(\"img\"); "

                        + "for(var i=0;i<objs.length;i++)  " + "{"

                        + "    objs[i].onclick=function()  " + "    {  "

                        + "        window.imageListener.openImage(this.src);  "
                        + "    }  " + "}" + "})()");

*//*view.evaluateJavascript("javascript:hide();", new ValueCallback<String>() {
    @Override
    public void onReceiveValue(String value) {

    }
});*//*
                progress.setVisibility(View.INVISIBLE);
                webView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                webView.setVisibility(View.INVISIBLE);
                progress.setVisibility(View.VISIBLE);
            }
        });*/

        super.onResume();
    }

    private void initView() {
progress= (ProgressBar) findViewById(R.id.progress);
       // draweeView= (DraweeView) findViewById(R.id.draweeView);
        webView= (WebView) findViewById(R.id.webview);
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        webView.loadUrl(url);
        //设置 缓存模式
    //    webView.addJavascriptInterface(new MyJavascriptInteraface(getApplicationContext()),"imageListener");


          webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);


       // webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);

// 开启 DOM storage API 功能
        webView.getSettings().setDomStorageEnabled(true);
       // webSetting.setDomStorageEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                //js代码

            /*     String javascript="javascript:function hide(){" +
                         "document.getElementsByClassName('header')[0].r" +
                         "emove();document.getElementsByClassName" +
                         "('share-wrapper')[0].remove();" +
                         "document.getElementsByClassName('related-wrapper')" +
                         "[0].remove();document.getElementsByClassName" +
                         "('comment-wrapper')[0].remove();document.getElem" +
                         "entsByClassName(\"select-wrapper\")[0].remove();}";
                 view.loadUrl(javascript);

             view.loadUrl("javascript:hide();");
                view.loadUrl("javascript:(function(){"
                        + "var obj = document.getElementById(\"img-content\"); "
                         +"var img=obj.getElementsByTagName(\"img\");"
                        + "for(var i=0;i<img.length;i++)  " + "{"

                        + "        window.imageListener.savePicUrl(img[i].src);  "
                        +  "}" + "})()");

                view.loadUrl("javascript:(function(){"
                        + "var obj = document.getElementById(\"img-content\"); "
                        +"var img=obj.getElementsByTagName(\"img\");"
                        + "for(var i=0;i<img.length;i++)  " + "{"

                        + "    img[i].onclick=function()  " + "    {  "

                        + "        window.imageListener.openImage(this.src);  "
                        + "    }  " + "}" + "})()");
*/
/*view.evaluateJavascript("javascript:hide();", new ValueCallback<String>() {
    @Override
    public void onReceiveValue(String value) {

    }
});*/
                progress.setVisibility(View.INVISIBLE);
                webView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
              webView.setVisibility(View.INVISIBLE);
                progress.setVisibility(View.VISIBLE);
            }
        });


    }

    public static void newIntent(Context context,String url){

        Intent intent=new Intent(context,webViewActivity.class);
           intent.putExtra("url",url);
        context.startActivity(intent);


    }
    class MyJavascriptInteraface{
        String[] url;
       // ArrayList<String >urlList;
        Context context;
        public MyJavascriptInteraface(Context c){
            context=c;
            //urlList=new ArrayList<>();
            url=new String[1024];
        }
        @android.webkit.JavascriptInterface
        public void openImage(String img){
            //Toast.makeText(context,img+" ",Toast.LENGTH_SHORT).show();
              Intent intent=new Intent(context,OpenImageActivity.class);
         // intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("img",img);
           // urlList.remove(0);
            intent.putExtra("imags",urlList);
            startActivity(intent);
//finish();
        }
        @android.webkit.JavascriptInterface
public void savePicUrl(String url1){

if ((!(TextUtils.isEmpty(url1)))) {


    urlList.add(url1);
    HttpURLConnection httpURLConnection;
    try {
        URL url=new URL("");
      httpURLConnection= (HttpURLConnection) url.openConnection();

        // httpURLConnection.connect();
        //httpURLConnection.getInputStream();

    } catch (MalformedURLException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }

}
}


    }

}
