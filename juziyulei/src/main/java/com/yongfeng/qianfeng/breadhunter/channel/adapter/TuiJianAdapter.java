package com.yongfeng.qianfeng.breadhunter.channel.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.picasso.Picasso;
import com.yongfeng.qianfeng.breadhunter.R;
import com.yongfeng.qianfeng.breadhunter.channel.bean.TuiJian2;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by Administrator on 2016/7/14.
 */
public class TuiJianAdapter extends BaseAdapter {
    private List<TuiJian2.DataBean.ListBean> itemsBeen;
    private Context context;

    public TuiJianAdapter(List<TuiJian2.DataBean.ListBean> itemsBeen, Context context) {
        this.itemsBeen = itemsBeen;
        this.context = context;
    }


    @Override
    public int getCount() {
        return itemsBeen ==null? 0: itemsBeen.size();
    }
    @Override
    public Object getItem(int position) {
        return position;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHodler viewHodler = null;
        TuiJian2.DataBean.ListBean bean=itemsBeen.get(position);
        //判断是否为gif项
        if(bean.getGif()!=null&&bean.getAuthor()!=null){
            view=LayoutInflater.from(context).inflate(R.layout.item_gif_list,null);
            viewHodler = new ViewHodler(view);
            viewHodler. gifname.setText(bean.getAuthor().getName());
            viewHodler.giftitle.setText(bean.getTitle());
            final Uri uri = Uri.parse(bean.getGif().get(0).getUrl());
            final SimpleDraweeView gifgif2=viewHodler.gifgif;
            viewHodler.gifgif.setImageURI(uri);
           viewHodler. gifplay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DraweeController controller = Fresco.newDraweeControllerBuilder()
                            .setUri(uri)
                            .setAutoPlayAnimations(true)
                            .build();
                    gifgif2.setController(controller);
                }
            });
           }
        //判断是否为视频
       if(bean.getSrc()!=null&&bean.getCat()!=null&&bean.getPic()!=null){
           view= LayoutInflater.from(context).inflate(R.layout.item_vedio_list,null);
           viewHodler = new ViewHodler(view);
           viewHodler.vedioread.setText(bean.getReadnum()+"");
           viewHodler.vedioname.setText(bean.getAuthor().getName());
           viewHodler.vediotitle.setText(bean.getTitle());
           viewHodler.vedioplay.setUp(bean.getSrc(),"");
           viewHodler.vedioplay.thumbImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
           Picasso.with(context).load(bean.getPic()).into(viewHodler.vedioplay.thumbImageView);
       }
        //判断是否为八卦等项
         if(bean.getSrc()==null&&bean.getGif()==null&&bean.getGrade()==null&&bean.getCat()!=null&&bean.getAuthor()!=null){
             view= LayoutInflater.from(context).inflate(R.layout.item_bagua_list,null);
             viewHodler = new ViewHodler(view);
             if(bean.getDisplay()==0){
                viewHodler. baguapklayout.setVisibility(View.GONE);
                 viewHodler. baguablayout.setVisibility(View.VISIBLE);
                 viewHodler. baguatitle.setText(bean.getTitle());
                 viewHodler .baguaname.setText(bean.getCat().getName());
                 viewHodler  .baguaread.setText(bean.getReadnum()+"");
                 Uri uri = Uri.parse(bean.getPic());
                 DraweeController controller = Fresco.newDraweeControllerBuilder()
                         .setUri(uri)
                         .setAutoPlayAnimations(true)
                         .build();
                 viewHodler.baguagif.setController(controller);
             }else {
                 viewHodler .baguapklayout.setVisibility(View.VISIBLE);
                 viewHodler .baguablayout.setVisibility(View.GONE);
                 Uri uri = Uri.parse(bean.getPic());
                 DraweeController controller = Fresco.newDraweeControllerBuilder()
                         .setUri(uri)
                         .setAutoPlayAnimations(true)
                         .build();
                 viewHodler.baguapkgif.setController(controller);
                 viewHodler .baguapktv.setText(bean.getTitle());
                 viewHodler .baguapklayout.setVisibility(View.VISIBLE);
                 viewHodler.baguablayout.setVisibility(View.GONE);
             }
         }
        //判断是否为打分
        if(bean.getSrc()==null&&bean.getGif()==null&&bean.getGrade()!=null){
            view = LayoutInflater.from(context).inflate(R.layout.item_tuijian_lv,null);
            viewHodler = new ViewHodler(view);
            viewHodler.starttext.setText(bean.getTitle());
            Picasso.with(context).load(bean.getGrade().getVoteimg().getSrc()).into(viewHodler.startima);
        }
        return view;
    }
    class ViewHodler{
        //八卦等item开始
        LinearLayout baguablayout;
        TextView baguatitle;
        TextView baguaname;
        TextView baguaread;
        SimpleDraweeView baguagif;
        LinearLayout baguapklayout;
        SimpleDraweeView baguapkgif;
        TextView baguapktv;
//        八卦等item结束
        //gifitem开始
       SimpleDraweeView gifgif;
        TextView gifname;
        TextView giftitle;
        TextView gifplay;
        //gifitem结束
        //视频开始
        TextView vedioread;
        TextView vediotitle;
        TextView vedioname;
        JCVideoPlayerStandard vedioplay;
//        视频结束
        //打分开始
       ImageView startima;
        TextView starttext;
//            打分结束


        public ViewHodler(View view){
            //八卦等item开始
            baguablayout= (LinearLayout) view.findViewById(R.id.item_bagua_lls);
            baguatitle= (TextView) view.findViewById(R.id.item_baguatitle_tv);
             baguaname= (TextView) view.findViewById(R.id.item_baguaname_tv);
             baguaread= (TextView) view.findViewById(R.id.item_baguaread_tv);
             baguagif= (SimpleDraweeView) view.findViewById(R.id.item_baguagif_sdv);
             baguapklayout= (LinearLayout) view.findViewById(R.id.item_baguaanpk_ll);
             baguapkgif= (SimpleDraweeView) view.findViewById(R.id.item_baguapk_ima);
             baguapktv= (TextView) view.findViewById(R.id.item_baguapk_tv);
//        八卦等item结束
            //gifitem开始
          gifgif= (SimpleDraweeView) view.findViewById(R.id.item_gif_sdv);
          gifname= (TextView) view.findViewById(R.id.item_gifname_tv);
          giftitle= (TextView) view.findViewById(R.id.item_giftitle_tv);
           gifplay= (TextView) view.findViewById(R.id.item_gifplay_tv);
            //gifitem结束
            //视频开始
             vedioread= (TextView) view.findViewById(R.id.item_vedioread_tv);
             vediotitle= (TextView) view.findViewById(R.id.item_item_vediotitle_tv);
             vedioname= (TextView) view.findViewById(R.id.item_vedioname_tv);
             vedioplay= (JCVideoPlayerStandard) view.findViewById(R.id.item_vedio_vp);
//        视频结束
//打分开始
            startima= (ImageView) view.findViewById(R.id.item_tuijianpk_ima);
             starttext= (TextView) view.findViewById(R.id.item_tuijianpk_tv);

//            打分结束




        }
    }

}
