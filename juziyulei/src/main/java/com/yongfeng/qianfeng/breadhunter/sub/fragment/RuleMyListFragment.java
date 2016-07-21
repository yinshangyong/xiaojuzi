package com.yongfeng.qianfeng.breadhunter.sub.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.gson.Gson;
import com.sray.httplibrary.IOkTaskCallback;
import com.sray.httplibrary.OkHttpTask;
import com.yongfeng.qianfeng.breadhunter.R;
import com.yongfeng.qianfeng.breadhunter.find.StarWebActivity;
import com.yongfeng.qianfeng.breadhunter.sub.activity.RuleContentendActivity;
import com.yongfeng.qianfeng.breadhunter.sub.adapter.RuleContentAdapter;
import com.yongfeng.qianfeng.breadhunter.sub.adapter.RuleMylistContentAdapter;
import com.yongfeng.qianfeng.breadhunter.sub.adapter.RuleMylistStarAdapter;
import com.yongfeng.qianfeng.breadhunter.sub.adapter.RuleStarAdapter;
import com.yongfeng.qianfeng.breadhunter.sub.bean.RuleContent;
import com.yongfeng.qianfeng.breadhunter.sub.bean.RuleMyList;
import com.yongfeng.qianfeng.breadhunter.sub.bean.RuleStar;
import com.yongfeng.qianfeng.breadhunter.urls.ContentURL;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RuleMyListFragment extends Fragment {
   private List<RuleMyList.DataBean.LabelBean>beanList=new ArrayList<>();
   private List<RuleMyList.DataBean.StarBean>startlist=new ArrayList<>();
    private GridView startgridView;
    private RuleMylistContentAdapter madapter;
    private GridView contentgridView;
    private RuleMylistStarAdapter startAdapter;

    public RuleMyListFragment() {
        // Required empty public constructor
    }

    public static RuleMyListFragment newInstance() {
        
        Bundle args = new Bundle();
        
        RuleMyListFragment fragment = new RuleMyListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_rule_mylist, container, false);
         contentgridView= (GridView) view.findViewById(R.id.fragment_rulemylistcontent_gv);
        startgridView= (GridView) view.findViewById(R.id.fragment_rulemyliststar_gv);
        initAdapter();
        initjsondata();
        initlisinner();
        return view;
    }

    private void initlisinner() {
        startgridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(), StarWebActivity.class);
                String id2=startlist.get(position).getId();
                intent.putExtra("id",id2);
                startActivity(intent);
            }
        });
        contentgridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(), RuleContentendActivity.class);
                intent.putExtra("id",beanList.get(position).getId());
                startActivity(intent);
            }
        });

    }

    private void initjsondata() {
        OkHttpTask.newInstance(getContext()).enqueue(new IOkTaskCallback() {
            @Override
            public void onSuccess(String result) {
                if(beanList.size()!=0){
                    return;
                }
                if(result==null){
                    return;
                }
                Gson gson=new Gson();
                RuleMyList content=gson.fromJson(result,RuleMyList.class);
                beanList.addAll(content.getData().getLabel());
                startlist.addAll(content.getData().getStar());
                  madapter.notifyDataSetChanged();
                startAdapter.notifyDataSetChanged();

            }
        }).start(ContentURL.RULEMYLIST);
    }
    private void initAdapter() {
         madapter=new RuleMylistContentAdapter(getContext(),R.layout.item_rulecontent_gv,beanList);
         contentgridView.setAdapter(madapter);
         startAdapter=new RuleMylistStarAdapter(getContext(),R.layout.item_rulestar_gv,startlist);
        startgridView.setAdapter(startAdapter);
    }

}
