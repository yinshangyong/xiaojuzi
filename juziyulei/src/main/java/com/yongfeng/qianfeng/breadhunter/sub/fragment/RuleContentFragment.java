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
import com.yongfeng.qianfeng.breadhunter.sub.activity.RuleContentendActivity;
import com.yongfeng.qianfeng.breadhunter.sub.adapter.RuleContentAdapter;
import com.yongfeng.qianfeng.breadhunter.sub.bean.RuleContent;
import com.yongfeng.qianfeng.breadhunter.urls.ContentURL;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RuleContentFragment extends Fragment {
   private List<RuleContent.DataBean.ListBean> beanList=new ArrayList<>();

    private GridView gridView;
    private RuleContentAdapter madapter;

    public RuleContentFragment() {
        // Required empty public constructor
    }

    public static RuleContentFragment newInstance() {
        
        Bundle args = new Bundle();
        
        RuleContentFragment fragment = new RuleContentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_rule_content, container, false);
         gridView= (GridView) view.findViewById(R.id.fragment_rulecontent_gv);
        initAdapter();
        initjsondata();
        return view;
    }

    private void initjsondata() {
        OkHttpTask.newInstance(getContext()).enqueue(new IOkTaskCallback() {
            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                RuleContent content=gson.fromJson(result,RuleContent.class);
                beanList.addAll(content.getData().getList());
                  madapter.notifyDataSetChanged();
                initlinner();
            }
        }).start(ContentURL.RULECONTENT);
    }

    private void initlinner() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(), RuleContentendActivity.class);
                intent.putExtra("id",beanList.get(position).getId());
                 startActivity(intent);
            }
        });
    }

    private void initAdapter() {
         madapter=new RuleContentAdapter(getContext(),R.layout.item_rulecontent_gv,beanList);
        gridView.setAdapter(madapter);
    }

}
