package com.yongfeng.qianfeng.breadhunter.channel.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.yongfeng.qianfeng.breadhunter.R;
import com.yongfeng.qianfeng.breadhunter.channel.bean.DaiLi;

import java.util.List;

/**
 * Created by Administrator on 2016/7/14.
 */
public class DaiLiAdapter2 extends BaseAdapter {
    private List<DaiLi.DataBean.ListBean> itemsBeen;
    private Context context;

    public DaiLiAdapter2(List<DaiLi.DataBean.ListBean> itemsBeen, Context context) {
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
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_dailili_list, null);
            viewHodler = new ViewHodler(view);
            view.setTag(viewHodler);
        } else {
            viewHodler = (ViewHodler) view.getTag();
        }
    viewHodler. title.setText(itemsBeen.get(position).getTitle());
        viewHodler.deiliclass.setText(itemsBeen.get(position).getAuthor().getName());
        Uri uri=Uri.parse(itemsBeen.get(position).getPic());
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setAutoPlayAnimations(true)
                .build();
      viewHodler.imageView.setController(controller);
        return view;
    }
    class ViewHodler{
        public LinearLayout linearLayout;
        public TextView title;
        public TextView deiliclass;
        public SimpleDraweeView imageView;
        public TextView description;
        public ViewHodler(View view){
             linearLayout= (LinearLayout) view.findViewById(R.id.item_daili_ll);
             title= (TextView) view.findViewById(R.id.item_dailititle_tv);
             deiliclass= (TextView) view.findViewById(R.id.item_dailiclass_tv);
             imageView= (SimpleDraweeView) view.findViewById(R.id.item_daili_ima);
        }
    }

}
