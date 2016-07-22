package com.yongfeng.qianfeng.breadhunter;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private TextView back;
    private CustomVideoView videoView;
    private Button login_wx,login_qq,login_weibo,login_phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        videoView = (CustomVideoView) findViewById(R.id.login_video);
        back = (TextView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.login_media));
        videoView.start();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
                mp.setLooping(true);
            }
        });
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.login_media));
                        videoView.start();

                    }
                });
        click();
    }

    private void click() {
        login_wx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this,"请安装WEIXIN客户端",Toast.LENGTH_SHORT).show();
            }
        });
        login_qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,LoginWebActivity.class);
                startActivity(intent);
            }
        });
        login_weibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,LoginSINAActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        login_wx = (Button) this.findViewById(R.id.login_wx);
        login_qq = (Button) this.findViewById(R.id.login_qq);
        login_weibo = (Button) this.findViewById(R.id.login_weibo);
        login_phone = (Button) this.findViewById(R.id.login_phone);
    }
}

