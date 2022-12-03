package cn.appoa.afdemo.activity.utils;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cn.appoa.afbase.titlebar.BaseTitlebar;
import cn.appoa.afdemo.R;
import cn.appoa.afdemo.base.BaseActivity;
import cn.appoa.afui.titlebar.DefaultTitlebar;
import cn.appoa.afutils.file.ThreadUtils;
import cn.appoa.afutils.res.PhoneUtils;
import cn.appoa.afutils.res.ViewUtils;
import cn.appoa.afutils.toast.ToastUtils;

/**
 * 主线程和子线程切换
 */
public class ThreadUtilsActivity extends BaseActivity {

    @Override
    public BaseTitlebar initTitlebar() {
        return new DefaultTitlebar.Builder(this)
                .setTitle("ThreadUtils")
                .setBackImage(R.drawable.back_black)
                .create();
    }

    @Override
    public void initContent(Bundle savedInstanceState) {
        setContent(R.layout.activity_thread_utils);
    }

    private EditText et_login_phone;
    private EditText et_login_pwd;
    private Button btn_login;

    @Override
    public void initView() {
        super.initView();
        et_login_phone = (EditText) findViewById(R.id.et_login_phone);
        et_login_pwd = (EditText) findViewById(R.id.et_login_pwd);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        if (ViewUtils.isTextEmpty(et_login_phone)) {
            ToastUtils.showShort(mActivity, et_login_phone.getHint(), false);
            return;
        }
        String phone = ViewUtils.getText(et_login_phone);
        if (!PhoneUtils.isMobile(phone)) {
            ToastUtils.showShort(mActivity, "请输入正确的手机号", false);
            return;
        }
        if (ViewUtils.isTextEmpty(et_login_pwd)) {
            ToastUtils.showShort(mActivity, et_login_pwd.getHint(), false);
            return;
        }
        showLoading("正在登录...");
        ThreadUtils.runInBack(new Runnable() {//切换子线程
            @Override
            public void run() {
                try {
                    Thread.sleep(3 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ThreadUtils.runInMain(new Runnable() {//切换主线程
                    @Override
                    public void run() {
                        dismissLoading();
                        ToastUtils.showShort(mActivity, "登录成功", false);
                        setResult(RESULT_OK);
                        finish();
                    }
                });
            }
        });
    }

    @Override
    public void initData() {

    }
}
