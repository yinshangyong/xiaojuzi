package com.yongfeng.qianfeng.breadhunter.find;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;

import com.google.gson.Gson;
import com.yongfeng.qianfeng.breadhunter.R;
import com.yongfeng.qianfeng.breadhunter.find.adapter.HotStarGvAdapter;
import com.yongfeng.qianfeng.breadhunter.find.adapter.ListStarGvAdapter;
import com.yongfeng.qianfeng.breadhunter.find.bean.Stars;
import com.yongfeng.qianfeng.breadhunter.find.constast.URLConstast;
import com.yongfeng.qianfeng.breadhunter.tools.http.HttpUtil;
import com.yongfeng.qianfeng.breadhunter.tools.http.IRequestCallBack;

import java.util.ArrayList;
import java.util.List;

public class MoreStarActivity extends AppCompatActivity {

    private MyGirdView hotgridView,listgridView;
    private EditText more_star_search;
    private HotStarGvAdapter hotadapter;
    private ListStarGvAdapter listStarGvAdapter;
    private List<Stars.DataBean.HotBean> hotBeen = new ArrayList<>();
    private List<Stars.DataBean.ListBean> listBeen = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_star);
        more_star_search = (EditText) findViewById(R.id.more_star_search);
        more_star_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoreStarActivity.this,SearchActivity.class);
                startActivity(intent);
            }
        });
        hotgridView = (MyGirdView) findViewById(R.id.star_gv_hot);
        listgridView = (MyGirdView) findViewById(R.id.star_gv_allstar);
        hotadapter = new HotStarGvAdapter(hotBeen,this);
        listStarGvAdapter = new ListStarGvAdapter(listBeen,this);
        hotgridView.setAdapter(hotadapter);
        listgridView.setAdapter(listStarGvAdapter);
        HttpUtil.requestGet(URLConstast.STARS, new IRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                Stars stars = gson.fromJson(result,Stars.class);
                listBeen.addAll(stars.getData().getList());
                hotBeen.addAll(stars.getData().getHot());
                hotadapter.notifyDataSetChanged();
                listStarGvAdapter.notifyDataSetChanged();
                hotgridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String ip = hotBeen.get(position).getId() + "";
                        Intent intent = new Intent(MoreStarActivity.this,StarWebActivity.class);
                        intent.putExtra("id", ip);
                        startActivity(intent);
                    }
                });
                listgridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String ip = listBeen.get(position).getId() + "";
                        Intent intent = new Intent(MoreStarActivity.this, StarWebActivity.class);
                        intent.putExtra("id", ip);
                        startActivity(intent);
                    }
                });
            }
        });
    }
    public void onClick(View view){
        onBackPressed();
    }
}
