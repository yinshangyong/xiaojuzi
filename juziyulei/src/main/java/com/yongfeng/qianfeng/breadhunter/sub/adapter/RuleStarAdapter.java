package com.yongfeng.qianfeng.breadhunter.sub.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yongfeng.qianfeng.breadhunter.R;
import com.yongfeng.qianfeng.breadhunter.channel.adapter.CommonAdapter;
import com.yongfeng.qianfeng.breadhunter.channel.adapter.ViewHolderM;
import com.yongfeng.qianfeng.breadhunter.sub.bean.RuleContent;
import com.yongfeng.qianfeng.breadhunter.sub.bean.RuleStar;

import java.util.List;

/**
 * Created by Administrator on 2016/7/18.
 */
public class RuleStarAdapter extends CommonAdapter<RuleStar.DataBean.ListBean> {
    private final Context context;

    public RuleStarAdapter(Context context, int layoutId, List<RuleStar.DataBean.ListBean> list) {
        super(context, layoutId, list);
        this.context=context;
    }

    @Override
    public void convert(ViewHolderM holderM, RuleStar.DataBean.ListBean bean) {
        Picasso.with(context).load(bean.getPic()).into((ImageView) holderM.getView(R.id.item_rulestar_ima));
      TextView tv= holderM.getView(R.id.item_rulestarname_tv);
        tv.setText("--"+bean.getName()+"--");
        TextView etv= holderM.getView(R.id.item_rulestarename_tv);
        etv.setText(bean.getEname());

    }
}
