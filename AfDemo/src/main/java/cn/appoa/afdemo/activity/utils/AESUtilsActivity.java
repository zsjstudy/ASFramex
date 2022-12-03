package cn.appoa.afdemo.activity.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import cn.appoa.afbase.titlebar.BaseTitlebar;
import cn.appoa.afdemo.R;
import cn.appoa.afdemo.app.MyApplication;
import cn.appoa.afdemo.base.BaseActivity;
import cn.appoa.afui.titlebar.DefaultTitlebar;
import cn.appoa.afutils.encrypt.AESUtils;
import cn.appoa.afutils.file.ACache;
import cn.appoa.afutils.net.LogUtils;
import cn.appoa.afutils.res.ViewUtils;
import cn.appoa.afutils.toast.ToastUtils;

/**
 * AES加密工具类
 */
public class AESUtilsActivity extends BaseActivity
        implements View.OnClickListener {

    @Override
    public BaseTitlebar initTitlebar() {
        return new DefaultTitlebar.Builder(this)
                .setTitle("AESUtils")
                .setBackImage(R.drawable.back_black)
                .create();
    }

    @Override
    public void initContent(Bundle savedInstanceState) {
        setContent(R.layout.activity_aes_utils);
    }

    private EditText et_default_key;
    private Button btn_default_key;
    private EditText et_default_value;
    private Button btn_default_value;
    private Button btn_aes_1;
    private EditText et_result_key;
    private EditText et_result_value;
    private Button btn_aes_2;
    private TextView tv_result;

    @Override
    public void initView() {
        super.initView();
        et_default_key = (EditText) findViewById(R.id.et_default_key);
        btn_default_key = (Button) findViewById(R.id.btn_default_key);
        et_default_value = (EditText) findViewById(R.id.et_default_value);
        btn_default_value = (Button) findViewById(R.id.btn_default_value);
        btn_aes_1 = (Button) findViewById(R.id.btn_aes_1);
        et_result_key = (EditText) findViewById(R.id.et_result_key);
        et_result_value = (EditText) findViewById(R.id.et_result_value);
        btn_aes_2 = (Button) findViewById(R.id.btn_aes_2);
        tv_result = (TextView) findViewById(R.id.tv_result);
    }

    @Override
    public void initData() {
        et_default_key.setText(MyApplication.aes_key);
        String aes_value = ACache.get(this).getAsString("aes_value");
        if (aes_value == null) {
            aes_value = "xuexiguofang";
        }
        et_default_value.setText(aes_value);
        //点击事件
        btn_default_key.setOnClickListener(this);
        btn_default_value.setOnClickListener(this);
        btn_aes_1.setOnClickListener(this);
        btn_aes_2.setOnClickListener(this);
        tv_result.setOnClickListener(this);
    }

    private ClipboardManager clipboard;

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_aes_1 || v.getId() == R.id.btn_aes_2) {
            if (ViewUtils.isTextEmpty(et_default_key)) {
                ToastUtils.showShort(mActivity, et_default_key.getHint(), false);
                return;
            }
            Map<String, String> params = null;
            switch (v.getId()) {
                case R.id.btn_aes_1:
                    if (ViewUtils.isTextEmpty(et_default_value)) {
                        ToastUtils.showShort(mActivity, et_default_value.getHint(), false);
                        return;
                    }
                    params = getParams(ViewUtils.getText(et_default_value));
                    break;
                case R.id.btn_aes_2:
                    if (ViewUtils.isTextEmpty(et_result_key)) {
                        ToastUtils.showShort(mActivity, et_result_key.getHint(), false);
                        return;
                    }
                    if (ViewUtils.isTextEmpty(et_result_value)) {
                        ToastUtils.showShort(mActivity, et_result_value.getHint(), false);
                        return;
                    }
                    params = getParams(ViewUtils.getText(et_result_key), ViewUtils.getText(et_result_value));
                    break;
            }
            if (params != null) {
                String text = "";
                for (String key : params.keySet()) {
                    text = text + key + "<<————>>" + params.get(key) + "\n";
                }
                tv_result.setText(text);
            }
            hideSoftKeyboard();
        } else {
            switch (v.getId()) {
                case R.id.btn_default_key:
                    if (ViewUtils.isTextEmpty(et_default_key)) {
                        ToastUtils.showShort(mActivity, et_default_key.getHint(), false);
                    } else {
                        ACache.get(this).put("aes_key", ViewUtils.getText(et_default_key));
                        ToastUtils.showShort(mActivity, "保存成功", false);
                    }
                    break;
                case R.id.btn_default_value:
                    if (ViewUtils.isTextEmpty(et_default_value)) {
                        ToastUtils.showShort(mActivity, et_default_value.getHint(), false);
                    } else {
                        ACache.get(this).put("aes_value", ViewUtils.getText(et_default_value));
                        ToastUtils.showShort(mActivity, "保存成功", false);
                    }
                    break;
                case R.id.tv_result:
                    String text = tv_result.getText().toString().trim();
                    if (!TextUtils.isEmpty(text)) {
                        if (clipboard == null) {
                            clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                        }
                        clipboard.setPrimaryClip(ClipData.newPlainText(null, text));
                        ToastUtils.showShort(mActivity, "复制成功", false);
                    }
                    break;
            }
        }
    }

    /**
     * 获取参数
     *
     * @return
     */
    public static Map<String, String> getParams(String value) {
        return getParams(null, value);
    }

    /**
     * 获取参数
     *
     * @return
     */
    public static Map<String, String> getParams(String key, String value) {
        Map<String, String> params = new HashMap<>();
        if (!TextUtils.isEmpty(value)) {
            params.put("encrypt", AESUtils.encode(value));
            if (!TextUtils.isEmpty(key)) {
                params.put(key, value);
            }
        }
        LogUtils.i("params", params.toString());
        return params;
    }

}
