package com.yongfeng.qianfeng.breadhunter.channel.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yongfeng.qianfeng.breadhunter.R;
import com.yongfeng.qianfeng.breadhunter.channel.bean.BaguaDetail;

import java.util.List;

/**
 * Created by Administrator on 2016/7/20.
 */
public class BaGuaFootAdapter extends CommonAdapter<BaguaDetail.DataBean.FooterBean.RecommendBean> {
    private Context context;
    public BaGuaFootAdapter(Context context, int layoutId, List<BaguaDetail.DataBean.FooterBean.RecommendBean> list) {
        super(context, layoutId, list);
        this.context=context;
    }

    @Override
    public void convert(ViewHolderM holderM, BaguaDetail.DataBean.FooterBean.RecommendBean bean) {
       TextView name= holderM.getView(R.id.foot2_baguaname_tv);
        TextView read= holderM.getView(R.id.foot2_baguaread_tv);
        TextView title= holderM.getView(R.id.foot2_baguatitle_tv);
        ImageView imageView=holderM.getView(R.id.foot2_baguatail_ima);
        if(bean!=null&&bean.getCat()!=null){
           name.setText(bean.getCat().getName());
            read.setText(bean.getReadnum()+"");
            title.setText(bean.getTitle());
            Picasso.with(context).load(bean.getPic()).into(imageView);
        }
    }
}
