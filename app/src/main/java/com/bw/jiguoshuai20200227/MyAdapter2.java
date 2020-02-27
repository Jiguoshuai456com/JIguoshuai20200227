package com.bw.jiguoshuai20200227;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyAdapter2 extends BaseAdapter {
    Context context;
    List<BeanList.ResultBean.PzshBean.CommodityListBeanX> list;
    public MyAdapter2(Context context, List<BeanList.ResultBean.PzshBean.CommodityListBeanX> list) {
        this.context=context;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
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
        View view = View.inflate(context, R.layout.item, null);
        TextView t1 = view.findViewById(R.id.t1);
        TextView t2 = view.findViewById(R.id.t2);
        ImageView iv = view.findViewById(R.id.iv);
        BeanList.ResultBean.PzshBean.CommodityListBeanX xx = list.get(position);
        String name = xx.getCommodityName();
        t1.setText(name);
        int price = xx.getPrice();
        t2.setText("￥"+price);
        String pic = xx.getMasterPic();
//        1、	使用Glide完成图片展示，配置占位图、错误图、图片加载优先级，从轮播图最高优先级，热销新品其次，然后魔力时尚，最低的是品质生活
        Glide.with(context).load(pic).into(iv);
        return view;
    }
}
