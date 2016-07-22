package com.yongfeng.qianfeng.breadhunter.find.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yongfeng.qianfeng.breadhunter.R;
import com.yongfeng.qianfeng.breadhunter.dao.Customer;

import java.util.List;

/**
 * Created by 70700 on 2016/7/20.
 */
public class SearchHistroyAdapter extends BaseAdapter {
    private Context context;
    private List<Customer> list;

    public SearchHistroyAdapter(Context context, List<Customer> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list ==null?0:list.size();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_searchhistory, null);
            holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_tv_search);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Customer starBeans = list.get(position);

        holder.tvTitle.setText(starBeans.getCustomerName());

        return convertView;
    }

    class ViewHolder {

        private TextView tvTitle;

    }
}
