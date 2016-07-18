package com.yongfeng.qianfeng.breadhunter.sub.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yongfeng.qianfeng.breadhunter.R;
import com.yongfeng.qianfeng.breadhunter.channel.adapter.CommonAdapter;
import com.yongfeng.qianfeng.breadhunter.channel.adapter.ViewHolderM;
import com.yongfeng.qianfeng.breadhunter.sub.bean.RuleContent;

import java.util.List;

/**
 * Created by Administrator on 2016/7/18.
 */
public class RuleContentAdapter extends CommonAdapter<RuleContent.DataBean.ListBean> {
    private final Context context;

    public RuleContentAdapter(Context context, int layoutId, List<RuleContent.DataBean.ListBean> list) {
        super(context, layoutId, list);
        this.context=context;
    }

    @Override
    public void convert(ViewHolderM holderM, RuleContent.DataBean.ListBean bean) {
        Picasso.with(context).load(bean.getPic()).into((ImageView) holderM.getView(R.id.item_rulecontent_ima));
      TextView tv= holderM.getView(R.id.item_rulecontent_tv);
        tv.setText(bean.getName());
    }
}
