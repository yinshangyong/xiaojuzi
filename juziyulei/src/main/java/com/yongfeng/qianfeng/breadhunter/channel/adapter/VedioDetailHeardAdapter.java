package com.yongfeng.qianfeng.breadhunter.channel.adapter;

import android.content.Context;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yongfeng.qianfeng.breadhunter.R;
import com.yongfeng.qianfeng.breadhunter.channel.bean.VedioDetail;
import com.yongfeng.qianfeng.breadhunter.channel.bean.VedioDetailHeard;
import com.yongfeng.qianfeng.breadhunter.channel.fragment.MyimaView;

import java.util.List;

/**
 * Created by Administrator on 2016/7/20.
 */
public class VedioDetailHeardAdapter extends CommonAdapter<VedioDetailHeard.DataBean.InfoBean> {
     private  Context context;

    public VedioDetailHeardAdapter(Context context, int layoutId, List<VedioDetailHeard.DataBean.InfoBean> list) {
        super(context, layoutId, list);
        this.context=context;
    }

    @Override
    public void convert(ViewHolderM holderM, VedioDetailHeard.DataBean.InfoBean bean) {
    MyimaView myimaView= holderM.getView(R.id.item_baguadetail_ima);
    TextView name= holderM.getView(R.id.item_baguadetailname_tv);
        TextView tatle  =  holderM.getView(R.id.item_baguadetailtatle_tv);
        TextView content  =   holderM.getView(R.id.item_baguadetailcontent_tv);

    }
}
