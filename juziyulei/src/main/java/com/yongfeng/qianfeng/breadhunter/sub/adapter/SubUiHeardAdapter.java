package com.yongfeng.qianfeng.breadhunter.sub.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yongfeng.qianfeng.breadhunter.R;
import com.yongfeng.qianfeng.breadhunter.channel.adapter.CommonAdapter;
import com.yongfeng.qianfeng.breadhunter.channel.adapter.ViewHolderM;
import com.yongfeng.qianfeng.breadhunter.sub.bean.RuleContent;
import com.yongfeng.qianfeng.breadhunter.sub.bean.SubUi;

import java.util.List;

/**
 * Created by Administrator on 2016/7/18.
 */
public class SubUiHeardAdapter extends CommonAdapter<SubUi.DataBean.StarBean> {
    private final Context context;

    public SubUiHeardAdapter(Context context, int layoutId, List<SubUi.DataBean.StarBean> list) {
        super(context, layoutId, list);
        this.context=context;
    }

    @Override
    public void convert(ViewHolderM holderM, SubUi.DataBean.StarBean bean) {
        Picasso.with(context).load(bean.getPic()).into((ImageView) holderM.getView(R.id.heard_subuistar_ima));
      TextView nametv= holderM.getView(R.id.heard_subuistarname_tv);
        TextView numtv= holderM.getView(R.id.heard_subuistarnum_tv);
        TextView qiantv= holderM.getView(R.id.heard_subuistarqian_tv);
        nametv.setText(bean.getName());
        numtv.setText("--"+bean.getPraised_num()+"--");
        if(bean.isIs_praised()){
            qiantv.setBackgroundResource(R.drawable.sub_sign_nor);
        }else {
            qiantv.setBackgroundResource(R.drawable.sub_sign_seleted);
        }
    }
}
