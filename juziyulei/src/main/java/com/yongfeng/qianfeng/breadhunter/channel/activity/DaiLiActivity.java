package com.yongfeng.qianfeng.breadhunter.channel.activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;
import com.yongfeng.qianfeng.breadhunter.R;
import com.yongfeng.qianfeng.breadhunter.channel.CustomListView;
import com.yongfeng.qianfeng.breadhunter.channel.adapter.DaiLiAdapter2;
import com.yongfeng.qianfeng.breadhunter.channel.bean.DaiLi;
import com.yongfeng.qianfeng.breadhunter.tools.HttpUtil;
import com.yongfeng.qianfeng.breadhunter.tools.IRequestCallBack;
import com.yongfeng.qianfeng.breadhunter.urls.ContentURL;
import java.util.ArrayList;
import java.util.List;
public class DaiLiActivity extends AppCompatActivity {
   private List<DaiLi.DataBean.ListBean> contentlist=new ArrayList<>();
    private TextView tabname;
    private CustomListView listView;
    private ImageButton back;
    private DaiLiAdapter2 daiLiAdapter;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_dai_li);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_behavior);
        toolbar.getBackground().setAlpha(0);//toolbar透明度初始化为0
        initView();
        initAdapter();
        initjsondata();
        initlistener();
    }
    private void initlistener() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //初始化适配器以及绑定适配器
    private void initAdapter() {
         daiLiAdapter=new DaiLiAdapter2(contentlist,this);
         listView.setAdapter(daiLiAdapter);
    }
    //加载网络数据
    private void initjsondata() {
        HttpUtil.requestGet(ContentURL.DAILI, new IRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                DaiLi daiLi=gson.fromJson(result,DaiLi.class);
                contentlist.addAll(daiLi.getData().getList());
                daiLiAdapter.notifyDataSetChanged();

            }
        });
    }
   //初始化视图
    private void initView() {
         listView= (CustomListView) findViewById(R.id.activity_daili_list);
         back= (ImageButton) findViewById(R.id.activity_dailiback_imb);
    }
}
