package com.yongfeng.qianfeng.breadhunter.sub.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.sray.httplibrary.IOkTaskCallback;
import com.sray.httplibrary.OkHttpTask;
import com.yongfeng.qianfeng.breadhunter.R;
import com.yongfeng.qianfeng.breadhunter.sub.adapter.ContentEndAdapter;
import com.yongfeng.qianfeng.breadhunter.sub.bean.RuleContent;
import com.yongfeng.qianfeng.breadhunter.sub.bean.RuleContentEnd;
import com.yongfeng.qianfeng.breadhunter.urls.ContentURL;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RuleContentendActivity extends AppCompatActivity {

    private ListView listView;
    private List<RuleContentEnd.DataBean.ListBean>beanList=new ArrayList<>();
    private RuleContentEnd.DataBean.InfoBean infoBean=null;
    private ContentEndAdapter madapter;
    private String id;
    private ImageView imageView;
    private CircleImageView moimage;
    private TextView name;
    private TextView issub;
    private Toolbar mToolbar;
    private String name2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rule_contentend);
        initdata();
        initview();
        initAdapter();
        initjsondata();

    }

    private void initlinner() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //使用CollapsingToolbarLayout必须把title设置到CollapsingToolbarLayout上，设置到Toolbar上则不会显示
        CollapsingToolbarLayout mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
        mCollapsingToolbarLayout.setTitle(name2);
        mCollapsingToolbarLayout.setCollapsedTitleGravity(Gravity.CENTER);
        //通过CollapsingToolbarLayout修改字体颜色
        mCollapsingToolbarLayout.setExpandedTitleColor(0*00000000);//设置还没收缩时状态下字体颜色
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);//设置收缩后Toolbar上字体的颜色
        mCollapsingToolbarLayout.setContentScrim( imageView.getBackground());
    }

    private void initdata() {
        Intent intent=getIntent();
         id=intent.getStringExtra("id");
    }

    private void initAdapter() {
        madapter=new ContentEndAdapter(this,beanList);
        listView.setAdapter(madapter);
    }

    private void initjsondata() {
        OkHttpTask.newInstance(this).enqueue(new IOkTaskCallback() {
            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                RuleContentEnd end=gson.fromJson(result,RuleContentEnd.class);
                beanList.addAll(end.getData().getList());
                infoBean=end.getData().getInfo();
                 name2=infoBean.getName();
                setView();
                initlinner();
                madapter.notifyDataSetChanged();

            }
        }).start(ContentURL.CONTENTEND1+id+ContentURL.CONTENTEND2);
    }
    private void setView() {
        Picasso.with(this).load(infoBean.getImg()).into(imageView);
        Picasso.with(this).load(infoBean.getImg()).into(moimage);
        name.setText(infoBean.getName());
         if(infoBean.isIssub()){
             issub.setText("订阅");
         }else {
             issub.setText("已订阅");
         }
    }

    private void initview() {
         listView= (ListView) findViewById(R.id.activity_rulecontentend_lv);
         imageView= (ImageView) findViewById(R.id.activity_rulecontentend_ima);
         moimage= (CircleImageView) findViewById(R.id.activity_rulecontentend_sima);
         name= (TextView) findViewById(R.id.activity_rulecontentend_nametv);
         issub= (TextView) findViewById(R.id.activity_rulecontentend_issubtv);
         mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
}
