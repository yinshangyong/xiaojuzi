package com.yongfeng.qianfeng.breadhunter.channel.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;
import com.sray.httplibrary.IOkTaskCallback;
import com.sray.httplibrary.OkHttpTask;
import com.yongfeng.qianfeng.breadhunter.R;
import com.yongfeng.qianfeng.breadhunter.channel.adapter.BuaGuaMoreTalkAdapter;
import com.yongfeng.qianfeng.breadhunter.channel.bean.Comment;
import com.yongfeng.qianfeng.breadhunter.urls.ContentURL;

import java.util.ArrayList;
import java.util.List;

public class BaGuaMoreTailkActivity extends AppCompatActivity {
    private List<Comment.DataBean.ListBean> morelist=new ArrayList<>();
    private String id;
    private ListView listView;
    private BuaGuaMoreTalkAdapter madapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ba_gua_more_tailk);
        initview();
        initid();
        initdata();
        initAdapter();

    }

    private void initAdapter() {
         madapter=new BuaGuaMoreTalkAdapter(this,R.layout.item_baguadetail_lv,morelist);
        listView.setAdapter(madapter);
    }

    private void initdata() {
        OkHttpTask.newInstance(this).enqueue(new IOkTaskCallback() {
            @Override
            public void onSuccess(String result) {
                if(result==null){
                    return;
                }
                Gson gson=new Gson();
                Comment comment=gson.fromJson(result,Comment.class);
                morelist.addAll(comment.getData().getList());
                madapter.notifyDataSetChanged();
            }
        }).start(ContentURL.BAGUAMORETAILK1+id+ContentURL.BAGUAMORETAILK2);
    }

    private void initid() {
        Intent intent=getIntent();
        id=intent.getStringExtra("id");
    }

    private void initview() {
         listView= (ListView) findViewById(R.id.activity_baguamore_lv);
    }
}
