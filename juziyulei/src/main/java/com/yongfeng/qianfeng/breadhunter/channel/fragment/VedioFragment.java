package com.yongfeng.qianfeng.breadhunter.channel.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.sray.httplibrary.IOkTaskCallback;
import com.sray.httplibrary.OkHttpTask;
import com.yongfeng.qianfeng.breadhunter.R;
import com.yongfeng.qianfeng.breadhunter.channel.activity.BaGuaDetailActivity;
import com.yongfeng.qianfeng.breadhunter.channel.activity.VedioDetailActivity;
import com.yongfeng.qianfeng.breadhunter.channel.adapter.VedioAdapter;
import com.yongfeng.qianfeng.breadhunter.channel.bean.Gif;
import com.yongfeng.qianfeng.breadhunter.channel.bean.Vedio;
import com.yongfeng.qianfeng.breadhunter.urls.ContentURL;

import java.util.ArrayList;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * A simple {@link Fragment} subclass.
 */
public class VedioFragment extends Fragment {

    private List<Vedio.DataBean.ListBean>listBeen=new ArrayList<>();
    private List<Vedio.DataBean.InfoBean>infolist=new ArrayList<>();
    private ListView listView;
    private VedioAdapter madapter;
    private ConvenientBanner convenientBanner;
    private SwipeRefreshLayout refreshLayout;
    int num=2;
    public VedioFragment() {
        // Required empty public constructor
    }
    public static VedioFragment newInstance() {
        Bundle args = new Bundle();
        VedioFragment fragment = new VedioFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_vedio, container, false);
         listView= (ListView) view.findViewById(R.id.item_vedio_lv);
         refreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.fragment_vedio_fresh);
        View heardview=LayoutInflater.from(getContext()).inflate(R.layout.heard_tuijian_list,null);

        convenientBanner= (ConvenientBanner) heardview.findViewById(R.id.heard_tuijian_cb);
        listView.addHeaderView(heardview);
        initAdapter();
        initjsondata(1);

        return view;
    }

    private void initlinsinner() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String id2=listBeen.get(position-1).getId();
                Intent intent=new Intent(getActivity(), VedioDetailActivity.class);
                intent.putExtra("id",id2);
                startActivity(intent);
            }
        });
        convenientBanner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
             Intent intent=new Intent(getActivity(), VedioDetailActivity.class);
                intent.putExtra("id",infolist.get(position).getId());
                startActivity(intent);
            }
        });
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                listBeen.clear();
                infolist.clear();
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
        OkHttpTask.newInstance(getContext()).enqueue(new IOkTaskCallback() {
            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                Vedio vedio=gson.fromJson(result,Vedio.class);
                listBeen.addAll(vedio.getData().getList());
                if(num==1){
                    infolist.addAll(vedio.getData().getInfo());
                    inithearddata();
                }
                madapter.notifyDataSetChanged();
                initlinsinner();
                refreshLayout.setRefreshing(false);
            }
        }).start(ContentURL.VEDIO+num+""+ContentURL.VEDIO2);
    }
    private void initAdapter() {
         madapter=new VedioAdapter(getContext(),R.layout.item_vedio_list,listBeen);
        listView.setAdapter(madapter);
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
    public class NetworkImageHolderView implements Holder<Vedio.DataBean.InfoBean> {
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
        public void UpdateUI(Context context, int position, Vedio.DataBean.InfoBean data) {
            // ImageLoader.loadImage(context,data,imageView);
            ImageView imageView= (ImageView) itemview.findViewById(R.id.item_tuijianbanner_ima);
            TextView textView= (TextView) itemview.findViewById(R.id.item_tuijianbanner_tv);
            Picasso.with(context).load(data.getPic()).into(imageView);
            textView.setText(data.getTitle());
        }
    }
    @Override
    public void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
}
