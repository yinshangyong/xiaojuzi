package com.yongfeng.qianfeng.breadhunter.channel.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import com.android.liuzhuang.library.Barrage;
import com.android.liuzhuang.library.model.BarrageDo;
import com.android.liuzhuang.library.ui.BarrageView;
import com.google.gson.Gson;
import com.sray.httplibrary.IOkTaskCallback;
import com.sray.httplibrary.OkHttpTask;
import com.yongfeng.qianfeng.breadhunter.R;
import com.yongfeng.qianfeng.breadhunter.channel.bean.BaguaDetail;
import com.yongfeng.qianfeng.breadhunter.channel.bean.Comment;
import com.yongfeng.qianfeng.breadhunter.urls.ContentURL;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BaGuaDetailActivity extends AppCompatActivity {
   BaguaDetail bean=null;
    private WebView webView;
    private String id;
    List<Comment.DataBean.ListBean>listBeen=new ArrayList<>();
    private BarrageView barrageView;
    private Barrage barrage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ba_gua_detail);
        initView();
        inintiddata();
        setView();
        initdata();
    }

    private void initView() {
         webView = (WebView) findViewById(R.id.activity_baguadetail_web);
         barrage = new Barrage(this, (BarrageView) findViewById(R.id.activity_baguadetail_ima));
    }

    private void initdata() {
        OkHttpTask.newInstance(this).enqueue(new IOkTaskCallback() {
            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
          //      bean=gson.fromJson(result,BaguaDetail.class);
                Comment comment=gson.fromJson(result,Comment.class);
                listBeen.addAll(comment.getData().getList());
                setView();
            }
        }).start(ContentURL.COMMENT);
    }

    private void setView() {
        Random random=new Random();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(ContentURL.BAGUAWEB1+id+ContentURL.BAGUAWEB2);
        List<BarrageDo> data = new ArrayList<>();
        for (int i = 0; i <listBeen.size() ; i++) {
            data.add(new BarrageDo.Builder().
                    setText(listBeen.get(i).getContent())
                    .setMillisecondFromStart(1000*random.nextInt(5))
                    .setVelocity(5)
                    .setTextSize(20)
                    .setOffsetFromMargin(100+ random.nextInt(1000))
                    .setAcceleration(0)
                  .build());
            barrage.addDataList(data);
            barrage.start();
        }
    }

    private void inintiddata() {
        Intent intent=getIntent();
         id=intent.getStringExtra("id");

    }

}
