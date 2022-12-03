package cn.appoa.afdemo.activity.widget;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import cn.appoa.afbase.titlebar.BaseTitlebar;
import cn.appoa.afdemo.R;
import cn.appoa.afdemo.base.BaseActivity;
import cn.appoa.afui.titlebar.DefaultTitlebar;
import cn.appoa.afui.widget.layout.RatioRelativeLayout;
import cn.appoa.afutils.res.ViewUtils;
import cn.appoa.afutils.toast.ToastUtils;

/**
 * 可设置宽高比的RelativeLayout
 */
public class RatioRelativeLayoutActivity extends BaseActivity {

    @Override
    public BaseTitlebar initTitlebar() {
        return new DefaultTitlebar.Builder(this)
                .setTitle("RatioRelativeLayout")
                .setBackImage(R.drawable.back_black)
                .create();
    }

    @Override
    public void initContent(Bundle savedInstanceState) {
        setContent(R.layout.activity_ratio_relative_layout);
    }

    private EditText et_width;
    private EditText et_height;
    private TextView tv_confirm;
    private RatioRelativeLayout mRatioRelativeLayout;

    @Override
    public void initView() {
        super.initView();
        et_width = (EditText) findViewById(R.id.et_width);
        et_height = (EditText) findViewById(R.id.et_height);
        tv_confirm = (TextView) findViewById(R.id.tv_confirm);
        mRatioRelativeLayout = (RatioRelativeLayout) findViewById(R.id.mRatioRelativeLayout);

    }

    @Override
    public void initData() {
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ViewUtils.isTextEmpty(et_width)) {
                    ToastUtils.showShort(mActivity, et_width.getHint(), false);
                    return;
                }
                if (ViewUtils.isTextEmpty(et_height)) {
                    ToastUtils.showShort(mActivity, et_height.getHint(), false);
                    return;
                }
                int width = Integer.parseInt(ViewUtils.getText(et_width));
                int height = Integer.parseInt(ViewUtils.getText(et_height));
                if (width == 0) {
                    ToastUtils.showShort(mActivity, "宽度必须大于0", false);
                    return;
                }
                mRatioRelativeLayout.setRatio(width, height);
                hideSoftKeyboard();
            }
        });
    }
}
