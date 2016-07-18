package com.yongfeng.qianfeng.breadhunter.sub.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yongfeng.qianfeng.breadhunter.R;
import com.yongfeng.qianfeng.breadhunter.channel.adapter.FragmentAdapter;
import com.yongfeng.qianfeng.breadhunter.channel.bean.TabLayoutList;
import com.yongfeng.qianfeng.breadhunter.channel.fragment.ChennelFragmentTuiJian;
import com.yongfeng.qianfeng.breadhunter.sub.bean.RuleStar;
import com.yongfeng.qianfeng.breadhunter.sub.fragment.RuleContentFragment;
import com.yongfeng.qianfeng.breadhunter.sub.fragment.RuleMyListFragment;
import com.yongfeng.qianfeng.breadhunter.sub.fragment.RuleStarFragment;

import java.util.ArrayList;
import java.util.List;

public class RuleActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<String> mTitleList = new ArrayList<>();
    private List<Fragment> fragmentList;
    private FragmentAdapter fragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rule);
        initview();
        getTab();
        fragmentList=getFragmentlist();
        initadapter();
    }

    private void initadapter() {
        fragmentAdapter=new FragmentAdapter(this.getSupportFragmentManager(),fragmentList,mTitleList);
        viewPager.setAdapter(fragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initview() {
         tabLayout= (TabLayout) findViewById(R.id.activity_rule_tab);
         viewPager= (ViewPager) findViewById(R.id.activity_rule_vp);
    }
    public void getTab( ){
            mTitleList.add("推荐内容");
            mTitleList.add("热门明星");
            mTitleList.add("我的订阅");
            tabLayout.addTab(tabLayout.newTab().setText(mTitleList.get(0)));
            tabLayout.addTab(tabLayout.newTab().setText(mTitleList.get(1)));
           tabLayout.addTab(tabLayout.newTab().setText(mTitleList.get(2)));
        //  mtablayot.setTabGravity(TabLayout.MODE_SCROLLABLE);
    }
    public List<Fragment> getFragmentlist() {
        List<Fragment> list=new ArrayList<>();
        list.add(RuleContentFragment.newInstance());
        list.add(RuleStarFragment.newInstance());
        list.add(RuleMyListFragment.newInstance());
        return list;
    }

}
