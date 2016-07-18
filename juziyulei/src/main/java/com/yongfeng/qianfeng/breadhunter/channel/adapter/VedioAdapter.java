package com.yongfeng.qianfeng.breadhunter.channel.adapter;

import android.content.Context;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.squareup.picasso.Picasso;
import com.yongfeng.qianfeng.breadhunter.R;
import com.yongfeng.qianfeng.breadhunter.channel.bean.Vedio;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by Administrator on 2016/7/16.
 */
public class VedioAdapter extends CommonAdapter<Vedio.DataBean.ListBean> {
    Context context;
    public VedioAdapter(Context context, int layoutId, List<Vedio.DataBean.ListBean> list) {
        super(context, layoutId, list);
        this.context=context;
    }

    @Override
    public void convert(ViewHolderM holderM, Vedio.DataBean.ListBean bean) {
      TextView read= holderM.getView(R.id.item_vedioread_tv);
        TextView title= holderM.getView(R.id.item_item_vediotitle_tv);
        TextView name= holderM.getView(R.id.item_vedioname_tv);
        JCVideoPlayerStandard play=holderM.getView(R.id.item_vedio_vp);
        read.setText(bean.getReadnum());
        name.setText(bean.getAuthor().getName());
        title.setText(bean.getTitle());
        play.setUp(bean.getSrc(),"");
        Uri uri = Uri.parse(bean.getSrc());
       // play.thumbImageView.setImageURI(uri);
        play.thumbImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        if(bean.getPic()!=null){
            Picasso.with(context).load(bean.getPic()).into(play.thumbImageView);
        }
    // play.thumbImageView.setBackgroundResource(R.mipmap.ic_launcher);

    }
}
