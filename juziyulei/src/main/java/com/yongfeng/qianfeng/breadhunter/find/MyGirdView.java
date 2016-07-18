package com.yongfeng.qianfeng.breadhunter.find;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by Administrator on 2016/6/30.
 */
public class MyGirdView extends GridView{
    public MyGirdView(Context context) {
        this(context,null);
    }

    public MyGirdView(Context context, AttributeSet attrs) {
       this(context, attrs,0);
    }

    public MyGirdView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, measureSpec);
    }


}
