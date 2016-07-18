package com.yongfeng.qianfeng.breadhunter.channel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.picasso.Picasso;
import com.yongfeng.qianfeng.breadhunter.R;
import com.yongfeng.qianfeng.breadhunter.channel.bean.DaiLi;
import com.yongfeng.qianfeng.breadhunter.channel.bean.TabLayoutList;

import org.askerov.dynamicgrid.BaseDynamicGridAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/7/14.
 */
public class PopAdapter extends BaseDynamicGridAdapter {
    private List<TabLayoutList.DataBean> items;
    private Context context;
    public PopAdapter(Context context, List<TabLayoutList.DataBean> items, int columnCount) {
        super(context, items, columnCount);
        this.context=context;
        this.items=items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHodler viewHodler = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_pop_gv, null);
            viewHodler = new ViewHodler(view);
            view.setTag(viewHodler);
        } else {
            viewHodler = (ViewHodler) view.getTag();
        }
        Picasso.with(context).load(items.get(position).getPic()).into(viewHodler.imageView);
        viewHodler.text.setText(items.get(position).getName());
        return view;
    }
    class ViewHodler{
      TextView text;
        ImageView imageView;
        public ViewHodler(View view){
          text= (TextView) view.findViewById(R.id.item_pop_tv);
            imageView= (ImageView) view.findViewById(R.id.item_pop_iv);
        }
    }
}
