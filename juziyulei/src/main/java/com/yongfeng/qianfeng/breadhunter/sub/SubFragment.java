package com.yongfeng.qianfeng.breadhunter.sub;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.yongfeng.qianfeng.breadhunter.R;
import com.yongfeng.qianfeng.breadhunter.sub.activity.RuleActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubFragment extends Fragment {


    private TextView torule;
    private ListView listView;

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
         torule= (TextView) view.findViewById(R.id.fragment_subtorule_tv);
         listView= (ListView) view.findViewById(R.id.fragment_sub_lv);
        initjsondata();
        initlisinner();
        return view ;
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

    }

}
