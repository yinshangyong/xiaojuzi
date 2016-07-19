package com.yongfeng.qianfeng.breadhunter.find;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yongfeng.qianfeng.breadhunter.R;
import com.yongfeng.qianfeng.breadhunter.find.adapter.SearchKeyGVAdapter;
import com.yongfeng.qianfeng.breadhunter.find.adapter.SearchListAdapter;
import com.yongfeng.qianfeng.breadhunter.find.bean.SearchDetile;
import com.yongfeng.qianfeng.breadhunter.find.bean.SearchKey;
import com.yongfeng.qianfeng.breadhunter.find.constast.URLConstast;
import com.yongfeng.qianfeng.breadhunter.tools.http.HttpUtil;
import com.yongfeng.qianfeng.breadhunter.tools.http.IRequestCallBack;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private GridView mGridView;
    private TextView tv_search, tv_search_del, tv_clear_all;
    private ImageView iv_search_del_his;
    private RelativeLayout relativeLayout;
    private ListView mListView;
    private EditText et_search;
    private SearchListAdapter searchListAdapter;
    private List<SearchDetile.DataBean.ListBean> slistBeen = new ArrayList<>();
    private List<SearchKey.DataBean.ListBean> listBeen = new ArrayList<>();
    private SearchKeyGVAdapter searchKeyGVAdapter;
    private String k;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mGridView = (GridView) findViewById(R.id.search_gv);
        //初始化
        initView();
        searchKeyGVAdapter = new SearchKeyGVAdapter(this, listBeen);
        mGridView.setAdapter(searchKeyGVAdapter);
        HttpUtil.requestGet(URLConstast.HOTSEARCHKEY, new IRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                SearchKey searchKey = gson.fromJson(result, SearchKey.class);
                listBeen.addAll(searchKey.getData().getList());
                searchKeyGVAdapter.notifyDataSetChanged();
                mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        k = listBeen.get(position).getKeyword();
                        et_search.setText(k);
                    }
                });
                //监听事件
                click();
            }
        });

        //listview

    }

    private void setupListview() {
        searchListAdapter = new SearchListAdapter(this, slistBeen);
        mListView.setAdapter(searchListAdapter);
        String key = URLEncoder.encode(et_search.getText().toString());
        HttpUtil.requestGet(URLConstast.SEARCH1 + key + URLConstast.SEARCH2, new IRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                SearchDetile ss = gson.fromJson(result, SearchDetile.class);
                slistBeen.clear();
                if (ss.getCode()!=20000) {
                    slistBeen.addAll(ss.getData().getList());
                }
                searchListAdapter.notifyDataSetChanged();
                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        //截取id
                        String ip = slistBeen.get(position).getId() + "";
                        Intent intent = new Intent(SearchActivity.this, WatchWebActivity.class);
                        intent.putExtra("id", ip);
                        startActivity(intent);
                    }
                });
            }
        });
    }

    private void click() {
        tv_search_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if ("".equals(et_search.getText().toString())) {
                    //X不显示
                    iv_search_del_his.setVisibility(View.INVISIBLE);
                    slistBeen.clear();
                    searchListAdapter.notifyDataSetChanged();
                    //搜索不显示
                    tv_search.setVisibility(View.INVISIBLE);
                    //取消不隐藏
                    tv_search_del.setVisibility(View.VISIBLE);


                } else {
                    //X显示
                    iv_search_del_his.setVisibility(View.VISIBLE);
                    iv_search_del_his.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            et_search.setText("");
                            relativeLayout.setVisibility(View.VISIBLE);
                            mListView.setVisibility(View.INVISIBLE);
                        }
                    });
                    //搜索显示
                    tv_search.setVisibility(View.VISIBLE);
                    tv_search.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            relativeLayout.setVisibility(View.INVISIBLE);
                            mListView.setVisibility(View.VISIBLE);
                            setupListview();
                        }
                    });
                    //取消隐藏
                    tv_search_del.setVisibility(View.INVISIBLE);

                }
            }
        });

    }

    private void initView() {
        mListView = (ListView) this.findViewById(R.id.search_main_list);
        relativeLayout = (RelativeLayout) this.findViewById(R.id.rl_main);
        tv_search = (TextView) this.findViewById(R.id.tv_search);
        tv_search_del = (TextView) this.findViewById(R.id.tv_search_del);
        tv_clear_all = (TextView) this.findViewById(R.id.tv_clear_all);
        iv_search_del_his = (ImageView) this.findViewById(R.id.iv_search_del_his);
        et_search = (EditText) this.findViewById(R.id.et_search);
    }
}
