package cn.appoa.afdemo.activity.github;

import android.os.Bundle;

import cn.appoa.afbase.titlebar.BaseTitlebar;
import cn.appoa.afdemo.R;
import cn.appoa.afdemo.base.BaseActivity;
import cn.appoa.afui.titlebar.DefaultTitlebar;

/**
 * android自动生成表格框架
 */
public class SmartTableActivity extends BaseActivity {

    @Override
    public BaseTitlebar initTitlebar() {
        return new DefaultTitlebar.Builder(this)
                .setTitle("SmartTable")
                .setBackImage(R.drawable.back_black)
                .create();
    }

    @Override
    public void initContent(Bundle savedInstanceState) {
        setContent(R.layout.activity_smart_table);
    }

    @Override
    public void initData() {

    }
}
