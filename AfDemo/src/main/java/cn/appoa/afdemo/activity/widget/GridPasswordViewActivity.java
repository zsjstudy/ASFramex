package cn.appoa.afdemo.activity.widget;

import android.os.Bundle;

import cn.appoa.afbase.titlebar.BaseTitlebar;
import cn.appoa.afdemo.R;
import cn.appoa.afdemo.base.BaseActivity;
import cn.appoa.afui.titlebar.DefaultTitlebar;
import cn.appoa.afui.widget.gridpassword.GridPasswordView;
import cn.appoa.afutils.toast.ToastUtils;

/**
 * 支付密码输入框
 */
public class GridPasswordViewActivity extends BaseActivity {

    @Override
    public BaseTitlebar initTitlebar() {
        return new DefaultTitlebar.Builder(this)
                .setTitle("GridPasswordView")
                .setBackImage(R.drawable.back_black)
                .create();
    }

    @Override
    public void initContent(Bundle savedInstanceState) {
        setContent(R.layout.activity_grid_password_view);
    }

    private GridPasswordView mGridPasswordView;

    @Override
    public void initView() {
        super.initView();
        mGridPasswordView = (GridPasswordView) findViewById(R.id.mGridPasswordView);
        //是否显示密码
        //mGridPasswordView.setPasswordVisibility(true);
        //其他设置看GitHub上说明
    }

    @Override
    public void initData() {
        //事件监听
        mGridPasswordView.setOnPasswordChangedListener(new GridPasswordView.OnPasswordChangedListener() {
            @Override
            public void onTextChanged(String psw) {
                //文字改变
            }

            @Override
            public void onInputFinish(String psw) {
                //输入完成
                ToastUtils.showShort(mActivity, psw, true);
                //清空输入框
                //mGridPasswordView.clearPassword();
            }
        });
        //自动弹起键盘
        mGridPasswordView.setForceInputViewGetFocus();
    }

}
