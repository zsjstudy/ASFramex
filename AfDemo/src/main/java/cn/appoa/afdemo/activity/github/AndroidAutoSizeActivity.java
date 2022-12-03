package cn.appoa.afdemo.activity.github;

import android.os.Bundle;

import cn.appoa.afbase.titlebar.BaseTitlebar;
import cn.appoa.afdemo.R;
import cn.appoa.afdemo.base.BaseActivity;
import cn.appoa.afui.titlebar.DefaultTitlebar;

/**
 * Android屏幕适配方案
 */
public class AndroidAutoSizeActivity extends BaseActivity {

    @Override
    public BaseTitlebar initTitlebar() {
        return new DefaultTitlebar.Builder(this)
                .setTitle("AndroidAutoSize")
                .setBackImage(R.drawable.back_black)
                .create();
    }

    @Override
    public void initContent(Bundle savedInstanceState) {
        setContent(R.layout.activity_android_auto_size);
    }

    @Override
    public void initData() {

    }
}
