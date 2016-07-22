package com.yongfeng.qianfeng.breadhunter.channel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.sray.httplibrary.IOkTaskCallback;
import com.sray.httplibrary.OkHttpTask;
import com.umeng.analytics.MobclickAgent;
import com.yongfeng.qianfeng.breadhunter.R;
import com.yongfeng.qianfeng.breadhunter.channel.CustomListView;
import com.yongfeng.qianfeng.breadhunter.channel.adapter.BaGuaFootAdapter;
import com.yongfeng.qianfeng.breadhunter.channel.adapter.BuaGuaTalkAdapter;
import com.yongfeng.qianfeng.breadhunter.channel.bean.BaGuaDetailTalk;
import com.yongfeng.qianfeng.breadhunter.channel.bean.BaguaDetail;
import com.yongfeng.qianfeng.breadhunter.urls.ContentURL;

import java.util.ArrayList;
import java.util.List;

public class BaGuaDetailActivity extends AppCompatActivity {
   BaguaDetail bean=null;
    private WebView webView;
    private String id;
    private TextView title;
    private TextView name;
    private TextView time;
    private ImageView image;
    private View heardview;
    private ListView listView;
    private List<BaGuaDetailTalk.DataBean.CommentBean.ListBean> commentlist=new ArrayList<>();
   private  List<BaguaDetail.DataBean.FooterBean.RecommendBean>footerBeanList=new ArrayList<>();
    private BuaGuaTalkAdapter madapter;
    private TextView moretalk;
    private CustomListView footlist;
    private BaGuaFootAdapter footAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bagua_detail);
       listView= (ListView) findViewById(R.id.activity_baguacontent_lv);
      heardview=LayoutInflater.from(this).inflate(R.layout.item_bagua_detail,null);
      View heardview2=LayoutInflater.from(this).inflate(R.layout.heard_baguadetail2_lv,null);
      View heardview3=LayoutInflater.from(this).inflate(R.layout.heard_baguadetail3_lv,null);
        View footview=LayoutInflater.from(this).inflate(R.layout.foot_baguadetail_lv,null);
        View footview2=LayoutInflater.from(this).inflate(R.layout.foot_baguadetail2_lv,null);
         footlist= (CustomListView) footview2.findViewById(R.id.foot_baguamoretail_lv);
         moretalk= (TextView) footview.findViewById(R.id.activity_baguacontentmore_tv);
        listView.addHeaderView(heardview);
        listView.addHeaderView(heardview2);
        listView.addHeaderView(heardview3);
        listView.addFooterView(footview);
        listView.addFooterView(footview2);
        initView();
        initadapter();
        inintiddata();
        initdata();
        initlinsinner();
    }

    private void initlinsinner() {
        moretalk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent=new Intent(BaGuaDetailActivity.this,BaGuaMoreTailkActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);

            }
        });
    }

    private void initadapter() {
         madapter=new BuaGuaTalkAdapter(this,R.layout.item_baguadetail_lv,commentlist);
        listView.setAdapter(madapter);
         footAdapter=new BaGuaFootAdapter(this,R.layout.item_foot2_baguatalk,footerBeanList);
        footlist.setAdapter(footAdapter);
    }

    private void setHeardview() {
        List<String>urlstr=new ArrayList<>();
     String webstr=bean.getData().getContents();
        for (int i = 0; i <bean.getData().getResources().getIMG().size() ; i++) {
            String str=bean.getData().getResources().getIMG().get(i).getThumb();
            urlstr.add(str);
        }
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        webView.getSettings().setJavaScriptEnabled(true);
        for (int i = 0; i <bean.getData().getResources().getIMG().size() ; i++) {
            webstr=webstr.replaceFirst("<!--IMG#"+i+"-->","<IMG src="+urlstr.get(i)+">");
        }
       webView.loadDataWithBaseURL(null,webstr,"text/html", "utf-8", null);
        name.setText(bean.getData().getInfo().getAuthor().getUsername());
        title.setText(bean.getData().getInfo().getTitle());
        time.setText(bean.getData().getInfo().getPublish_time());
        Picasso.with(this).load(bean.getData().getInfo().getImg()).into(image);

    }
    private void initView() {
         webView = (WebView)heardview.findViewById(R.id.activity_baguadetail_web);
         name= (TextView)heardview. findViewById(R.id.activity_baguadetailname_tv);
         title= (TextView)heardview. findViewById(R.id.activity_baguadetailtitle_tv);
         time= (TextView)heardview. findViewById(R.id.activity_baguadetaitime_tv);
         image= (ImageView)heardview. findViewById(R.id.activity_baguadetail_ima);
    }

    private void initdata() {
        OkHttpTask.newInstance(this).enqueue(new IOkTaskCallback() {
            @Override
            public void onSuccess(String result) {
                if(result==null){
                    return;
                }
                Gson gson=new Gson();
               bean=gson.fromJson(result,BaguaDetail.class);
                footerBeanList.addAll(bean.getData().getFooter().getRecommend());
                setHeardview();
                footAdapter.notifyDataSetChanged();
            }
        }).start(ContentURL.BAGUADETAIL1+id+ContentURL.BAGUADETAIL2);
        OkHttpTask.newInstance(this).enqueue(new IOkTaskCallback() {
            @Override
            public void onSuccess(String result) {
                if(result==null){
                    return;
                }
                Gson gson=new Gson();
                BaGuaDetailTalk bagua=gson.fromJson(result,BaGuaDetailTalk.class);
                commentlist.addAll(bagua.getData().getComment().getList());
                  madapter.notifyDataSetChanged();
            }
        }).start(ContentURL.BAGUATAILK1+id+ContentURL.BAGUATAILK2);
    }
    private void inintiddata() {
        Intent intent=getIntent();
         id=intent.getStringExtra("id");

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
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
