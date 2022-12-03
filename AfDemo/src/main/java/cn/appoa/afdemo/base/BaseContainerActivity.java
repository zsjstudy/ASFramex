package cn.appoa.afdemo.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.fragment.app.Fragment;
import cn.appoa.afbase.titlebar.BaseTitlebar;
import cn.appoa.afdemo.R;
import cn.appoa.afui.titlebar.DefaultTitlebar;

/**
 * 盛装Fragment的一个容器(代理)Activity
 * 普通界面只需要编写Fragment,使用此Activity盛装,这样就不需要每个界面都在AndroidManifest中注册一遍
 */
public class BaseContainerActivity extends BaseActivity {

    /**
     * Activity跳转容器页面
     *
     * @param canonicalName 规范名 : XXXFragment.class.getCanonicalName()
     */
    public static void startContainerActivity(Activity mActivity, String canonicalName) {
        startContainerActivity(mActivity, canonicalName, null, null);
    }

    /**
     * Activity跳转容器页面
     *
     * @param canonicalName 规范名 : XXXFragment.class.getCanonicalName()
     */
    public static void startContainerActivity(Activity mActivity, String canonicalName, String title) {
        startContainerActivity(mActivity, canonicalName, title, null);
    }

    /**
     * Activity跳转容器页面
     *
     * @param canonicalName 规范名 : XXXFragment.class.getCanonicalName()
     */
    public static void startContainerActivity(Activity mActivity, String canonicalName, Bundle bundle) {
        startContainerActivity(mActivity, canonicalName, null, bundle);
    }

    /**
     * Activity跳转容器页面
     *
     * @param canonicalName 规范名 : XXXFragment.class.getCanonicalName()
     * @param title         页面标题
     * @param bundle        跳转所携带的信息
     */
    public static void startContainerActivity(Activity mActivity, String canonicalName, String title, Bundle bundle) {
        if (mActivity == null || TextUtils.isEmpty(canonicalName)) {
            return;
        }
        mActivity.startActivity(getContainerActivityIntent(mActivity, canonicalName, title, bundle));
    }

    /**
     * Fragment跳转容器页面
     *
     * @param canonicalName 规范名 : XXXFragment.class.getCanonicalName()
     */
    public static void startContainerActivity(Fragment mFragment, String canonicalName) {
        startContainerActivity(mFragment, canonicalName, null, null);
    }

    /**
     * Fragment跳转容器页面
     *
     * @param canonicalName 规范名 : XXXFragment.class.getCanonicalName()
     */
    public static void startContainerActivity(Fragment mFragment, String canonicalName, String title) {
        startContainerActivity(mFragment, canonicalName, title, null);
    }

    /**
     * Fragment跳转容器页面
     *
     * @param canonicalName 规范名 : XXXFragment.class.getCanonicalName()
     */
    public static void startContainerActivity(Fragment mFragment, String canonicalName, Bundle bundle) {
        startContainerActivity(mFragment, canonicalName, null, bundle);
    }

    /**
     * Fragment跳转容器页面
     *
     * @param canonicalName 规范名 : XXXFragment.class.getCanonicalName()
     * @param title         页面标题
     * @param bundle        跳转所携带的信息
     */
    public static void startContainerActivity(Fragment mFragment, String canonicalName, String title, Bundle bundle) {
        if (mFragment == null || TextUtils.isEmpty(canonicalName)) {
            return;
        }
        mFragment.startActivity(getContainerActivityIntent(mFragment.getActivity(), canonicalName, title, bundle));
    }

    /**
     * Activity跳转容器页面
     *
     * @param canonicalName 规范名 : XXXFragment.class.getCanonicalName()
     */
    public static void startContainerActivityForResult(Activity mActivity, String canonicalName, int requestCode) {
        startContainerActivityForResult(mActivity, canonicalName, null, null, requestCode);
    }

    /**
     * Activity跳转容器页面
     *
     * @param canonicalName 规范名 : XXXFragment.class.getCanonicalName()
     */
    public static void startContainerActivityForResult(Activity mActivity, String canonicalName, String title, int requestCode) {
        startContainerActivityForResult(mActivity, canonicalName, title, null, requestCode);
    }

    /**
     * Activity跳转容器页面
     *
     * @param canonicalName 规范名 : XXXFragment.class.getCanonicalName()
     */
    public static void startContainerActivityForResult(Activity mActivity, String canonicalName, Bundle bundle, int requestCode) {
        startContainerActivityForResult(mActivity, canonicalName, null, bundle, requestCode);
    }

