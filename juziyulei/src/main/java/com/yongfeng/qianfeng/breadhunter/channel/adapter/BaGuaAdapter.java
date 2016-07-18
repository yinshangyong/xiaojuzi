package com.yongfeng.qianfeng.breadhunter.channel.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.yongfeng.qianfeng.breadhunter.R;
import com.yongfeng.qianfeng.breadhunter.channel.bean.BaGua;
import java.util.List;
/**
 * Created by Administrator on 2016/7/16.
 */
public class BaGuaAdapter extends CommonAdapter<BaGua.DataBean.ListBean> {
    Context context;
    public BaGuaAdapter(Context context, int layoutId, List<BaGua.DataBean.ListBean> list) {
        super(context, layoutId, list);
        this.context=context;
    }
    @Override
    public void convert(ViewHolderM holderM, BaGua.DataBean.ListBean bean) {
        LinearLayout blayout=holderM.getView(R.id.item_bagua_lls);
        TextView title= holderM.getView(R.id.item_baguatitle_tv);
        TextView name= holderM.getView(R.id.item_baguaname_tv);
        TextView read= holderM.getView(R.id.item_baguaread_tv);
         SimpleDraweeView gif=holderM.getView(R.id.item_baguagif_sdv);
        LinearLayout pklayout=holderM.getView(R.id.item_baguaanpk_ll);
        SimpleDraweeView pkgif=holderM.getView(R.id.item_baguapk_ima);
        TextView pktv=holderM.getView(R.id.item_baguapk_tv);
        if(bean.getDisplay()==0){
            pklayout.setVisibility(View.GONE);
            blayout.setVisibility(View.VISIBLE);
            title.setText(bean.getTitle());
            name.setText(bean.getCat().getName());
            read.setText(bean.getReadnum()+"");
            Uri uri = Uri.parse(bean.getPic());
            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setUri(uri)
                    .setAutoPlayAnimations(true)
                    .build();
            gif.setController(controller);
        }else {
            pklayout.setVisibility(View.VISIBLE);
            blayout.setVisibility(View.GONE);
            Uri uri = Uri.parse(bean.getPic());
            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setUri(uri)
                    .setAutoPlayAnimations(true)
                    .build();
            pkgif.setController(controller);
            pktv.setText(bean.getTitle());
            pklayout.setVisibility(View.VISIBLE);
            blayout.setVisibility(View.GONE);
        }

    }
}
