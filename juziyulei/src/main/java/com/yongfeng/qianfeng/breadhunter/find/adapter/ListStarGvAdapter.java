package com.yongfeng.qianfeng.breadhunter.find.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yongfeng.qianfeng.breadhunter.R;
import com.yongfeng.qianfeng.breadhunter.find.bean.Stars;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 70700 on 2016/7/14.
 */
public class ListStarGvAdapter extends BaseAdapter {
    private List<Stars.DataBean.ListBean> starBeen;
    private Context context;

    public ListStarGvAdapter(List<Stars.DataBean.ListBean> starBeen, Context context) {
        this.starBeen = starBeen;
        this.context = context;
    }

    @Override
    public int getCount() {
        return starBeen == null ? 0 : starBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_find_gv, null);
            holder.circleImageView = (CircleImageView) convertView.findViewById(R.id.gv_find_cv);
            holder.tvName = (TextView) convertView.findViewById(R.id.gv_find_name);
            holder.tvScrip = (TextView) convertView.findViewById(R.id.gv_find_scrip);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Stars.DataBean.ListBean starBeans = starBeen.get(position);
        holder.tvName.setText(starBeans.getName());
        holder.tvScrip.setText(starBeans.getEngname());
        Picasso.with(context).load(starBeans.getPortrait()).into(holder.circleImageView);
        return convertView;
    }

    class ViewHolder {
        private CircleImageView circleImageView;
        private TextView tvName;
        private TextView tvScrip;
    }
}
