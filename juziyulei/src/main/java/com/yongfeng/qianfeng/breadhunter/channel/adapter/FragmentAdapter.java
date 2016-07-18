package com.yongfeng.qianfeng.breadhunter.channel.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by my on 2016/6/7.
 */
public class FragmentAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragmentList;
    List<String> mTitleList = new ArrayList<>();
    public FragmentAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> mTitleList  ) {
        super(fm);
        this.fragmentList=fragmentList;
        this.mTitleList=mTitleList;
    }



    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);


    }

    @Override
    public int getCount() {
        return fragmentList == null ? 0 : fragmentList.size();

    }



    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList.get(position);
    }
}
