package comn.example.administrator.news;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ImageFormat;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.DraweeView;

import comn.example.administrator.news.net.ConnectionUtils;

public class webViewActivity extends AppCompatActivity {
WebView webView;
    String url;
   // DraweeView draweeView;
    ProgressBar progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_web_view);
        url=getIntent().getStringExtra("url");

        initView();

        //getSupportActionBar().setSubtitle(content);

    }

    private void initView() {
progress= (ProgressBar) findViewById(R.id.progress);
       // draweeView= (DraweeView) findViewById(R.id.draweeView);
        webView= (WebView) findViewById(R.id.webview);
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
        //设置 缓存模式


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

                 String javascript="javascript:function hide(){document.getElementsByClassName('header')[0].remove();document.getElementsByClassName('share-wrapper')[0].remove();document.getElementsByClassName('related-wrapper')[0].remove();document.getElementsByClassName('comment-wrapper')[0].remove();document.getElementsByClassName(\"select-wrapper\")[0].remove();}";
                 view.loadUrl(javascript);
             view.loadUrl("javascript:hide();");
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

}
