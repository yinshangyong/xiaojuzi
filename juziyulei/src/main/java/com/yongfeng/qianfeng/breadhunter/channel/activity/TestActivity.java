package com.yongfeng.qianfeng.breadhunter.channel.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.yongfeng.qianfeng.breadhunter.R;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_behavior);
        toolbar.getBackground().setAlpha(0);//toolbar透明度初始化为0
    }
}
