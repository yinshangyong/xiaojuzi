package com.yongfeng.qianfeng.breadhunter.channel.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.sray.httplibrary.IOkTaskCallback;
import com.sray.httplibrary.OkHttpTask;
import com.yongfeng.qianfeng.breadhunter.R;
import com.yongfeng.qianfeng.breadhunter.channel.activity.BaGuaDetailActivity;
import com.yongfeng.qianfeng.breadhunter.channel.adapter.BaGuaAdapter;
import com.yongfeng.qianfeng.breadhunter.channel.bean.BaGua;
import com.yongfeng.qianfeng.breadhunter.channel.bean.BaguaDetail;
import com.yongfeng.qianfeng.breadhunter.channel.bean.TuiJian2;
import com.yongfeng.qianfeng.breadhunter.tools.HttpUtil;
import com.yongfeng.qianfeng.breadhunter.tools.IRequestCallBack;
import com.yongfeng.qianfeng.breadhunter.urls.ContentURL;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaGuaFragment extends Fragment {


    private ListView listView;
    private List<BaGua.DataBean.InfoBean>infolist=new ArrayList<>();
    private List<BaGua.DataBean.ListBean>contentlist=new ArrayList<>();
    private ConvenientBanner convenientBanner;
    private BaGuaAdapter madapeter;
    private String id;
    private SwipeRefreshLayout refreshLayout;
  int num=2;
    public BaGuaFragment() {
        // Required empty public constructor
    }

    public static BaGuaFragment newInstance(String id) {

        Bundle args = new Bundle();
        args.putString("id",id);
        BaGuaFragment fragment = new BaGuaFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Fresco.initialize(getContext());
        View view=inflater.inflate(R.layout.fragment_ba_gua, container, false);
         id=getArguments().getString("id");
         listView= (ListView) view.findViewById(R.id.fragment_bagua_lv);
         refreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.fragment_bagua_fresh);
        View heardview=LayoutInflater.from(getContext()).inflate(R.layout.heard_tuijian_list,null);
        convenientBanner= (ConvenientBanner) heardview.findViewById(R.id.heard_tuijian_cb);
        listView.addHeaderView(heardview);
        initAdapter();
        initjsondata(1);
        return view;
    }
    private void initlisinner() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(),BaGuaDetailActivity.class);
                intent.putExtra("id",contentlist.get(position-1).getId());
                startActivity(intent);
            }
        });
        convenientBanner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent=new Intent(getActivity(),BaGuaDetailActivity.class);
                intent.putExtra("id",infolist.get(position).getId()+"");
                startActivity(intent);
            }
        });
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                infolist.clear();
                contentlist.clear();
              initjsondata(1);
            }
        });
     listView.setOnScrollListener(new AbsListView.OnScrollListener() {
         @Override
         public void onScrollStateChanged(AbsListView view, int scrollState) {
             if(scrollState==AbsListView.OnScrollListener.SCROLL_STATE_IDLE)
             {
                 if (listView.getLastVisiblePosition() == (listView
                         .getCount() - 1)) {
                     initjsondata(num);
                     num+=1;
                 }
             }
         }

         @Override
         public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

         }
     });

    }
    private void initjsondata(final int num) {
        HttpUtil.requestGet(ContentURL.PINDAO1 +id +ContentURL.PINDAO2+num+""+ContentURL.PINDAO3, new IRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                BaGua baGua=gson.fromJson(result,BaGua.class);
                contentlist.addAll(baGua.getData().getList());

                if(num==1){
                    infolist.addAll(baGua.getData().getInfo());
                    inithearddata();
                }
                madapeter.notifyDataSetChanged();

                initlisinner();
                refreshLayout.setRefreshing(false);
            }
        });

    }
    private void initAdapter() {
         madapeter=new BaGuaAdapter(getContext(),R.layout.item_bagua_list,contentlist);
        listView.setAdapter(madapeter);
    }
    private void inithearddata() {
        convenientBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        },infolist)   //设置需要切换的View
                .setPointViewVisible(true)    //设置指示器是否可见
                .setPageIndicator(new int[]{R.drawable.feature_point, R.drawable.feature_point_cur})   //设置指示器圆点
                //      .startTurning(3000)     //设置自动切换（同时设置了切换时间间隔）
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL); //设置指示器位置（左、中、右）
    }
    public class NetworkImageHolderView implements Holder<BaGua.DataBean.InfoBean> {
        //  private ImageView imageView;
        View itemview=null;
        @Override
        public View createView(Context context) {
            //你可以通过layout文件来创建，也可以像我一样用代码创建，不一定是Image，任何控件都可以进行翻页
            //   imageView = new ImageView(context);
            itemview=LayoutInflater.from(context).inflate(R.layout.item_tuijian_banner,null);
            // imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return itemview;
        }
        @Override
        public void UpdateUI(Context context, int position, BaGua.DataBean.InfoBean data) {
            // ImageLoader.loadImage(context,data,imageView);
            ImageView imageView= (ImageView) itemview.findViewById(R.id.item_tuijianbanner_ima);
            TextView textView= (TextView) itemview.findViewById(R.id.item_tuijianbanner_tv);
            Picasso.with(context).load(data.getPic()).into(imageView);
            textView.setText(data.getTitle());
        }
    }


}
