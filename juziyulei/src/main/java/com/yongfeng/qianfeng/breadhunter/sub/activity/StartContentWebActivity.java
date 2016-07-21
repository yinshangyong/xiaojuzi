package com.yongfeng.qianfeng.breadhunter.sub.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Interpolator;
import android.webkit.WebView;

import com.yongfeng.qianfeng.breadhunter.R;

public class StartContentWebActivity extends AppCompatActivity {

    private String id;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_content_web);
        initdata();
        initview();

    }

    private void initview() {
         webView= (WebView) findViewById(R.id.activity_wenstar_web);
    }

    private void initdata() {
        Intent intent=getIntent();
         id=intent.getStringExtra("id");

    }
}
