package com.yongfeng.qianfeng.breadhunter.find;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.yongfeng.qianfeng.breadhunter.R;
import com.yongfeng.qianfeng.breadhunter.find.adapter.MoreHotAdapter;
import com.yongfeng.qianfeng.breadhunter.find.bean.HotNotic;
import com.yongfeng.qianfeng.breadhunter.find.constast.URLConstast;
import com.yongfeng.qianfeng.breadhunter.tools.http.HttpUtil;
import com.yongfeng.qianfeng.breadhunter.tools.http.IRequestCallBack;

import java.util.ArrayList;
import java.util.List;

public class MoreHotActivity extends AppCompatActivity {

    private List<HotNotic.DataBean.ListBean> listBeen = new ArrayList<>();
    HotNotic.DataBean.ListBean bean = null;
    private MoreHotAdapter moreHotAdapter;
    private ListView mListView;
    private View headview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_more_hot);
        mListView = (ListView) findViewById(R.id.more_hot_list);
        moreHotAdapter = new MoreHotAdapter(this, listBeen);
        mListView.setAdapter(moreHotAdapter);

        HttpUtil.requestGet(URLConstast.MOREHOT, new IRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                HotNotic hotNotic = gson.fromJson(result, HotNotic.class);
                listBeen.addAll(hotNotic.getData().getList());
                bean = listBeen.get(0);
                headView();
                mListView.addHeaderView(headview);
                listBeen.remove(0);
                moreHotAdapter.notifyDataSetChanged();
                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String ip = listBeen.get(position-1).getId()+"";
                        Intent intent = new Intent(MoreHotActivity.this,WatchWebActivity.class);
                        intent.putExtra("id", ip);
                        startActivity(intent);
                    }
                });
            }
        });
    }

    private void headView() {
        headview = LayoutInflater.from(this).inflate(R.layout.more_hot_head, null);
        final TextView tv_hot_bang_title = (TextView) headview.findViewById(R.id.tv_hot_bang_title);
        final TextView topcat_name = (TextView) headview.findViewById(R.id.topcat_name);
        final TextView top_readnum = (TextView) headview.findViewById(R.id.top_readnum);
        final ImageView hot_bang_iv = (ImageView) headview.findViewById(R.id.hot_bang_iv);
        tv_hot_bang_title.setText(bean.getTitle());
        topcat_name.setText(bean.getCat().getName());
        top_readnum.setText(bean.getReadnum() + "");
        Picasso.with(MoreHotActivity.this).load(listBeen.get(0).getPic()).into(hot_bang_iv);
        headview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ip = bean.getId()+"";
                Intent intent = new Intent(MoreHotActivity.this,WatchWebActivity.class);
                intent.putExtra("id", ip);
                startActivity(intent);
            }
        });
    }
    public void onClick(View v){
        onBackPressed();
    }
}
