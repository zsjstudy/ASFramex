package cn.appoa.afhttp.okgo;


import java.util.List;

import cn.appoa.afmvp.AfView;
import cn.appoa.afutils.net.JsonUtils;


/**
 * 网络访问成功的回调
 *
 * @param <T> 后台返回的data里数据
 */
public abstract class OkGoDatasListener<T> extends OkGoSuccessListener {

    /**
     * data里生成的JavaBean
     */
    protected Class<T> clazz;

    public OkGoDatasListener(AfView mView, Class<T> clazz) {
        super(mView);
        this.clazz = clazz;
    }

    public OkGoDatasListener(AfView mView, String tag, Class<T> clazz) {
        super(mView, tag);
        this.clazz = clazz;
    }

    public OkGoDatasListener(AfView mView, String tag, String loadingMessage, Class<T> clazz) {
        super(mView, tag, loadingMessage);
        this.clazz = clazz;
    }

    public OkGoDatasListener(AfView mView, String tag, String loadingMessage, int messageType, Class<T> clazz) {
        super(mView, tag, loadingMessage, messageType);
        this.clazz = clazz;
    }

    public OkGoDatasListener(AfView mView, String tag, String loadingMessage, String errorMessage, Class<T> clazz) {
        super(mView, tag, loadingMessage, errorMessage);
        this.clazz = clazz;
    }

    public OkGoDatasListener(AfView mView, String tag, int messageType, String errorMessage, Class<T> clazz) {
        super(mView, tag, messageType, errorMessage);
        this.clazz = clazz;
    }

    public OkGoDatasListener(AfView mView, String tag, String loadingMessage, int messageType, String errorMessage, Class<T> clazz) {
        super(mView, tag, loadingMessage, messageType, errorMessage);
        this.clazz = clazz;
    }

    @Override
    public void onSuccessResponse(String response) {
        if (clazz != null) {
            onDatasResponse(JsonUtils.parseJson(response, clazz));
        }
    }

    /**
     * 解析的回调
     *
     * @param datas
     */
    public abstract void onDatasResponse(List<T> datas);
}
