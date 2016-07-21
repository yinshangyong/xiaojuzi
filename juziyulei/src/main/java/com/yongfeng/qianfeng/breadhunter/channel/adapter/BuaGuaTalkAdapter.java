package com.yongfeng.qianfeng.breadhunter.channel.adapter;

import android.content.Context;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yongfeng.qianfeng.breadhunter.R;
import com.yongfeng.qianfeng.breadhunter.channel.bean.BaGuaDetailTalk;
import com.yongfeng.qianfeng.breadhunter.channel.fragment.MyimaView;

import java.util.List;

/**
 * Created by Administrator on 2016/7/20.
 */
public class BuaGuaTalkAdapter extends CommonAdapter<BaGuaDetailTalk.DataBean.CommentBean.ListBean> {
     private  Context context;

    public BuaGuaTalkAdapter(Context context, int layoutId, List<BaGuaDetailTalk.DataBean.CommentBean.ListBean> list) {
        super(context, layoutId, list);
        this.context=context;
    }

    @Override
    public void convert(ViewHolderM holderM, BaGuaDetailTalk.DataBean.CommentBean.ListBean bean) {
    MyimaView myimaView= holderM.getView(R.id.item_baguadetail_ima);
    TextView name= holderM.getView(R.id.item_baguadetailname_tv);
        TextView tatle  =  holderM.getView(R.id.item_baguadetailtatle_tv);
        TextView content  =   holderM.getView(R.id.item_baguadetailcontent_tv);
        name.setText(bean.getUser().getName());
        tatle.setText(bean.getPublish_time());
        content.setText(bean.getContent());
        Picasso.with(context).load(bean.getUser().getAvatar()).into(myimaView);
    }
}
