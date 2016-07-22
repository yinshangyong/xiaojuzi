package com.yongfeng.qianfeng.breadhunter;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.umeng.analytics.MobclickAgent;
import com.umeng.message.PushAgent;
import com.yongfeng.qianfeng.breadhunter.channel.ChannelFragment;
import com.yongfeng.qianfeng.breadhunter.find.FindFragment;
import com.yongfeng.qianfeng.breadhunter.my.MyFragment;
import com.yongfeng.qianfeng.breadhunter.sub.SubFragment;

import cn.sharesdk.framework.ShareSDK;

public class MainActivity extends AppCompatActivity {
    private RadioGroup rgButton;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private ChannelFragment channelFragment;
    private MyFragment myFragment;
    private FindFragment findFragment;
    private SubFragment subFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ShareSDK.initSDK(this,"15394f94a3f08");
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);
        PushAgent mPushAgent = PushAgent.getInstance(this);
        mPushAgent.enable();
        PushAgent.getInstance(this).onAppStart();

        initView();
        RadioButton childAt = (RadioButton) rgButton.getChildAt(0);
        childAt.setChecked(true);
        manager = getSupportFragmentManager();
        rgButton.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                transaction = manager.beginTransaction();
                switch (checkedId) {
                    case R.id.rb_find:
                        if (findFragment == null) {
                            findFragment = findFragment.newInstance();
                            transaction.add(R.id.fr_replace, findFragment, "rec");
                            if (channelFragment != null) {
                                transaction.hide(channelFragment);
                            }
                            if (subFragment != null) {
                                transaction.hide(subFragment);
                            }
                            if (myFragment != null) {
                                transaction.hide(myFragment);
                            }
                        } else {
                            transaction.show(findFragment);
                            if (subFragment != null) {
                                transaction.hide(subFragment);
                            }
                            if (myFragment != null) {
                                transaction.hide(myFragment);
                            }
                            if (channelFragment != null) {
                                transaction.hide(channelFragment);
                            }
                        }
                        break;
                    case R.id.rb_channel:
                        if (channelFragment == null) {
                            channelFragment = channelFragment.newInstance();
                            transaction.add(R.id.fr_replace, channelFragment, "rec");
                            if (findFragment != null) {
                                transaction.hide(findFragment);
                            }
                            if (subFragment != null) {
                                transaction.hide(subFragment);
                            }
                            if (myFragment != null) {
                                transaction.hide(myFragment);
                            }
                        } else {
                            transaction.show(channelFragment);
                            if (subFragment != null) {
                                transaction.hide(subFragment);
                            }
                            if (myFragment != null) {
                                transaction.hide(myFragment);
                            }
                            if (findFragment != null) {
                                transaction.hide(findFragment);
                            }
                        }
                        break;
                    case R.id.rb_sub:
                        if (subFragment == null) {
                            subFragment = subFragment.newInstance();
                            transaction.add(R.id.fr_replace, subFragment, "rec");
                            if (findFragment != null) {
                                transaction.hide(findFragment);
                            }
                            if (channelFragment != null) {
                                transaction.hide(channelFragment);
                            }
                            if (myFragment != null) {
                                transaction.hide(myFragment);
                            }
                        } else {
                            transaction.show(subFragment);
                            if (channelFragment != null) {
                                transaction.hide(channelFragment);
                            }
                            if (myFragment != null) {
                                transaction.hide(myFragment);
                            }
                            if (findFragment != null) {
                                transaction.hide(findFragment);
                            }
                        }
                        break;
                    case R.id.rb_my:
                        if (myFragment == null) {
                            myFragment = myFragment.newInstance();
                            transaction.add(R.id.fr_replace, myFragment, "rec");
                            if (findFragment != null) {
                                transaction.hide(findFragment);
                            }
                            if (channelFragment != null) {
                                transaction.hide(channelFragment);
                            }
                            if (subFragment != null) {
                                transaction.hide(subFragment);
                            }
                        } else {
                            transaction.show(myFragment);
                            if (channelFragment != null) {
                                transaction.hide(channelFragment);
                            }
                            if (subFragment != null) {
                                transaction.hide(subFragment);
                            }
                            if (findFragment != null) {
                                transaction.hide(findFragment);
                            }
                        }
                        break;
                }
                transaction.commit();
            }
        });
    }

    private void initView() {
        rgButton = (RadioGroup) this.findViewById(R.id.home_rg);
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        channelFragment = channelFragment.newInstance();
        transaction.add(R.id.fr_replace, channelFragment, "rec");
        transaction.commit();
    }
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
