package com.yongfeng.qianfeng.breadhunter.find.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yongfeng.qianfeng.breadhunter.R;
import com.yongfeng.qianfeng.breadhunter.find.bean.Finds;

import java.util.List;

/**
 * Created by 70700 on 2016/7/14.
 */
public class WatchAdapter extends BaseAdapter {
    private Context context;
    private List<Finds.DataBean.WewatchBean> wewatchBeen;

    public WatchAdapter(Context context, List<Finds.DataBean.WewatchBean> wewatchBeen) {
        this.context = context;
        this.wewatchBeen = wewatchBeen;
    }

    @Override
    public int getCount() {
        return wewatchBeen ==null?0:wewatchBeen.size();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_find_watchgv, null);
            holder.tvTitle = (TextView) convertView.findViewById(R.id.watch_gv_tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Finds.DataBean.WewatchBean starBeans = wewatchBeen.get(position);

        holder.tvTitle.setText(starBeans.getTitle());

        return convertView;
    }

    class ViewHolder {

        private TextView tvTitle;

    }
}
