package cn.appoa.afdemo.fragment;


import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.appoa.afbase.adapter.AfAdapter;
import cn.appoa.afdemo.R;
import cn.appoa.afdemo.adapter.RefreshBeanAdapter;
import cn.appoa.afdemo.bean.RefreshBean;
import cn.appoa.afdemo.net.API;
import cn.appoa.afrefresh.fragment.PullToRefreshGridViewFragment;

public class SmartRefreshGridViewFragment extends PullToRefreshGridViewFragment<RefreshBean> {

    @Override
    public float initHorizontalSpacing() {
        return 4.0f;
    }

    @Override
    public float initVerticalSpacing() {
        return 4.0f;
    }

    @Override
    public int initNumColumns() {
        return 2;
    }

    @Override
    public AfAdapter<RefreshBean> initAdapter() {
        return new RefreshBeanAdapter(getActivity(), dataList, R.layout.item_refresh_bean_grid);
    }

    @Override
    public String setUrl() {
        return API.faq_list;
    }

    @Override
    public Map<String, String> setParams() {
        Map<String, String> params = new HashMap<>();
        params.put("pageindex", pageindex + "");
        params.put("pagesize", 20 + "");
        return params;
    }

    @Override
    public void initData() {
        //super.initData();
        List<RefreshBean> datas = new ArrayList<>();
        if (pageindex < 4) {
            for (int i = 0; i < 20; i++) {
                RefreshBean data = new RefreshBean();
                data.ID = String.valueOf((pageindex - 1) * 20 + (i + 1));
                data.Title = "测试标题" + data.ID;
                datas.add(data);
            }
        }
        onSuccessResponse("{\"msg\":\"\",\"status\":false,\"message\":\"\",\"code\":\"200\",\"data\":" + JSON.toJSONString(datas) + "}");
    }

    @Override
    public List<RefreshBean> filterResponse(String response) {
        if (API.filterJson(response)) {
            return API.parseJson(response, RefreshBean.class);
        }
        return null;
    }

}
