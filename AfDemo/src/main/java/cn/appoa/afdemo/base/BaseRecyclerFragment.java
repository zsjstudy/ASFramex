package cn.appoa.afdemo.base;

import cn.appoa.afrefresh.fragment.PullToRefreshRecyclerViewFragment;
import cn.appoa.afutils.file.ThreadUtils;

/**
 * 列表Fragment基类
 *
 * @param <T>
 */
public abstract class BaseRecyclerFragment<T> extends PullToRefreshRecyclerViewFragment<T> {

    /**
     * 是否开启测试（调接口时候改为false）
     */
    protected boolean isTestData = false;

    @Override
    public void initData() {
        if (isTestData || setUrl() == null || setParams() == null) {
            ThreadUtils.runInBack(new Runnable() {

                @Override
                public void run() {
                    try {
                        Thread.sleep(2 * 1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ThreadUtils.runInMain(new Runnable() {

                        @Override
                        public void run() {
                            onSuccessResponse(null);
                        }
                    });
                }
            });
        } else {
            super.initData();
        }
    }

    @Override
    public void showLoading(CharSequence message) {
        // super.showLoading(message);
    }

    @Override
    public void dismissLoading() {
        // super.dismissLoading();
    }

//    // 需要自动加载更多请取消注释以下代码
//    @Override
//    public boolean setRefreshMode() {
//        return false;
//        // return true;
//    }
//
//    @Override
//    public void initRefreshLayout(Bundle savedInstanceState) {
//        // refreshLayout.setEnableAutoLoadMore(true);// 是否启用列表惯性滑动到底部时自动加载更多
//    }
//
//    @Override
//    protected void setAdapter() {
//        if (isHasLoadMore()) {
//            if (adapter != null) {
//                adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
//
//                    @Override
//                    public void onLoadMoreRequested() {
//                        onLoadMore(refreshLayout);
//                    }
//                }, recyclerView);
//            }
//        }
//    }
//
//    /**
//     * 是否自动加载更多
//     */
//    public boolean isHasLoadMore() {
//        return true;
//    }

}
