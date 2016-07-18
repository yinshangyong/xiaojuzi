package com.yongfeng.qianfeng.breadhunter.sub.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.yongfeng.qianfeng.breadhunter.R;
import com.yongfeng.qianfeng.breadhunter.channel.adapter.CommonAdapter;
import com.yongfeng.qianfeng.breadhunter.channel.adapter.ViewHolderM;
import com.yongfeng.qianfeng.breadhunter.sub.bean.RuleMyList;
import com.yongfeng.qianfeng.breadhunter.sub.bean.RuleStar;

import java.util.List;

/**
 * Created by Administrator on 2016/7/18.
 */
public class RuleMylistStarAdapter extends CommonAdapter<RuleMyList.DataBean.StarBean> {
    private final Context context;

    public RuleMylistStarAdapter(Context context, int layoutId, List<RuleMyList.DataBean.StarBean> list) {
        super(context, layoutId, list);
        this.context=context;
    }

    @Override
    public void convert(ViewHolderM holderM, RuleMyList.DataBean.StarBean bean) {
        Picasso.with(context).load(bean.getPic()).into((ImageView) holderM.getView(R.id.item_rulestar_ima));
        TextView tv= holderM.getView(R.id.item_rulestarname_tv);
        tv.setText("--"+bean.getName()+"--");
        TextView etv= holderM.getView(R.id.item_rulestarename_tv);
        etv.setText(bean.getEname());
    }
}
