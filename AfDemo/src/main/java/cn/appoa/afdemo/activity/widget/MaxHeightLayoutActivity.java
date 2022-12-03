package cn.appoa.afdemo.activity.widget;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.List;

import cn.appoa.afbase.adapter.AfAdapter;
import cn.appoa.afbase.adapter.AfHolder;
import cn.appoa.afbase.titlebar.BaseTitlebar;
import cn.appoa.afdemo.R;
import cn.appoa.afdemo.base.BaseActivity;
import cn.appoa.afui.titlebar.DefaultTitlebar;
import cn.appoa.afui.widget.layout.MaxHeightLayout;

/**
 * 可设置最大高度的FrameLayout
 */
public class MaxHeightLayoutActivity extends BaseActivity
        implements CompoundButton.OnCheckedChangeListener {

    @Override
    public BaseTitlebar initTitlebar() {
        return new DefaultTitlebar.Builder(this)
                .setTitle("MaxHeightLayout")
                .setBackImage(R.drawable.back_black)
                .create();
    }

    @Override
    public void initContent(Bundle savedInstanceState) {
        setContent(R.layout.activity_max_height_layout);
    }

    private RadioButton btn_data3;
    private RadioButton btn_data30;
    private MaxHeightLayout mMaxHeightLayout;
    private ListView mListView;

    @Override
    public void initView() {
        super.initView();
        btn_data3 = (RadioButton) findViewById(R.id.btn_data3);
        btn_data30 = (RadioButton) findViewById(R.id.btn_data30);
        mMaxHeightLayout = (MaxHeightLayout) findViewById(R.id.mMaxHeightLayout);
        mListView = (ListView) findViewById(R.id.mListView);

        btn_data3.setOnCheckedChangeListener(this);
        btn_data30.setOnCheckedChangeListener(this);
    }

    @Override
    public void initData() {
        btn_data3.setChecked(true);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            switch (buttonView.getId()) {
                case R.id.btn_data3:
                    setData(3);
                    break;
                case R.id.btn_data30:
                    setData(30);
                    break;
            }
        }
    }

    /**
     * 模拟数据
     */
    private void setData(int size) {
        if (size == 0) {
            return;
        }
        List<String> titles = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            titles.add("标题" + (i + 1));
        }
        mListView.setAdapter(new AfAdapter<String>(mActivity, titles, R.layout.item_main) {
            @Override
            public void init(AfHolder zmHolder, String t, int position) {
                zmHolder.setText(R.id.tv_main, t);
            }
        });
    }

}
