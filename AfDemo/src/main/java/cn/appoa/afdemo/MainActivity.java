package cn.appoa.afdemo;

import android.view.KeyEvent;

import cn.appoa.afbase.titlebar.BaseTitlebar;
import cn.appoa.afdemo.activity.AbsListActivity;
import cn.appoa.afdemo.activity.CommonUtilsActivity;
import cn.appoa.afdemo.activity.CustomWidgetActivity;
import cn.appoa.afdemo.activity.GithubProjectActivity;
import cn.appoa.afdemo.activity.RefreshBeanActivity;
import cn.appoa.afdemo.activity.UploadMediaActivity;
import cn.appoa.afdemo.activity.WebViewActivity;
import cn.appoa.afdemo.activity.ZmQrCodeActivity;
import cn.appoa.afdemo.activity.ZmShakeActivity;
import cn.appoa.afui.titlebar.DefaultTitlebar;

/**
 * 主页面
 */
public class MainActivity extends AbsListActivity {

    @Override
    public BaseTitlebar initTitlebar() {
        return new DefaultTitlebar.Builder(this)
                .setTitle(R.string.app_name)
                .create();
    }

    @Override
    protected CharSequence initTitle() {
        return getString(R.string.app_name);
    }

    @Override
    protected String[] initTitles() {
        return new String[]{
                "常用工具类",
                "Github优秀开源库",
                "自定义控件",
                "下拉刷新",
                "多媒体上传",
                "WebView的使用",
                "微信扫一扫",
                "微信摇一摇",
        };
    }

    @Override
    protected Class[] initClass() {
        return new Class[]{
                CommonUtilsActivity.class,
                GithubProjectActivity.class,
                CustomWidgetActivity.class,
                RefreshBeanActivity.class,
                UploadMediaActivity.class,
                WebViewActivity.class,
                ZmQrCodeActivity.class,
                ZmShakeActivity.class,
        };
    }

    @Override
    public boolean enableSliding() {
        return false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && //
                event.getAction() == KeyEvent.ACTION_DOWN) {
            doubleClickExit(2000, "再按一次返回键退出程序");
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
