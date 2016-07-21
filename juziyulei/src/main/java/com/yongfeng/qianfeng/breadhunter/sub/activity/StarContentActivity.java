package com.yongfeng.qianfeng.breadhunter.sub.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.sray.httplibrary.IOkTaskCallback;
import com.sray.httplibrary.OkHttpTask;
import com.yongfeng.qianfeng.breadhunter.R;
import com.yongfeng.qianfeng.breadhunter.channel.adapter.FragmentAdapter;
import com.yongfeng.qianfeng.breadhunter.sub.bean.StarContentInfo;
import com.yongfeng.qianfeng.breadhunter.sub.fragment.RuleContentFragment;
import com.yongfeng.qianfeng.breadhunter.sub.fragment.RuleMyListFragment;
import com.yongfeng.qianfeng.breadhunter.sub.fragment.RuleStarFragment;
import com.yongfeng.qianfeng.breadhunter.urls.ContentURL;

import java.util.ArrayList;
import java.util.List;

public class StarContentActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TextView name;
    private TextView num;
    private ImageView imageView;
    private TextView more;
   private  StarContentInfo.DataBean.StarBean starinfobean;
    private List<String> mTitleList = new ArrayList<>();
    private List<Fragment> fragmentList;
    private FragmentAdapter fragmentAdapter;
    private TextView content;
    private Toolbar mToolbar;
    private String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_star_content);
        initIddata();
        initview();
        initJsondata();

    }

    private void initIddata() {
        Intent intent=getIntent();
         id=intent.getStringExtra("id");
    }

    private void initAdapter() {
        fragmentAdapter=new FragmentAdapter(this.getSupportFragmentManager(),fragmentList,mTitleList);
        viewPager.setAdapter(fragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initJsondata() {
        OkHttpTask.newInstance(this).enqueue(new IOkTaskCallback() {
            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
               StarContentInfo bean=gson.fromJson(result,StarContentInfo.class);
                starinfobean=bean.getData().getStar();
               mTitleList=starinfobean.getModule();
                getTab();
                getFragmentlist();
                initAdapter();
                setinitView();
                initlinsinner();
            }
        }).start(ContentURL.STARTINFO+id);
    }

    private void initlinsinner() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //使用CollapsingToolbarLayout必须把title设置到CollapsingToolbarLayout上，设置到Toolbar上则不会显示
        CollapsingToolbarLayout mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.activity_startcontent_ctl);
        mCollapsingToolbarLayout.setTitle(starinfobean.getName());
        mCollapsingToolbarLayout.setCollapsedTitleGravity(Gravity.CENTER);
        //通过CollapsingToolbarLayout修改字体颜色
        mCollapsingToolbarLayout.setExpandedTitleColor(0*00000000);//设置还没收缩时状态下字体颜色
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);//设置收缩后Toolbar上字体的颜色

    }

    private void setinitView() {
        name.setText(starinfobean.getName());
        num.setText("---------本周积分:"+starinfobean.getWeek_bonus()+"------");
        content.setText(starinfobean.getDescription());
        Picasso.with(this).load(starinfobean.getBgpic()).into(imageView);
    }

    private void initview() {
        tabLayout= (TabLayout) findViewById(R.id.activity_startcontentinfo_tab);
        viewPager= (ViewPager) findViewById(R.id.activity_startcontentinfo_vp);
         name= (TextView) findViewById(R.id.activity_startcontentinfoname_tv);
         num= (TextView) findViewById(R.id.activity_startcontentinfonum_tv);
         imageView= (ImageView) findViewById(R.id.activity_startcontentinfo_ima);
         more= (TextView) findViewById(R.id.activity_startcontentinfomore_tv);
         content= (TextView) findViewById(R.id.activity_startcontentinfocon_tv);
        mToolbar = (Toolbar) findViewById(R.id.activity_startcontent_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    public void getTab( ){
        for(int i=0;i<mTitleList.size();i++){
            tabLayout.addTab(tabLayout.newTab().setText(mTitleList.get(i)));
        }

        //  mtablayot.setTabGravity(TabLayout.MODE_SCROLLABLE);
    }
    public void getFragmentlist() {
         fragmentList=new ArrayList<>();
         for(int i=0;i<mTitleList.size();i++){
             fragmentList.add(RuleMyListFragment.newInstance());
         }
    }

}
