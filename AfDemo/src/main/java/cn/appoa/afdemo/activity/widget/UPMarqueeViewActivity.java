package cn.appoa.afdemo.activity.widget;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.appoa.afbase.titlebar.BaseTitlebar;
import cn.appoa.afdemo.R;
import cn.appoa.afdemo.base.BaseActivity;
import cn.appoa.afdemo.bean.UPMarqueeBean;
import cn.appoa.afhttp.okgo.AfOkGo;
import cn.appoa.afhttp.okgo.OkGoDatasListener;
import cn.appoa.afui.titlebar.DefaultTitlebar;
import cn.appoa.afui.widget.pager.UPMarqueeView;

/**
 * 仿淘宝头条，向上翻页
 */
public class UPMarqueeViewActivity extends BaseActivity {

    @Override
    public BaseTitlebar initTitlebar() {
        return new DefaultTitlebar.Builder(this)
                .setTitle("UPMarqueeView")
                .setBackImage(R.drawable.back_black)
                .create();
    }

    @Override
    public void initContent(Bundle savedInstanceState) {
        setContent(R.layout.activity_up_marquee_view);
    }

    private UPMarqueeView mUPMarqueeView1;
    private UPMarqueeView mUPMarqueeView2;

    @Override
    public void initView() {
        super.initView();
        mUPMarqueeView1 = (UPMarqueeView) findViewById(R.id.mUPMarqueeView1);
        mUPMarqueeView2 = (UPMarqueeView) findViewById(R.id.mUPMarqueeView2);
    }

    @Override
    public void initData() {
        Map<String, String> params = new HashMap<>();
        AfOkGo.request("", params).tag(this.getRequestTag())
                .execute(new OkGoDatasListener<UPMarqueeBean>
                        (this, "", UPMarqueeBean.class) {
                    @Override
                    public void onDatasResponse(List<UPMarqueeBean> datas) {
                        setData1(datas);
                        setData2(datas);
                    }

                    @Override
                    public void onError(Response<String> error) {
                        super.onError(error);
                        //TODO 模拟数据
                        List<UPMarqueeBean> datas = new ArrayList<>();
                        datas.add(new UPMarqueeBean("就是这样充满烟火气的食物，总是深深虏获了大人小孩的心"));
                        datas.add(new UPMarqueeBean("比起大动干戈的客厅打造，独爱那温馨的卫浴空间"));
                        datas.add(new UPMarqueeBean("扦插花卉不生根？教你3分钟学“高压”"));
                        datas.add(new UPMarqueeBean("夏日降温偏方，北欧风清新卫浴这般造"));
                        datas.add(new UPMarqueeBean("糖酥饼真香警告，真的那么好吃？"));
                        datas.add(new UPMarqueeBean("联邦快递证实一名飞行员在中国被拘 处于保释阶段"));
                        datas.add(new UPMarqueeBean("26岁女中学教师成都闹市地铁站失踪 教育局：正全力寻找"));
                        datas.add(new UPMarqueeBean("iPhone 11首发：未出现以往大排长龙的景象 王府井店首批顾客普遍选了绿色"));
                        datas.add(new UPMarqueeBean("紫药水、红药水、酒精、碘伏……哪种处理伤口好？你可能一直都用错了"));
                        setData1(datas);
                        setData2(datas);
                    }
                });
    }

    /**
     * 设置数据
     *
     * @param datas
     */
    private void setData1(List<UPMarqueeBean> datas) {
        if (datas != null && datas.size() > 0) {
            mUPMarqueeView1.removeAllViews();
            List<View> views = new ArrayList<>();
            for (int i = 0; i < datas.size(); i++) {
                final UPMarqueeBean data = datas.get(i);
                View view = View.inflate(mActivity, R.layout.item_marquee_list1, null);
                TextView tv_title = view.findViewById(R.id.tv_title);
                tv_title.setText(data.Title);
                final int position = i;
                tv_title.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //TODO 跳转详情(position)
                    }
                });
                views.add(view);
            }
            mUPMarqueeView1.setViews(views);
        }

    }

    /**
     * 设置数据
     *
     * @param datas
     */
    private void setData2(List<UPMarqueeBean> datas) {
        mUPMarqueeView2.removeAllViews();
        List<View> views = new ArrayList<>();
        for (int i = 0; i < datas.size(); i = i + 2) {
            View view = View.inflate(mActivity, R.layout.item_marquee_list2, null);
            TextView tv1 = (TextView) view.findViewById(R.id.tv_title1);
            TextView tv2 = (TextView) view.findViewById(R.id.tv_title2);
            tv1.setText(datas.get(i).Title);
            if (datas.size() > i + 1) {
                // 因为是两条数据，但是当数据是奇数时就不需要赋值第二个，所以加了一个判断，把第二个布局给隐藏掉
                tv2.setText(datas.get(i + 1).Title);
                tv2.setVisibility(View.VISIBLE);
            } else {
                tv2.setText(null);
                tv2.setVisibility(View.INVISIBLE);
            }
            final int position = i;
            tv1.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    //TODO 跳转详情(position)
                }
            });
            tv2.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    //TODO 跳转详情(position + 1)
                }
            });
            views.add(view);
        }
        mUPMarqueeView2.setViews(views);
    }
}
