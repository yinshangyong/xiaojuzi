package com.yongfeng.qianfeng.breadhunter.find;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.yongfeng.qianfeng.breadhunter.R;
import com.yongfeng.qianfeng.breadhunter.find.constast.URLConstast;

import java.net.URLDecoder;

public class BannerWebActivity extends AppCompatActivity {

    private WebView webView;
    private  String url;
    private String getUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_web);
        String id = getIntent().getStringExtra("id");
        url = URLDecoder.decode(id);
        if (url.contains("http")){
            getUrl = url;
        }else {
            getUrl = URLConstast.BANNERSPHLE+url;
        }
        webView = (WebView) findViewById(R.id.banner_web);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setMediaPlaybackRequiresUserGesture(true);
//        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setDomStorageEnabled(true);
        webView.loadUrl(getUrl);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 在APP内部打开链接，不要调用系统浏览器
                view.loadUrl(url);
                return true;
            }
        });

    }
    @Override
    protected void onStop() {
        super.onStop();
        webView.stopLoading();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.destroy();
    }
    }

