package cn.appoa.afdemo.fragment;

import android.view.View;
import android.widget.ListView;

import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.appoa.afdemo.R;
import cn.appoa.afdemo.adapter.RefreshBeanAdapter;
import cn.appoa.afdemo.bean.RefreshBean;
import cn.appoa.afdemo.net.API;
import cn.appoa.afhttp.okgo.AfOkGo;
import cn.appoa.afhttp.okgo.OkGoDatasListener;
import cn.appoa.afrefresh.fragment.PullToRefreshScrollerLayoutFragment;

public class SmartRefreshScrollLayoutFragment extends PullToRefreshScrollerLayoutFragment {

    @Override
    public int initScrollerLayoutId() {
        return R.layout.fragment_refresh_scroll_layout;
    }

    private ListView lv_refresh;
    private List<RefreshBean> dataList;
    private RefreshBeanAdapter adapter;

    @Override
    public void initScrollerLayoutView(View view) {
        if (view == null)
            return;
        lv_refresh = (ListView) view.findViewById(R.id.lv_refresh);
        dataList = new ArrayList<>();
        adapter = new RefreshBeanAdapter(getActivity(), dataList, R.layout.item_refresh_bean_list);
        lv_refresh.setAdapter(adapter);
    }

    @Override
    public void initData() {
        getBannerData();
        getCategoryData();
        getData1();
        getData2();
        getData3();
        initDataList();
    }

    /**
     * 轮播
     */
    private void getBannerData() {
    }

    /**
     * 分类
     */
    private void getCategoryData() {
    }

    /**
     * 推荐1
     */
    private void getData1() {
    }

    /**
     * 推荐2
     */
    private void getData2() {
    }

    /**
     * 推荐3
     */
    private void getData3() {
    }

    @Override
    public void initDataList() {
        if (pageindex == 1) {
            dataList.clear();
            adapter.setNewData(dataList);
        }
        Map<String, String> params = new HashMap<>();
        params.put("pageindex", pageindex + "");
        params.put("pagesize", 10 + "");
        AfOkGo.request(API.faq_list, params).tag(this.getRequestTag())
                .execute(new OkGoDatasListener<RefreshBean>
                        (this, "问题列表", RefreshBean.class) {
                    @Override
                    public void onDatasResponse(List<RefreshBean> datas) {
                        if (datas != null && datas.size() > 0) {
                            isMore = true;
                            dataList.addAll(datas);
                            adapter.setNewData(dataList);
                        }
                    }

                    @Override
                    public void onSuccess(Response<String> responses) {
                        stopRefresh();
                        stopLoadMore();
                        super.onSuccess(responses);
                    }

                    @Override
                    public void onError(Response<String> error) {
                        stopRefresh();
                        stopLoadMore();
                        super.onError(error);
                        //TODO 模拟数据
                        List<RefreshBean> datas = new ArrayList<>();
                        if (pageindex < 4) {
                            for (int i = 0; i < 20; i++) {
                                RefreshBean data = new RefreshBean();
                                data.ID = String.valueOf((pageindex - 1) * 20 + (i + 1));
                                data.Title = "测试标题" + data.ID;
                                datas.add(data);
                            }
                        }
                        onDatasResponse(datas);
                    }
                });
    }

}
