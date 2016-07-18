package com.yongfeng.qianfeng.breadhunter.channel.adapter;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.yongfeng.qianfeng.breadhunter.R;
import com.yongfeng.qianfeng.breadhunter.channel.bean.Gif;

import java.util.List;

/**
 * Created by Administrator on 2016/7/16.
 */
public class GifAdapter extends CommonAdapter<Gif.DataBean.ListBean> {


    public GifAdapter(Context context, int layoutId, List<Gif.DataBean.ListBean> list) {
        super(context, layoutId, list);
    }

    @Override
    public void convert(ViewHolderM holderM, Gif.DataBean.ListBean bean) {
        final SimpleDraweeView gif=holderM.getView(R.id.item_gif_sdv);
        TextView name=holderM.getView(R.id.item_gifname_tv);
        TextView title=holderM.getView(R.id.item_giftitle_tv);
        TextView play=holderM.getView(R.id.item_gifplay_tv);
       name.setText(bean.getAuthor().getName());
        title.setText(bean.getTitle());
        final Uri uri = Uri.parse(bean.getGif().get(0).getUrl());
       gif.setImageURI(uri);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DraweeController controller = Fresco.newDraweeControllerBuilder()
                        .setUri(uri)
                        .setAutoPlayAnimations(true)
                .build();
                gif.setController(controller);
            }
        });





        }
}
