package com.yongfeng.qianfeng.breadhunter.find;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yongfeng.qianfeng.breadhunter.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchListFragment extends Fragment {


    public SearchListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_list, container, false);
    }

}
