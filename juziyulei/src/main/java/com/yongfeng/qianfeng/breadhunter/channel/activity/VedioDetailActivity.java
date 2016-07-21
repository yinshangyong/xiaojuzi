package com.yongfeng.qianfeng.breadhunter.channel.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.sray.httplibrary.IOkTaskCallback;
import com.sray.httplibrary.OkHttpTask;
import com.yongfeng.qianfeng.breadhunter.R;
import com.yongfeng.qianfeng.breadhunter.channel.CustomListView;
import com.yongfeng.qianfeng.breadhunter.channel.adapter.BuaGuaTalkAdapter;
import com.yongfeng.qianfeng.breadhunter.channel.adapter.VedioDetailAdapter;
import com.yongfeng.qianfeng.breadhunter.channel.adapter.VedioDetailFootAdapter;
import com.yongfeng.qianfeng.breadhunter.channel.adapter.VedioDetailHeardAdapter;
import com.yongfeng.qianfeng.breadhunter.channel.bean.BaGuaDetailTalk;
import com.yongfeng.qianfeng.breadhunter.channel.bean.BaguaDetail;
import com.yongfeng.qianfeng.breadhunter.channel.bean.Vedio;
import com.yongfeng.qianfeng.breadhunter.channel.bean.VedioDetail;
import com.yongfeng.qianfeng.breadhunter.channel.bean.VedioDetailHeard;
import com.yongfeng.qianfeng.breadhunter.urls.ContentURL;

import java.util.ArrayList;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class VedioDetailActivity extends AppCompatActivity {

    private ListView listView;
    private CustomListView footlist;
    private TextView moretalk;
    private String id;
    private List<VedioDetail.DataBean.CommentBean.ListBean> commentlist=new ArrayList<>();
    private  List<VedioDetailHeard.DataBean.FooterBean.RecommendBean>footerBeanList=new ArrayList<>();

    private VedioDetailAdapter madapter=null;
    private VedioDetailHeard.DataBean.InfoBean infoBean=null;
    private View heardview1;
    private VedioDetailFootAdapter footAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vedio_detail);
         listView= (ListView) findViewById(R.id.activity_vediodetail_lv);
         heardview1=LayoutInflater.from(this).inflate(R.layout.heard_vediodetail_lv,null);
        View heardview2= LayoutInflater.from(this).inflate(R.layout.heard_baguadetail2_lv,null);
        View heardview3=LayoutInflater.from(this).inflate(R.layout.heard_baguadetail3_lv,null);
        View footview=LayoutInflater.from(this).inflate(R.layout.foot_baguadetail_lv,null);
        View footview2=LayoutInflater.from(this).inflate(R.layout.foot_baguadetail2_lv,null);
        listView.addHeaderView(heardview1);
        listView.addHeaderView(heardview2);
        listView.addHeaderView(heardview3);
        listView.addFooterView(footview);
        listView.addFooterView(footview2);
        footlist= (CustomListView) footview2.findViewById(R.id.foot_baguamoretail_lv);
        moretalk= (TextView) footview.findViewById(R.id.activity_baguacontentmore_tv);
       initiddata();
        initadapter();
        initdata();
        initlinsinner();
    }

    private void initlinsinner() {
        moretalk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(VedioDetailActivity.this,BaGuaMoreTailkActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
    }

    private void initadapter() {
        madapter=new VedioDetailAdapter(this,R.layout.item_baguadetail_lv,commentlist);
        listView.setAdapter(madapter);
         footAdapter=new VedioDetailFootAdapter(this,R.layout.item_foot2_baguatalk,footerBeanList);
        footlist.setAdapter(footAdapter);
    }
    private void initdata() {
        OkHttpTask.newInstance(this).enqueue(new IOkTaskCallback() {
            @Override
            public void onSuccess(String result) {
                if(result==null){
                    return;
                }
                Gson gson=new Gson();
                VedioDetail bagua=gson.fromJson(result,VedioDetail.class);
                commentlist.addAll(bagua.getData().getComment().getList());
                madapter.notifyDataSetChanged();
            }
        }).start(ContentURL.BAGUATAILK1+id+ContentURL.BAGUATAILK2);
        OkHttpTask.newInstance(this).enqueue(new IOkTaskCallback() {
            @Override
            public void onSuccess(String result) {
                if(result==null){
                    return;
                }
                Gson gson=new Gson();
                VedioDetailHeard heard=gson.fromJson(result,VedioDetailHeard.class);
                infoBean=heard.getData().getInfo();
                footerBeanList.addAll(heard.getData().getFooter().getRecommend());
                       footAdapter.notifyDataSetChanged();
               Setheardview();
            }
        }).start(ContentURL.BAGUADETAIL1+id+ContentURL.BAGUADETAIL2);


    }

    private void Setheardview() {
        TextView name= (TextView) heardview1.findViewById(R.id.heard_vediodetailname_tv);
        TextView title= (TextView) heardview1.findViewById(R.id.heard_vediodetailtitle_tv);
        TextView time= (TextView) heardview1.findViewById(R.id.heard_vediodetailtime_tv);
        TextView texted= (TextView) heardview1.findViewById(R.id.heard_vediodetailtexted_tv);
        JCVideoPlayerStandard vedioplay= (JCVideoPlayerStandard) heardview1.findViewById(R.id.heard_vediodetai_jc);

       if(infoBean.getCat()!=null){
           name.setText(infoBean.getCat().getName());
           title.setText(infoBean.getTitle());
           time.setText(infoBean.getPublish_time());
           texted.setText(infoBean.getTxtlead());
           vedioplay.setUp(infoBean.getVideo().getUrl(),"");
           ImageView imageView=vedioplay.thumbImageView;
           imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
           Picasso.with(this).load(infoBean.getImg()).into(imageView);
       }

    }
    private void initiddata() {
        Intent intent=getIntent();
         id=intent.getStringExtra("id");
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
}
