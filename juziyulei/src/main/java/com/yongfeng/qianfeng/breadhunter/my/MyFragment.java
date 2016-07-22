package com.yongfeng.qianfeng.breadhunter.my;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yongfeng.qianfeng.breadhunter.LoginActivity;
import com.yongfeng.qianfeng.breadhunter.R;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends Fragment {
    private Button pifu_style, star_alarm, my_msg, my_fav, task_cent, mall_point;
    private ImageView iv_logtou;
    private TextView friend;
    public MyFragment() {
        // Required empty public constructor
    }

    public static MyFragment newInstance() {
        Bundle args = new Bundle();
        MyFragment fragment = new MyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        initView(view);
        click();

        return view;
    }

    private void click() {
        friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare();
            }
        });
        mall_point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        iv_logtou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        task_cent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        my_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        my_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        star_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "暂未开放，敬请期待", Toast.LENGTH_SHORT).show();
            }
        });
        pifu_style.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "暂未开放，敬请期待", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showShare() {
        ShareSDK.initSDK(getContext());
        OnekeyShare oks = new OnekeyShare();
//关闭sso授权
        oks.disableSSOWhenAuthorize();

// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("标题");
// titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl("http://sharesdk.cn");
// text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
// url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
// comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
// site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
// siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(getContext());
    }

    private void initView(View view) {
        friend = (TextView) view.findViewById(R.id.friend);
        iv_logtou = (ImageView) view.findViewById(R.id.iv_logtou);
        pifu_style = (Button) view.findViewById(R.id.pifu_style);
        star_alarm = (Button) view.findViewById(R.id.star_alarm);
        my_msg = (Button) view.findViewById(R.id.my_msg);
        task_cent = (Button) view.findViewById(R.id.task_cent);
        my_fav = (Button) view.findViewById(R.id.my_fav);
        mall_point = (Button) view.findViewById(R.id.mall_point);
    }


}
