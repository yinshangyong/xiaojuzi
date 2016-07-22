package com.yongfeng.qianfeng.breadhunter;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * Created by 70700 on 2016/7/21.
 */
public class CustomVideoView extends VideoView {
    public CustomVideoView(Context context) {
        super(context,null);
    }

    public CustomVideoView(Context context, AttributeSet attrs) {
        super(context, attrs,0);
    }

    public CustomVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
    //    Log.d("androidxx", "onMeasure: " + width);
        //设置自定义的大小
        setMeasuredDimension(width,height);
    }
}
