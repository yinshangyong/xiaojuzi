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
import com.yongfeng.qianfeng.breadhunter.R;
import com.yongfeng.qianfeng.breadhunter.channel.activity.BaGuaDetailActivity;
import com.yongfeng.qianfeng.breadhunter.channel.activity.VedioDetailActivity;
import com.yongfeng.qianfeng.breadhunter.channel.adapter.TuiJianAdapter;
import com.yongfeng.qianfeng.breadhunter.channel.bean.TuiJian2;
import com.yongfeng.qianfeng.breadhunter.tools.HttpUtil;
import com.yongfeng.qianfeng.breadhunter.tools.IRequestCallBack;
import com.yongfeng.qianfeng.breadhunter.urls.ContentURL;

import java.util.ArrayList;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChennelFragmentTuiJian extends Fragment {
    private ListView listView;
    private List<TuiJian2.DataBean.ListBean>listBeen=new ArrayList<>();
    private TuiJianAdapter madapter;
    private ConvenientBanner convenientBanner;
    private  List<TuiJian2.DataBean.InfoBean>infolist=new ArrayList<>();
   private SwipeRefreshLayout refreshLayout;
   int num=2;
    public ChennelFragmentTuiJian() {
        // Required empty public constructor
    }
    public static ChennelFragmentTuiJian newInstance( ) {
        
        Bundle args = new Bundle();
        ChennelFragmentTuiJian fragment = new ChennelFragmentTuiJian();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Fresco.initialize(getContext());
        View view=inflater.inflate(R.layout.fragment_chennel_fragment_tui_jian, container, false);
         listView= (ListView) view.findViewById(R.id.fragment_tuijian_lv2);
        refreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.fragment_tuijian_fresh);
        View heardview=LayoutInflater.from(getContext()).inflate(R.layout.heard_tuijian_list,null);
         convenientBanner= (ConvenientBanner) heardview.findViewById(R.id.heard_tuijian_cb);
        listView.addHeaderView(heardview);
          //绑定适配器
        initadapter();
//        初始化数据
        initdata(1);
        initlinsinner();
        return view ;
    }

    private void initlinsinner() {
        convenientBanner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent=new Intent(getActivity(),BaGuaDetailActivity.class);
                intent.putExtra("id",infolist.get(position).getId()+"");
                startActivity(intent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TuiJian2.DataBean.ListBean bean=listBeen.get(position-1);
                if(bean.getSrc()==null&&bean.getGif()==null&&bean.getGrade()==null&&bean.getCat()!=null&&bean.getAuthor()!=null){
                    Intent intent=new Intent(getActivity(),BaGuaDetailActivity.class);
                    intent.putExtra("id",bean.getId()+"");
                    startActivity(intent);
                }
                if(bean.getSrc()!=null&&bean.getCat()!=null&&bean.getPic()!=null){
                    Intent intent=new Intent(getActivity(), VedioDetailActivity.class);
                    intent.putExtra("id",bean.getId()+"");
                    startActivity(intent);
                }
                if(bean.getGif()!=null&&bean.getAuthor()!=null){
                       return;
                }
                if(bean.getSrc()==null&&bean.getGif()==null&&bean.getGrade()!=null){
                    return;
                }
            }
        });

        //刷新
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                infolist.clear();
                listBeen.clear();
                initdata(1);
            }
        });

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if(scrollState==AbsListView.OnScrollListener.SCROLL_STATE_IDLE)
                {
                    if (listView.getLastVisiblePosition() == (listView
                            .getCount() - 1)) {
                        initdata(num);
                        num+=1;
                    }
                }
            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

    }
    private void initdata(final int num) {
        HttpUtil.requestGet(ContentURL.TUIJIAN+num+""+ContentURL.TUIJIAN2, new IRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                TuiJian2 tuiJian=gson.fromJson(result,TuiJian2.class);
                listBeen.addAll(tuiJian.getData().getList());
                if(num==1){
                    infolist.addAll(tuiJian.getData().getInfo());
                    inithearddata();
                }
                madapter.notifyDataSetChanged();
                refreshLayout.setRefreshing(false);
            }
        });
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
    public class NetworkImageHolderView implements Holder<TuiJian2.DataBean.InfoBean> {
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
        public void UpdateUI(Context context, int position, TuiJian2.DataBean.InfoBean data) {
           // ImageLoader.loadImage(context,data,imageView);
            ImageView imageView= (ImageView) itemview.findViewById(R.id.item_tuijianbanner_ima);
            TextView textView= (TextView) itemview.findViewById(R.id.item_tuijianbanner_tv);
            Picasso.with(context).load(data.getPic()).into(imageView);
            textView.setText(data.getTitle());
        }
    }
    private void initadapter() {
         madapter=new TuiJianAdapter(listBeen,getActivity());
        listView.setAdapter(madapter);
    }

    @Override
    public void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
}
