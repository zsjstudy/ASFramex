package cn.appoa.afrefresh.view;

import cn.appoa.afbase.view.IBaseView;

public interface IPullToRefreshView extends IBaseView {

    /**
     * 访问成功
     *
     * @param response
     */
    void onSuccessResponse(String response);

    /**
     * 访问失败
     *
     * @param message
     */
    void onFailedResponse(String message);
}
