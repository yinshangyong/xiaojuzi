package com.yongfeng.qianfeng.breadhunter.find.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yongfeng.qianfeng.breadhunter.R;
import com.yongfeng.qianfeng.breadhunter.find.RecyWebActivity;
import com.yongfeng.qianfeng.breadhunter.find.bean.Finds;

import java.util.List;

/**
 * Created by 70700 on 2016/7/14.
 */
public class RecyAdapter  extends RecyclerView.Adapter<MyViewHolder>{
    private Context context;
    private List<Finds.DataBean.ActivityBean> activityBeen;

    public RecyAdapter(Context context, List<Finds.DataBean.ActivityBean> activityBeen) {
        this.context = context;
        this.activityBeen = activityBeen;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_find_recycleview,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Finds.DataBean.ActivityBean bean =activityBeen.get(position);
        holder.tvSub.setText(bean.getSub());
        holder.tvTitle.setText(bean.getTitle());
        Picasso.with(context).load(bean.getPic()).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RecyWebActivity.class);
                String ss =activityBeen.get(position).getUrlroute();
                String id = ss.substring(20,ss.length());
                intent.putExtra("id",id);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return activityBeen==null?0:activityBeen.size();
    }
}

    class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView tvTitle;
        public TextView tvSub;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.reecy_iv);
            tvSub = (TextView) itemView.findViewById(R.id.recy_sub_find);
            tvTitle = (TextView) itemView.findViewById(R.id.recy_title_find);
        }

}
