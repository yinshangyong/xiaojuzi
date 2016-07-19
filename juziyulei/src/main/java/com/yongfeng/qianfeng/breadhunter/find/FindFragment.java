package com.yongfeng.qianfeng.breadhunter.find;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.yongfeng.qianfeng.breadhunter.R;
import com.yongfeng.qianfeng.breadhunter.find.adapter.RecyAdapter;
import com.yongfeng.qianfeng.breadhunter.find.adapter.StarGvAdapter;
import com.yongfeng.qianfeng.breadhunter.find.adapter.WatchAdapter;
import com.yongfeng.qianfeng.breadhunter.find.adapter.WatchTwoAdapter;
import com.yongfeng.qianfeng.breadhunter.find.bean.Change;
import com.yongfeng.qianfeng.breadhunter.find.bean.Finds;
import com.yongfeng.qianfeng.breadhunter.find.constast.URLConstast;
import com.yongfeng.qianfeng.breadhunter.tools.http.HttpUtil;
import com.yongfeng.qianfeng.breadhunter.tools.http.IRequestCallBack;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FindFragment extends Fragment {
    private ConvenientBanner mConvenientBanner;
    private GridView mGridView;
    private GridView mWatchGridView;
    private RecyclerView mRecyclerView;
    private StarGvAdapter mStarGvAdapter;
    private TextView mText, pk_title, change_watch, find_more_star,more_hot,tv_rank;
    private ImageView imageView;
    private EditText et_seachmore;
    private WatchAdapter mWatchAdapter;
    private RecyAdapter mRecyAdapter;
    private WatchTwoAdapter wtAdapter;
    private List<Change.DataBean> databean = new ArrayList<>();
    private List<Finds.DataBean.ActivityBean> activitybean = new ArrayList<>();
    private List<Finds.DataBean.WewatchBean> watchbean = new ArrayList<>();
    private List<Finds.DataBean.StarBean> starbean = new ArrayList<>();
    private List<Finds.DataBean.BannerBean> bannerbean = new ArrayList<>();
    private int i;

    public FindFragment() {
        // Required empty public constructor
    }

    public static FindFragment newInstance() {
        Bundle args = new Bundle();
        FindFragment fragment = new FindFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_find, container, false);

        //初始化视图
        initView(view);
        //初始化adapter
        initAdapter();
        //绑定适配器
        baindAdapter();
        //动态加载广告数据
        loadBannerDatas();
        //点击事件
        initClick();
        initClickMore();
        //recyclerview设置布局方向
        setupRecyclerView();
        //       onClick(view);
        return view;
    }

    private void initClickMore() {
        find_more_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten = new Intent(getContext(), MoreStarActivity.class);
                getContext().startActivity(inten);
            }
        });
       //更多热文
        more_hot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten = new Intent(getContext(),MoreHotActivity.class);
                getContext().startActivity(inten);
            }
        });
        tv_rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),StarRankActivity.class);
                startActivity(intent);
            }
        });
        et_seachmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),SearchActivity.class);
                startActivity(intent);
            }
        });
    }


    private void initClick() {
        i = 2;

        change_watch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databean.clear();
                String url = URLConstast.CHANGEWEWATCH1 + i + URLConstast.CHANGEWEWATCH2;
                i++;
                mWatchGridView.setAdapter(wtAdapter);
                HttpUtil.requestGet(url, new IRequestCallBack() {
                    @Override
                    public void onSuccess(String result) {
                        Gson gson = new Gson();
                        Change change = gson.fromJson(result, Change.class);
                        databean.addAll(change.getData());
                        wtAdapter.notifyDataSetChanged();
                        mWatchGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                String ss = databean.get(position).getUrlroute();
                                //截取id
                                String ip = ss.substring(ss.length() - 5, ss.length()) + "";
                                Intent intent = new Intent(getContext(), WatchWebActivity.class);
                                intent.putExtra("id", ip);
                                startActivity(intent);
                            }
                        });


                    }
                });
            }
        });
    }

    private void setupRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }

    private void baindAdapter() {
        mGridView.setAdapter(mStarGvAdapter);
        mWatchGridView.setAdapter(mWatchAdapter);
        mRecyclerView.setAdapter(mRecyAdapter);
    }

    private void initAdapter() {
        mRecyAdapter = new RecyAdapter(getContext(), activitybean);
        mStarGvAdapter = new StarGvAdapter(starbean, getContext());
        mWatchAdapter = new WatchAdapter(getContext(), watchbean);
        wtAdapter = new WatchTwoAdapter(getContext(), databean);
    }

    private void loadBannerDatas() {
        HttpUtil.requestGet(URLConstast.BANNER, new IRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                Finds finds = gson.fromJson(result, Finds.class);
                mText.setText(finds.getData().getInteract().get(0).getTitle());
                pk_title.setText(finds.getData().getInteract().get(1).getTitle());
                Picasso.with(getContext()).load(finds.getData().getInteract().get(1).getVote().getVoteimg().getSrc()).into(imageView);
                //star添加数据
                starbean.addAll(finds.getData().getStar());
                mStarGvAdapter.notifyDataSetChanged();
                mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String ip = starbean.get(position).getId() + "";
                        Intent intent = new Intent(getContext(), StarWebActivity.class);
                        intent.putExtra("id", ip);
                        startActivity(intent);
                    }
                });
                //watch添加数据
                watchbean.addAll(finds.getData().getWewatch());
                mWatchAdapter.notifyDataSetChanged();
                mWatchGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String ss = watchbean.get(position).getUrlroute();
                        String ip = ss.substring(ss.length() - 5, ss.length()) + "";
                        Intent intent = new Intent(getContext(), WatchWebActivity.class);
                        intent.putExtra("id", ip);
                        startActivity(intent);
                    }
                });
                //recy添加数据
                activitybean.addAll(finds.getData().getActivity());
                mRecyAdapter.notifyDataSetChanged();
                //banner添加数据
                bannerbean.addAll(finds.getData().getBanner());
                setupBanner();
                mConvenientBanner.getViewPager().getAdapter().notifyDataSetChanged();
                mConvenientBanner.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        String ss = bannerbean.get(position).getUrlroute();
                        String ip = ss.substring(20, ss.length());
                        Intent intent = new Intent(getContext(), BannerWebActivity.class);
                        intent.putExtra("id", ip);
                        startActivity(intent);
                    }
                });
            }
        });
    }


    private void setupBanner() {
        mConvenientBanner.setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
                .setPages(new CBViewHolderCreator() {
                    @Override
                    public BannerViewHolder createHolder() {
                        return new BannerViewHolder();
                    }
                }, bannerbean)
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
    }

    class BannerViewHolder implements Holder<Finds.DataBean.BannerBean> {
        ImageView imageVIew;

        @Override
        public View createView(Context context) {
            imageVIew = new ImageView(context);
            imageVIew.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return imageVIew;
        }

        @Override
        public void UpdateUI(Context context, int position, Finds.DataBean.BannerBean data) {
            Picasso.with(getActivity()).load(data.getPic()).into(imageVIew);
        }
    }

    private void initView(View view) {
        mText = (TextView) view.findViewById(R.id.title_hd);
        et_seachmore = (EditText) view.findViewById(R.id.et_seachmore);
        tv_rank = (TextView) view.findViewById(R.id.tv_rank);
        find_more_star = (TextView) view.findViewById(R.id.find_more_star);
        pk_title = (TextView) view.findViewById(R.id.pk_title);
        more_hot = (TextView) view.findViewById(R.id.more_hot);
        change_watch = (TextView) view.findViewById(R.id.change_watch);
        imageView = (ImageView) view.findViewById(R.id.pk_iv);
        mConvenientBanner = (ConvenientBanner) view.findViewById(R.id.find_banner);
        mGridView = (GridView) view.findViewById(R.id.find_gv_star);
        mWatchGridView = (GridView) view.findViewById(R.id.watch_gv);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.find_recyview);
    }

    //开始翻页
    @Override
    public void onResume() {
        super.onResume();
        mConvenientBanner.startTurning(3000);
    }

    //停止翻页
    @Override
    public void onPause() {
        super.onPause();
        mConvenientBanner.stopTurning();
    }
}
