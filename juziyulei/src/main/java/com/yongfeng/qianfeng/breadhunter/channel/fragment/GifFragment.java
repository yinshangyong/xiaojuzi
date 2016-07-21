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
import com.yongfeng.qianfeng.breadhunter.channel.adapter.GifAdapter;
import com.yongfeng.qianfeng.breadhunter.channel.bean.BaGua;
import com.yongfeng.qianfeng.breadhunter.channel.bean.Gif;
import com.yongfeng.qianfeng.breadhunter.tools.HttpUtil;
import com.yongfeng.qianfeng.breadhunter.urls.ContentURL;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GifFragment extends Fragment {

   private List<Gif.DataBean.ListBean>listBeen=new ArrayList<>();
    private List<Gif.DataBean.InfoBean>infolist=new ArrayList<>();
    private ConvenientBanner convenientBanner;
    private ListView listView;
    private GifAdapter madapter;
    private String id;
    int num=2;
    private SwipeRefreshLayout refreshLayout;

    public GifFragment() {
        // Required empty public constructor
    }

    public static GifFragment newInstance(String id) {

        Bundle args = new Bundle();
        args.putString("id",id);
        GifFragment fragment = new GifFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         id=getArguments().getString("id");
        View view=inflater.inflate(R.layout.fragment_gif, container, false);
         listView= (ListView) view.findViewById(R.id.fragment_gif_lv);
         refreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.fragment_gif_fresh);
        View heardview=LayoutInflater.from(getContext()).inflate(R.layout.heard_tuijian_list,null);
        convenientBanner= (ConvenientBanner) heardview.findViewById(R.id.heard_tuijian_cb);
        listView.addHeaderView(heardview);
        initAdapter();
        initjsondata(1);
        initlinsinner();
        return view;
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
                Gif gif=gson.fromJson(result,Gif.class);
                listBeen.addAll(gif.getData().getList());
                if(num==1){
                    infolist.addAll(gif.getData().getInfo());
                    inithearddata();
                }
                madapter.notifyDataSetChanged();
                refreshLayout.setRefreshing(false);
            }
        }).start(ContentURL.GIF+num+""+ContentURL.GIF2);

    }
    private void initAdapter() {
         madapter=new GifAdapter(getContext(),R.layout.item_gif_list,listBeen);
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
    public class NetworkImageHolderView implements Holder<Gif.DataBean.InfoBean> {
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
        public void UpdateUI(Context context, int position, Gif.DataBean.InfoBean data) {
            // ImageLoader.loadImage(context,data,imageView);
            ImageView imageView= (ImageView) itemview.findViewById(R.id.item_tuijianbanner_ima);
            TextView textView= (TextView) itemview.findViewById(R.id.item_tuijianbanner_tv);
            Picasso.with(context).load(data.getPic()).into(imageView);
            textView.setText(data.getTitle());
        }
    }
}
