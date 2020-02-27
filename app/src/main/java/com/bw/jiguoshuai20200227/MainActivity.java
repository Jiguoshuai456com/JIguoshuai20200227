package com.bw.jiguoshuai20200227;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.stx.xhb.xbanner.XBanner;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private XBanner xb;
    private ListView lv;
    private GridView g;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xb = findViewById(R.id.xb);
        lv = findViewById(R.id.lv);
        g = findViewById(R.id.gv);
        String url="http://mobile.bwstudent.com/small/commodity/v1/bannerShow";
        VolleyNetUtiuls.getInstance().doGet(url, new VolleyNetUtiuls.ICallback() {
            @Override
            public void onSuccess(String url) {
                Log.i("xxx",url);
                Gson gson = new Gson();
                Bean bean = gson.fromJson(url, Bean.class);
                final List<Bean.ResultBean> result = bean.getResult();
                xb.setBannerData(result);
                xb.loadImage(new XBanner.XBannerAdapter() {
                    @Override
                    public void loadBanner(XBanner banner, Object model, View view, int position) {
                        String imageUrl = result.get(position).getImageUrl();
//                        1、	使用Glide完成图片展示，配置占位图、错误图、图片加载优先级，从轮播图最高优先级，热销新品其次，然后魔力时尚，最低的是品质生活
                        Glide.with(MainActivity.this).load(imageUrl).into((ImageView) view);
                    }
                });
            }

            @Override
            public void onError(String url) {

            }
        });
        String path="http://mobile.bwstudent.com/small/commodity/v1/commodityList";
        VolleyNetUtiuls.getInstance().doGet(path, new VolleyNetUtiuls.ICallback() {
            @Override
            public void onSuccess(String url) {
                Gson gson = new Gson();
                BeanList beanList = gson.fromJson(url, BeanList.class);
                BeanList.ResultBean result = beanList.getResult();

//                3、	把Bean对象展示成列表
                BeanList.ResultBean.MlssBean mlss = result.getMlss();
                List<BeanList.ResultBean.MlssBean.CommodityListBeanXX> list = mlss.getCommodityList();
                MyAdapter adapter = new MyAdapter(MainActivity.this, list);
                lv.setAdapter(adapter);

//                3、	把Bean对象展示成列表
                BeanList.ResultBean.PzshBean pzsh = result.getPzsh();
                List<BeanList.ResultBean.PzshBean.CommodityListBeanX> list1 = pzsh.getCommodityList();
                MyAdapter2 adapter1 = new MyAdapter2(MainActivity.this, list1);
                g.setAdapter(adapter1);
            }

            @Override
            public void onError(String url) {

            }
        });
    }
}
