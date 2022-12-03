package cn.appoa.afrefresh.presenter;

import java.util.Map;

import cn.appoa.afbase.presenter.BasePresenter;
import cn.appoa.afmvp.AfView;
import cn.appoa.afrefresh.view.IPullToRefreshView;

public abstract class PullToRefreshPresenter extends BasePresenter {

    protected IPullToRefreshView mIPullToRefreshView;

    @Override
    public void onAttach(AfView view) {
        if (view instanceof IPullToRefreshView) {
            mIPullToRefreshView = (IPullToRefreshView) view;
        }
    }

    @Override
    public void onDetach() {
        if (mIPullToRefreshView != null) {
            mIPullToRefreshView = null;
        }
    }

    /**
     * 获取数据
     *
     * @param url
     * @param params
     */
    public abstract void getData(String url, Map<String, String> params);
}
