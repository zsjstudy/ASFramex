package cn.appoa.afdemo.activity.github;

import android.os.Bundle;

import cn.appoa.afbase.titlebar.BaseTitlebar;
import cn.appoa.afdemo.R;
import cn.appoa.afdemo.base.BaseActivity;
import cn.appoa.afui.titlebar.DefaultTitlebar;

/**
 * 强大的TextView
 */
public class SuperTextViewActivity extends BaseActivity {

    @Override
    public BaseTitlebar initTitlebar() {
        return new DefaultTitlebar.Builder(this)
                .setTitle("SuperTextView")
                .setBackImage(R.drawable.back_black)
                .create();
    }

    @Override
    public void initContent(Bundle savedInstanceState) {
        setContent(R.layout.activity_super_text_view);
    }

    @Override
    public void initData() {

    }
}
