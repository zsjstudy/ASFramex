package cn.appoa.afrefresh.fragment;

import android.os.Bundle;
import android.view.View;

import com.donkingliang.consecutivescroller.ConsecutiveScrollerLayout;

/**
 * 下拉刷新的连贯滑动布局
 *
 * @author https://github.com/donkingliang/ConsecutiveScroller
 */
public abstract class PullToRefreshScrollerLayoutFragment
        extends PullToRefreshBaseFragment<ConsecutiveScrollerLayout> {

    @Override
    public void initRefreshLayout(Bundle savedInstanceState) {

    }

    @Override
    public void initRefreshView() {

    }

    @Override
    public ConsecutiveScrollerLayout onCreateRefreshView() {
        View view = View.inflate(getActivity(), initScrollerLayoutId(), null);
        initScrollerLayoutView(view);
        return (ConsecutiveScrollerLayout) view;
    }

    @Override
    public boolean setRefreshMode() {
        return true;
    }

    @Override
    public void onRefresh() {
        initData();
    }

    @Override
    public void onLoadMore() {
        initDataList();
    }

    @Override
    public void toScrollTop() {
        if (refreshView != null) {
            View view = refreshView.getChildAt(0);
            if (view != null && !refreshView.isScrollTop()) {
                refreshView.scrollToChild(view);
            }
        }
    }

    /**
     * 初始化布局id
     *
     * @return 布局id（跟布局必须是ConsecutiveScrollerLayout）
     */
    public abstract int initScrollerLayoutId();

    /**
     * 初始化控件
     *
     * @param view
     */
    public abstract void initScrollerLayoutView(View view);

    /**
     * 初始化列表数据
     */
    public abstract void initDataList();
}
