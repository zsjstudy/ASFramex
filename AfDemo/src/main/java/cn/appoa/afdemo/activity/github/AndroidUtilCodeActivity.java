package cn.appoa.afdemo.activity.github;

import android.os.Bundle;

import cn.appoa.afbase.titlebar.BaseTitlebar;
import cn.appoa.afdemo.R;
import cn.appoa.afdemo.base.BaseActivity;
import cn.appoa.afui.titlebar.DefaultTitlebar;

/**
 * 安卓工具类库
 */
public class AndroidUtilCodeActivity extends BaseActivity {

    @Override
    public BaseTitlebar initTitlebar() {
        return new DefaultTitlebar.Builder(this)
                .setTitle("AndroidUtilCode")
                .setBackImage(R.drawable.back_black)
                .create();
    }

    @Override
    public void initContent(Bundle savedInstanceState) {
        setContent(R.layout.activity_android_util_code);
    }

    @Override
    public void initData() {

    }
}
