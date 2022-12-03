package cn.appoa.afmvp;

/**
 * View回调基类
 */
public interface AfView {

    void showLoading(CharSequence message);

    void dismissLoading();

    void showSoftKeyboard();

    void hideSoftKeyboard();

    String getRequestTag();

    void onErrorCodeResponse(String message);

}
