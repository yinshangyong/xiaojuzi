package com.yongfeng.qianfeng.breadhunter.find.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.yongfeng.qianfeng.breadhunter.R;
import com.yongfeng.qianfeng.breadhunter.find.bean.HotNotic;

import java.util.List;

/**
 * Created by 70700 on 2016/7/18.
 */
public class MoreHotAdapter extends BaseAdapter {
    private Context context;
    private List<HotNotic.DataBean.ListBean> listBeen;

    public MoreHotAdapter(Context context, List<HotNotic.DataBean.ListBean> listBeen) {
        this.context = context;
        this.listBeen = listBeen;
    }

    @Override
    public int getCount() {
        return listBeen == null ? 0 : listBeen.size();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_hot_list, null);
            holder.listtitle = (TextView) convertView.findViewById(R.id.list_title);
            holder.catname = (TextView) convertView.findViewById(R.id.cat_name);
            holder.readnum = (TextView) convertView.findViewById(R.id.readnum);
            holder.simpleDraweeView = (SimpleDraweeView) convertView.findViewById(R.id.list_iv);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        HotNotic.DataBean.ListBean listBeans = listBeen.get(position);
        holder.listtitle.setText(listBeans.getTitle());
        holder.catname.setText(listBeans.getCat().getName());
        holder.readnum.setText(listBeans.getReadnum() + "");
        DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                .setAutoPlayAnimations(true)
                .setUri(listBeans.getPic())
                .build();
        holder.simpleDraweeView.setController(draweeController);
        return convertView;
    }

    class ViewHolder {

        private TextView listtitle;
        //  private CircleImageView circleImageView;
        private TextView catname;
        private TextView readnum;
        private SimpleDraweeView simpleDraweeView;
    }
}
