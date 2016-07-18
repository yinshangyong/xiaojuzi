package com.yongfeng.qianfeng.breadhunter.sub.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yongfeng.qianfeng.breadhunter.R;
import com.yongfeng.qianfeng.breadhunter.channel.adapter.CommonAdapter;
import com.yongfeng.qianfeng.breadhunter.channel.adapter.ViewHolderM;
import com.yongfeng.qianfeng.breadhunter.sub.bean.RuleContent;
import com.yongfeng.qianfeng.breadhunter.sub.bean.RuleMyList;

import java.util.List;

/**
 * Created by Administrator on 2016/7/18.
 */
public class RuleMylistContentAdapter extends CommonAdapter<RuleMyList.DataBean.LabelBean> {
    private final Context context;

    public RuleMylistContentAdapter(Context context, int layoutId, List<RuleMyList.DataBean.LabelBean> list) {
        super(context, layoutId, list);
        this.context=context;
    }

    @Override
    public void convert(ViewHolderM holderM,RuleMyList.DataBean.LabelBean bean) {
        Picasso.with(context).load(bean.getPic()).into((ImageView) holderM.getView(R.id.item_rulecontent_ima));
      TextView tv= holderM.getView(R.id.item_rulecontent_tv);
        tv.setText(bean.getName());
    }
}
