package com.yongfeng.qianfeng.breadhunter.sub;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sray.httplibrary.IOkTaskCallback;
import com.sray.httplibrary.OkHttpTask;
import com.yongfeng.qianfeng.breadhunter.R;
import com.yongfeng.qianfeng.breadhunter.sub.activity.RuleActivity;
import com.yongfeng.qianfeng.breadhunter.sub.adapter.SubUiConAdapter;
import com.yongfeng.qianfeng.breadhunter.sub.adapter.SubUiHeardAdapter;
import com.yongfeng.qianfeng.breadhunter.sub.bean.SubUi;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubFragment extends Fragment {

   private List<SubUi.DataBean.ListBean>benlist=new ArrayList<>();
    private List<SubUi.DataBean.StarBean>starlist=new ArrayList<>();
    private TextView torule;
    private ListView listView;
    private SubUiConAdapter madapter;
    private GridView heardgridview;
    private SubUiHeardAdapter heardadapter;

    public SubFragment() {
        // Required empty public constructor
    }

    public static SubFragment newInstance() {
        
        Bundle args = new Bundle();
        
        SubFragment fragment = new SubFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_sub, container, false);
        View heardview=inflater.inflate(R.layout.heard_sub_ui,null);
         heardgridview= (GridView) heardview.findViewById(R.id.heard_subui_gv);
         torule= (TextView) view.findViewById(R.id.fragment_subtorule_tv);
         listView= (ListView) view.findViewById(R.id.fragment_sub_lv);
        listView.addHeaderView(heardview);
        initadapter();
        initjsondata();
        initlisinner();
        return view ;
    }

    private void initadapter() {
         madapter=new SubUiConAdapter(getContext(),benlist);
        listView.setAdapter(madapter);
         heardadapter=new SubUiHeardAdapter(getContext(),R.layout.item_heard_subui,starlist);
        heardgridview.setAdapter(heardadapter);

    }

    private void initlisinner() {
        torule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),RuleActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initjsondata() {
        OkHttpTask.newInstance(getContext()).enqueue(new IOkTaskCallback() {
            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                SubUi subUi=gson.fromJson(result,SubUi.class);
                benlist.addAll(subUi.getData().getList());
                starlist.addAll(subUi.getData().getStar());
                madapter.notifyDataSetChanged();
                heardadapter.notifyDataSetChanged();
            }
        }).start("http://api.app.happyjuzi.com/article/list/subscribe?res=1080x1920&accesstoken=434fe833e6332801501b4f0e09af888f&pf=android&uid=3996811031500465&page=1&ts=0&channel=qihoo360&net=wifi&mac=08-00-27-9d-b2-0f&ver=3.0.0");
    }

}
