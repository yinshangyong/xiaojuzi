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
import com.yongfeng.qianfeng.breadhunter.sub.activity.StarContentActivity;
import com.yongfeng.qianfeng.breadhunter.sub.adapter.RuleContentAdapter;
import com.yongfeng.qianfeng.breadhunter.sub.adapter.RuleStarAdapter;
import com.yongfeng.qianfeng.breadhunter.sub.bean.RuleContent;
import com.yongfeng.qianfeng.breadhunter.sub.bean.RuleStar;
import com.yongfeng.qianfeng.breadhunter.urls.ContentURL;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RuleStarFragment extends Fragment {
   private List<RuleStar.DataBean.ListBean> beanList=new ArrayList<>();

    private GridView gridView;
    private RuleStarAdapter madapter;

    public RuleStarFragment() {
        // Required empty public constructor
    }
    public static RuleStarFragment newInstance() {
        Bundle args = new Bundle();
        
        RuleStarFragment fragment = new RuleStarFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_rule_star, container, false);
         gridView= (GridView) view.findViewById(R.id.fragment_rulestar_gv);
        initAdapter();
        initjsondata();
        initlinner();
        return view;
    }

    private void initlinner() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(), StarWebActivity.class);
                String id2=beanList.get(position).getId();
                intent.putExtra("id",id2);
                startActivity(intent);
            }
        });
    }

    private void initjsondata() {
        OkHttpTask.newInstance(getContext()).enqueue(new IOkTaskCallback() {
            @Override
            public void onSuccess(String result) {
                if (result==null){
                    return;
                }
                Gson gson=new Gson();
                RuleStar content=gson.fromJson(result,RuleStar.class);
                beanList.addAll(content.getData().getList());
                  madapter.notifyDataSetChanged();
            }
        }).start(ContentURL.RULESTAR);
    }

    private void initAdapter() {
         madapter= new RuleStarAdapter(getContext(),R.layout.item_rulestar_gv,beanList);
        gridView.setAdapter(madapter);
    }

}
