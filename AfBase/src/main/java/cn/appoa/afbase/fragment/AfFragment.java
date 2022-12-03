package cn.appoa.afbase.fragment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import cn.appoa.afbase.activity.AfActivity;
import cn.appoa.afbase.dialog.DefaultLoadingDialog;
import cn.appoa.afbase.event.AfEvent;
import cn.appoa.afbase.presenter.BasePresenter;
import cn.appoa.afbase.view.IBaseView;
import cn.appoa.afhttp.AfHttp;
import cn.appoa.afpermission.grant.PermissionsManager;
import cn.appoa.afpermission.grant.PermissionsResultAction;

/**
 * Fragment基类，封装了懒加载的实现
 * <p>
 * 1、Viewpager + Fragment情况下，fragment的生命周期因Viewpager的缓存机制而失去了具体意义
 * 该抽象类自定义新的回调方法，当fragment可见状态改变时会触发的回调方法，和 Fragment 第一次可见时会回调的方法
 *
 * @see #onFragmentVisibleChange(boolean)
 * @see #onFragmentFirstVisible()
 */
public abstract class AfFragment<P extends BasePresenter> extends Fragment
        implements IBaseView {

    /**
     * Tag
     */
    protected String REQUEST_TAG;
    /**
     * Fragment的整体view
     */
    protected View rootView;
    /**
     * Fragment是否初次可见
     */
    protected boolean isFirstVisible;
    /**
     * Fragment是否可见
     */
    protected boolean isFragmentVisible;
    /**
     * 是否使用 view的复用
     */
    protected boolean isReuseView;

    /**
     * setUserVisibleHint()在Fragment创建时会先被调用一次，传入isVisibleToUser = false
     * 如果当前Fragment可见，那么setUserVisibleHint()会再次被调用一次，传入isVisibleToUser = true
     * 如果Fragment从可见->不可见，那么setUserVisibleHint()也会被调用，传入isVisibleToUser = false
     * 总结：setUserVisibleHint()除了Fragment的可见状态发生变化时会被回调外，在new Fragment()时也会被回调
     * 如果我们需要在 Fragment 可见与不可见时干点事，用这个的话就会有多余的回调了，那么就需要重新封装一个
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        // setUserVisibleHint()有可能在fragment的生命周期外被调用
        if (rootView == null) {
            return;
        }
        if (isFirstVisible && isVisibleToUser) {
            isFirstVisible = false;
            onFragmentFirstVisible();
        }
        if (isVisibleToUser) {
            isFragmentVisible = true;
            onFragmentVisibleChange(true);
            return;
        }
        if (isFragmentVisible) {
            isFragmentVisible = false;
            onFragmentVisibleChange(false);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        REQUEST_TAG = String.valueOf(System.currentTimeMillis());
        // 注册事件总线
        EventBus.getDefault().register(this);
        initVariable();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //解绑事件总线
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        if (mPresenter != null) {
            mPresenter.onDestroy();
            mPresenter = null;
        }
        //取消全局默认的OKHttpClient中标识为tag的请求
        AfHttp.getInstance().cancelTag(REQUEST_TAG);
        initVariable();
    }

    /**
     * 至少需要一个接收者，事件接收者必须是public
     * 否则报异常：its super classes have no public methods with the @Subscribe annotation
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateEvent(AfEvent event) {

    }

    private void initVariable() {
        isFirstVisible = true;
        isFragmentVisible = false;
        rootView = null;
        isReuseView = true;
    }

    /**
     * Activity
     */
    protected Activity mActivity = null;
    /**
     * Fragment管理
     */
    protected FragmentManager mFragmentManager = null;
    /**
     * 输入法管理
     */
    protected InputMethodManager mInputMethodManager = null;
    /**
     * Presenter
     */
    protected P mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 初始化
        mActivity = getActivity();
        mFragmentManager = getChildFragmentManager();
        mInputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        // 数据传递
        Bundle arguments = getArguments();
        if (arguments != null) {
            initArguments(arguments);
        }
        // 如果setUserVisibleHint()在rootView创建前调用时，那么
        // 就等到rootView创建完后才回调onFragmentVisibleChange(true)
        // 保证onFragmentVisibleChange()的回调发生在rootView创建完成之后，以便支持ui操作
        // 加载布局
        View view = initFragment(inflater, container, savedInstanceState);
        if (view == null) {
            view = super.onCreateView(inflater, container, savedInstanceState);
        }
        bindButterKnife(view);
        initView(view);
        mPresenter = initPresenter();
        if (mPresenter != null) {
            mPresenter.onCreate(savedInstanceState);
            onAttachView();
        }
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mPresenter != null) {
            mPresenter.onSaveInstanceState(outState);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unBindButterKnife();
        if (mPresenter != null) {
            mPresenter.onDetach();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        // 如果setUserVisibleHint()在rootView创建前调用时，那么
        // 就等到rootView创建完后才回调onFragmentVisibleChange(true)
        // 保证onFragmentVisibleChange()的回调发生在rootView创建完成之后，以便支持ui操作
        if (rootView == null) {
            rootView = view;
            if (getUserVisibleHint()) {
                if (isFirstVisible) {
                    onFragmentFirstVisible();
                    isFirstVisible = false;
                }
                onFragmentVisibleChange(true);
                isFragmentVisible = true;
            }
        }
        super.onViewCreated(isReuseView ? rootView : view, savedInstanceState);
    }

    /**
     * 设置是否使用 view 的复用，默认开启 view 的复用是指，ViewPager 在销毁和重建 Fragment 时会不断调用
     * onCreateView() -> onDestroyView() 之间的生命函数，这样可能会出现重复创建 view 的情况，导致界面上显示多个相同的
     * Fragment view 的复用其实就是指保存第一次创建的 view，后面再 onCreateView() 时直接返回第一次创建的 view
     *
     * @param isReuse
     */
    protected void reuseView(boolean isReuse) {
        isReuseView = isReuse;
    }

    /**
     * 去除setUserVisibleHint()多余的回调场景，保证只有当fragment可见状态发生变化时才回调
     * 回调时机在view创建完后，所以支持ui操作，解决在setUserVisibleHint()里进行ui操作有可能报null异常的问题
     * <p>
     * 可在该回调方法里进行一些ui显示与隐藏，比如加载框的显示和隐藏
     *
     * @param isVisible true 不可见 -> 可见 false 可见 -> 不可见
     */
    protected void onFragmentVisibleChange(boolean isVisible) {
        if (isVisible) {
            visibleData();
        } else {
            inVisibleData();
        }
    }

    /**
     * 在fragment首次可见时回调，可在这里进行加载数据，保证只在第一次打开Fragment时才会加载数据， 这样就可以防止每次进入都重复加载数据
     * 该方法会在 onFragmentVisibleChange() 之前调用，所以第一次打开时，可以用一个全局变量表示数据下载状态，
     * 然后在该方法内将状态设置为下载状态，接着去执行下载的任务 最后在 onFragmentVisibleChange()
     * 里根据数据下载状态来控制下载进度ui控件的显示与隐藏
     */
    protected void onFragmentFirstVisible() {
        initData();
    }

    /**
     * Fragment是否可见
     *
     * @return isFragmentVisible
     */
    protected boolean isFragmentVisible() {
        return isFragmentVisible;
    }

    /**
     * 获取Intent传递的数据
     */
    public void initArguments(Bundle arguments) {

    }

    /**
     * 初始化Presenter
     */
    public P initPresenter() {
        return null;
    }

    /**
     * 获取Presenter
     *
     * @return
     */
    public P getPresenter() {
        return mPresenter;
    }

    /**
     * 绑定view
     */
    public void onAttachView() {
        if (mPresenter != null) {
            mPresenter.onAttach(this);
        }
    }

    /**
     * 初始化Fragment
     */
    public abstract View initFragment(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    /**
     * 初始化控件
     */
    public abstract void initView(View view);

    /**
     * 初始化数据
     */
    public abstract void initData();

    /**
     * 刷新数据
     */
    public void notifyData() {

    }

    /**
     * Fragment可见时加载数据
     */
    public void visibleData() {

    }

    /**
     * Fragment不可见时隐藏数据
     */
    public void inVisibleData() {

    }

    /**
     * 显示加载框
     */
    @Override
    public void showLoading(CharSequence message) {
        DefaultLoadingDialog.getInstance().showLoading(getActivity(), message);
    }

    /**
     * 隐藏加载框
     */
    @Override
    public void dismissLoading() {
        DefaultLoadingDialog.getInstance().dismissLoading();
    }

    /**
     * 显示软键盘
     */
    @Override
    public void showSoftKeyboard() {
        if (mInputMethodManager != null) {
            mInputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 隐藏软键盘
     */
    @Override
    public void hideSoftKeyboard() {
        if (mInputMethodManager != null && getActivity().getCurrentFocus() != null) {
            mInputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public String getRequestTag() {
        return REQUEST_TAG;
    }

    @Override
    public void onErrorCodeResponse(String message) {
        try {
            ((AfActivity) getActivity()).onErrorCodeResponse(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @TargetApi(23)
    public void requestPermissions(String[] permissions, PermissionsResultAction action) {
        // 请求获取权限
        if (permissions != null && permissions.length > 0) {
            PermissionsManager.getInstance().requestPermissionsIfNecessaryForResult(this, permissions, action);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        // 因为权限管理类无法监听系统，所以需要重写onRequestPermissionResult方法，更新权限管理类，并回调结果。这个是必须要有的
        PermissionsManager.getInstance().notifyPermissionsChange(permissions, grantResults);
    }

    public void bindButterKnife(View view) {
        // 绑定注解
        // ButterKnife.bind(this, view);
    }

    public void unBindButterKnife() {
        // 解绑注解
        // ButterKnife.unbind(this);
    }
}