    /**
     * Activity跳转容器页面
     *
     * @param canonicalName 规范名 : XXXFragment.class.getCanonicalName()
     * @param title         页面标题
     * @param bundle        跳转所携带的信息
     */
    public static void startContainerActivityForResult(Activity mActivity, String canonicalName, String title, Bundle bundle, int requestCode) {
        if (mActivity == null || TextUtils.isEmpty(canonicalName)) {
            return;
        }
        mActivity.startActivityForResult(getContainerActivityIntent(mActivity, canonicalName, title, bundle), requestCode);
    }

    /**
     * Fragment跳转容器页面
     *
     * @param canonicalName 规范名 : XXXFragment.class.getCanonicalName()
     */
    public static void startContainerActivityForResult(Fragment mFragment, String canonicalName, int requestCode) {
        startContainerActivityForResult(mFragment, canonicalName, null, null, requestCode);
    }

    /**
     * Fragment跳转容器页面
     *
     * @param canonicalName 规范名 : XXXFragment.class.getCanonicalName()
     */
    public static void startContainerActivityForResult(Fragment mFragment, String canonicalName, String title, int requestCode) {
        startContainerActivityForResult(mFragment, canonicalName, title, null, requestCode);
    }

    /**
     * Fragment跳转容器页面
     *
     * @param canonicalName 规范名 : XXXFragment.class.getCanonicalName()
     */
    public static void startContainerActivityForResult(Fragment mFragment, String canonicalName, Bundle bundle, int requestCode) {
        startContainerActivityForResult(mFragment, canonicalName, null, bundle, requestCode);
    }

    /**
     * Fragment跳转容器页面
     *
     * @param canonicalName 规范名 : XXXFragment.class.getCanonicalName()
     * @param title         页面标题
     * @param bundle        跳转所携带的信息
     */
    public static void startContainerActivityForResult(Fragment mFragment, String canonicalName, String title, Bundle bundle, int requestCode) {
        if (mFragment == null || TextUtils.isEmpty(canonicalName)) {
            return;
        }
        mFragment.startActivityForResult(getContainerActivityIntent(mFragment.getContext()
                , canonicalName, title, bundle), requestCode);
    }

    /**
     * 获取跳转容器页面的Intent
     *
     * @param context
     * @param canonicalName
     * @param title
     * @param bundle
     * @return
     */
    public static Intent getContainerActivityIntent(Context context, String canonicalName, String title, Bundle bundle) {
        Intent intent = new Intent(context, BaseContainerActivity.class);
        intent.putExtra(BaseContainerActivity.FRAGMENT_NAME, canonicalName);
        if (title != null) {
            intent.putExtra(BaseContainerActivity.FRAGMENT_TITLE, title);
        }
        if (bundle != null) {
            intent.putExtra(BaseContainerActivity.FRAGMENT_BUNDLE, bundle);
        }
        return intent;
    }

    public static final String FRAGMENT_TAG = "fragment_tag";
    public static final String FRAGMENT_NAME = "fragment_name";
    public static final String FRAGMENT_TITLE = "fragment_title";
    public static final String FRAGMENT_BUNDLE = "fragment_bundle";

    private String fragmentName;
    private String fragmentTitle;
    private Bundle fragmentBundle;

    @Override
    public void initIntent(Intent intent) {
        fragmentName = intent.getStringExtra(BaseContainerActivity.FRAGMENT_NAME);
        if (TextUtils.isEmpty(fragmentName)) {
            finish();
        }
        fragmentTitle = intent.getStringExtra(BaseContainerActivity.FRAGMENT_TITLE);
        fragmentBundle = intent.getBundleExtra(BaseContainerActivity.FRAGMENT_BUNDLE);
    }

    @Override
    public BaseTitlebar initTitlebar() {
        if (TextUtils.isEmpty(fragmentTitle)) {
            return super.initTitlebar();
        } else {
            DefaultTitlebar.Builder builder = new DefaultTitlebar.Builder(mActivity);
            builder.setBackImage(R.drawable.back_black);
            builder.setTitle(fragmentTitle);
            return builder.create();
        }
    }

    private Fragment fragment = null;

    @Override
    public void initContent(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            fragment = mFragmentManager.getFragment(savedInstanceState, BaseContainerActivity.FRAGMENT_TAG);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (fragment != null) {
            mFragmentManager.putFragment(outState, BaseContainerActivity.FRAGMENT_TAG, fragment);
        }
    }

    @Override
    public void initData() {
        if (fragment == null) {
            try {
                Class<?> fragmentClass = Class.forName(fragmentName);
                fragment = (Fragment) fragmentClass.newInstance();
                if (fragmentBundle != null) {
                    fragment.setArguments(fragmentBundle);
                }
            } catch (Exception e) {
                e.printStackTrace();
                finish();
            }
        }
        if (fragment != null) {
            mFragmentManager.beginTransaction().replace(R.id.fl_fragment, fragment).commitAllowingStateLoss();
        }
    }

}
