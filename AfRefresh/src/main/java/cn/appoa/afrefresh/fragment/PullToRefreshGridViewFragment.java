package cn.appoa.afrefresh.fragment;

import android.widget.GridView;

import cn.appoa.afutils.res.SizeUtils;


/**
 * GridView下拉刷新
 *
 * @param <T> 数据源
 */
public abstract class PullToRefreshGridViewFragment<T> extends PullToRefreshAdapterViewBaseFragment<T, GridView> {

    @Override
    public GridView onCreateRefreshView() {
        return new GridView(getActivity());
    }

    @Override
    public void toScrollTop() {
        if (refreshView != null)
            refreshView.smoothScrollToPositionFromTop(0, 0);
    }

    @Override
    public void setRefreshView() {
        try {
            refreshView.setHorizontalSpacing(SizeUtils.dp2px(getActivity(), initHorizontalSpacing()));
            refreshView.setVerticalSpacing(SizeUtils.dp2px(getActivity(), initVerticalSpacing()));
            int numColumns = initNumColumns();
            if (numColumns > 0) {
                refreshView.setNumColumns(numColumns);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 横向间距
     *
     * @return 间距(单位dp)
     */
    public abstract float initHorizontalSpacing();

    /**
     * 纵向间距
     *
     * @return 间距(单位dp)
     */
    public abstract float initVerticalSpacing();

    /**
     * 列数
     *
     * @return 列数
     */
    public abstract int initNumColumns();
}
