package com.yongfeng.qianfeng.breadhunter.channel;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yongfeng.qianfeng.breadhunter.R;
import com.yongfeng.qianfeng.breadhunter.channel.activity.DaiLiActivity;
import com.yongfeng.qianfeng.breadhunter.channel.adapter.FragmentAdapter;
import com.yongfeng.qianfeng.breadhunter.channel.adapter.PopAdapter;
import com.yongfeng.qianfeng.breadhunter.channel.bean.BaGua;
import com.yongfeng.qianfeng.breadhunter.channel.bean.TabLayoutList;
import com.yongfeng.qianfeng.breadhunter.channel.fragment.BaGuaFragment;
import com.yongfeng.qianfeng.breadhunter.channel.fragment.ChennelFragmentTuiJian;
import com.yongfeng.qianfeng.breadhunter.channel.fragment.GifFragment;
import com.yongfeng.qianfeng.breadhunter.channel.fragment.VedioFragment;
import com.yongfeng.qianfeng.breadhunter.tools.HttpUtil;
import com.yongfeng.qianfeng.breadhunter.tools.IRequestCallBack;
import com.yongfeng.qianfeng.breadhunter.urls.ContentURL;

import org.askerov.dynamicgrid.DynamicGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChannelFragment extends Fragment {
    private TabLayout mtablayot;
    private ViewPager mpager;
   private  List<TabLayoutList.DataBean>tablist;
    private List<String> mTitleList = new ArrayList<>();
    private List<Fragment> fragmentList;
    private FragmentAdapter fragmentAdapter;
    private ImageButton todaily;
    private ImageButton shannelmore;
    private DynamicGridView gridView;
    private static final String TAG ="ysyong";
    private PopAdapter madapter;

    public ChannelFragment() {
        // Required empty public constructor
    }

    public static ChannelFragment newInstance() {
        Bundle args = new Bundle();

        ChannelFragment fragment = new ChannelFragment();
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_channel, container, false);

        mtablayot = (TabLayout) view.findViewById(R.id.classfs_tab);
        mpager= (ViewPager) view.findViewById(R.id.classfs_vp);
         shannelmore= (ImageButton) view.findViewById(R.id.fragment_shannelmore_imb);
         todaily= (ImageButton) view.findViewById(R.id.fragment_todaily_imb);
        initjsondata();
        initlinner();

        return view;
    }
         //设置监听
    private void initlinner() {
        //跳转到daily界面
       todaily.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(getContext(),DaiLiActivity.class);
               startActivity(intent);
           }
       });
        shannelmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showPopupWindow(v);
            }
        });

    }

    //解析网络tablyout数据
    private void initjsondata() {
        tablist=new ArrayList<>();
        HttpUtil.requestGet(ContentURL.CHENNELTAB, new IRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                if(result==null){
                    return;
                }
                Gson gson=new Gson();
                String res = result;
                TabLayoutList tabLayoutList=gson.fromJson(res,TabLayoutList.class);
                tablist.addAll(tabLayoutList.getData());
                fragmentList=getFragmentlist(tablist);
                getTab(tablist);
                initAdapter();
            }
        });
    }
//  得到头部名称
    public void getTab( List<TabLayoutList.DataBean> tablist2){
        for(int i=0;i<tablist2.size();i++){
            mTitleList.add(tablist2.get(i).getName());
            mtablayot.addTab(mtablayot.newTab().setText(mTitleList.get(i)));
        }
      //  mtablayot.setTabGravity(TabLayout.MODE_SCROLLABLE);
    }
//    得到fragmentlist
    public List<Fragment> getFragmentlist( List<TabLayoutList.DataBean>tablist2) {
        List<Fragment> list=new ArrayList<>();
        for(int i=0;i<tablist2.size();i++){
            switch (tablist2.get(i).getId()){
                case "0":
                    list.add(ChennelFragmentTuiJian.newInstance());
                    break;
                case "26":
                    list.add(BaGuaFragment.newInstance(tablist2.get(i).getId()));
                    break;
                case "27":
                    list.add(BaGuaFragment.newInstance(tablist2.get(i).getId()));
                    break;
                case "61":
                    list.add(BaGuaFragment.newInstance(tablist2.get(i).getId()));
                    break;
                case "32":
                    list.add(BaGuaFragment.newInstance(tablist2.get(i).getId()));
                    break;
                case "102":
                    list.add(BaGuaFragment.newInstance(tablist2.get(i).getId()));
                    break;
                case "95":
                    list.add(BaGuaFragment.newInstance(tablist2.get(i).getId()));
                    break;
                case "62":
                    list.add(VedioFragment.newInstance());
                    break;
                case "91":
                    list.add(GifFragment.newInstance(tablist2.get(i).getName()));
                    break;
            }
        }



        return list;
    }
//    将viewpager与tablayout绑定
    private void initAdapter( ) {
          fragmentAdapter=new FragmentAdapter(getActivity().getSupportFragmentManager(),fragmentList,mTitleList);
           mpager.setAdapter(fragmentAdapter);
          mtablayot.setupWithViewPager(mpager);
    }


    private void showPopupWindow(View view){
        View contentView = LayoutInflater.from(getContext()).inflate(
                R.layout.pop_tab_more, null);
         gridView= (DynamicGridView) contentView.findViewById(R.id.pop_gv);
        gridView.setChoiceMode(GridView.CHOICE_MODE_SINGLE);
         madapter=new PopAdapter(getContext(),tablist,3);
        gridView.setAdapter(madapter);
        Button button= (Button) contentView.findViewById(R.id.pop_ok);

    //    PopGvAdapter madapter=new PopGvAdapter(getContext(),R.layout.item_pop_gv,tablist);
    //    gridView.setAdapter(madapter);
        final PopupWindow popupWindow = new PopupWindow(contentView,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setContentView(contentView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(popupWindow.isShowing()){
                    popupWindow.dismiss();
                    gridView.stopEditMode();

                }
            }
        });
           initgradviewlis();
     //   popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.searchgod_but));
         popupWindow.showAsDropDown(view);
    }

    private void initgradviewlis() {
        gridView.setOnDragListener(new DynamicGridView.OnDragListener() {
            @Override
            public void onDragStarted(int position) {

            }

            @Override
            public void onDragPositionsChanged(int oldPosition, int newPosition) {

            }
        });
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                gridView.startEditMode(position);
                madapter.notifyDataSetChanged();
                return true;
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), parent.getAdapter().getItem(position).toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }


}
