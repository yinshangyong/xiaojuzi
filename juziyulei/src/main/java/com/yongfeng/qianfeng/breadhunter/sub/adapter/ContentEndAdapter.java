package com.yongfeng.qianfeng.breadhunter.sub.adapter;

import android.content.Context;
import android.net.Uri;
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
import com.yongfeng.qianfeng.breadhunter.sub.bean.RuleContentEnd;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by Administrator on 2016/7/18.
 */
public class ContentEndAdapter extends BaseAdapter {
    private  Context context;
    private List<RuleContentEnd.DataBean.ListBean>list;

    public ContentEndAdapter(Context context, List<RuleContentEnd.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list ==null? 0: list.size();
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
        RuleContentEnd.DataBean.ListBean bean=list.get(position);
        View view = convertView;
        ViewHodler viewHodler = null;
      if (bean.getSrc()!=null&&bean.getCat()!=null&&bean.getPic()!=null){
            view= LayoutInflater.from(context).inflate(R.layout.item_vedio_list,null);
            viewHodler = new ViewHodler(view);
            viewHodler.vedioread.setText(bean.getReadnum()+"");
            viewHodler.vedioname.setText(bean.getAuthor().getName());
            viewHodler.vediotitle.setText(bean.getTitle());
            viewHodler.vedioplay.setUp(bean.getSrc(),"");
            viewHodler.vedioplay.thumbImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Picasso.with(context).load(bean.getPic()).into(viewHodler.vedioplay.thumbImageView);
        }
        if(bean.getSrc()==null&&bean.getPic()!=null&&bean.getCat()!=null){
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

        return view;
    }
    class ViewHodler{
        //视频开始
        TextView vedioread;
        TextView vediotitle;
        TextView vedioname;
        JCVideoPlayerStandard vedioplay;
        //        视频结束
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
        public ViewHodler(View view){
            //视频开始
            vedioread= (TextView) view.findViewById(R.id.item_vedioread_tv);
            vediotitle= (TextView) view.findViewById(R.id.item_item_vediotitle_tv);
            vedioname= (TextView) view.findViewById(R.id.item_vedioname_tv);
            vedioplay= (JCVideoPlayerStandard) view.findViewById(R.id.item_vedio_vp);
//        视频结束
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
        }
    }
}
