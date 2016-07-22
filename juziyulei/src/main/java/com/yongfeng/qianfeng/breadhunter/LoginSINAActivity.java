package com.yongfeng.qianfeng.breadhunter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class LoginSINAActivity extends AppCompatActivity {
    public static final String URLQQ = "http://qzs.qq.com/open/mobile/sdk_common/down_qq.htm?sdkv=2.9.4&status_machine=genymotion_vbox86p_4.2.2_151117_140247&pf=openmobile_android&sdkp=a&format=json&status_version=17&status_os=4.2.2#context=downQQ&time=1469101401468";
    public static final String URLSINA="https://passport.weibo.cn/signin/welcome?entry=mweibo&r=http%3A%2F%2Fpad.weibo.cn%2F%3Fsudaref%3Dwww.baidu.com%26retcode%3D6102&sudaref=www.baidu.com&retcode=6102";
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_sina);
        webView = (WebView) findViewById(R.id.login_web);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setMediaPlaybackRequiresUserGesture(true);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setDomStorageEnabled(true);
        webView.loadUrl(URLSINA);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 在APP内部打开链接，不要调用系统浏览器
                view.loadUrl(url);
                return true;
            }
        });
//
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
