package cn.appoa.afrefresh.presenter;

import android.text.TextUtils;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.PostRequest;
import com.lzy.okgo.request.base.Request;

import java.util.Map;

import cn.appoa.afutils.net.LogUtils;

/**
 * OKgo列表网络访问
 */
public class PullToRefreshOkGoPresenter extends PullToRefreshPresenter {

    @Override
    public void getData(String url, Map<String, String> params) {
        if (mIPullToRefreshView == null) {
            return;
        }
        PostRequest<String> request = OkGo.<String>post(url);
        for (String key : params.keySet()) {
            request.params(key, params.get(key));
        }
        request.tag(mIPullToRefreshView.getRequestTag());
        request.execute(new StringCallback() {

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                //mIPullToRefreshView.showLoading(AfApplication.appContext.getString(R.string.loading));
            }

            @Override
            public void onFinish() {
                super.onFinish();
                //mIPullToRefreshView.dismissLoading();
            }

            @Override
            public void onCacheSuccess(Response<String> responses) {
                onSuccess(responses);
            }

            @Override
            public void onSuccess(Response<String> responses) {
                if (responses == null) {
                    mIPullToRefreshView.onSuccessResponse("{}");
                    return;
                }
                String response = responses.body();
                if (!TextUtils.isEmpty(response)) {
                    LogUtils.i("onSuccessResponse", response);
                    mIPullToRefreshView.onSuccessResponse(response);
                }
            }

            @Override
            public void onError(Response<String> error) {
                super.onError(error);
                //mIPullToRefreshView.dismissLoading();
                Throwable throwable = error.getException();
                if (throwable != null) {
                    String message = error.toString();
                    if (!TextUtils.isEmpty(throwable.getMessage())) {
                        message = throwable.getMessage();
                        mIPullToRefreshView.onFailedResponse(message);
                    }
                    LogUtils.i("onErrorResponse", message);
                }
            }
        });
    }
}
