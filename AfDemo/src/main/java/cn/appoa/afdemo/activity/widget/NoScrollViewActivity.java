package cn.appoa.afdemo.activity.widget;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import cn.appoa.afbase.adapter.AfPagerAdapter;
import cn.appoa.afbase.titlebar.BaseTitlebar;
import cn.appoa.afdemo.R;
import cn.appoa.afdemo.base.BaseActivity;
import cn.appoa.afdemo.fragment.NoScrollViewFragment;
import cn.appoa.afui.titlebar.DefaultTitlebar;
import cn.appoa.afui.widget.noscroll.NoScrollViewPager;
import cn.appoa.afutils.toast.ToastUtils;

/**
 * ScrollView嵌套滑动布局
 */
public class NoScrollViewActivity extends BaseActivity
        implements CompoundButton.OnCheckedChangeListener {

    @Override
    public BaseTitlebar initTitlebar() {
        return new DefaultTitlebar.Builder(this)
                .setTitle("ScrollView嵌套滑动布局")
                .setBackImage(R.drawable.back_black)
                .create();
    }

    @Override
    public void initContent(Bundle savedInstanceState) {
        setContent(R.layout.activity_no_scroll_view);
    }

    private RadioButton btn_can_scroll;
    private RadioButton btn_no_scroll;
    private NoScrollViewPager mViewPager;


    @Override
    public void initView() {
        super.initView();
        btn_can_scroll = (RadioButton) findViewById(R.id.btn_can_scroll);
        btn_no_scroll = (RadioButton) findViewById(R.id.btn_no_scroll);
        mViewPager = (NoScrollViewPager) findViewById(R.id.mViewPager);

        btn_can_scroll.setChecked(true);
        btn_can_scroll.setOnCheckedChangeListener(this);
        btn_no_scroll.setOnCheckedChangeListener(this);
    }

    @Override
    public void initData() {
        //TODO 模拟数据
        List<Fragment> listFragment = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            listFragment.add(new NoScrollViewFragment());
        }
        mViewPager.setAdapter(new AfPagerAdapter(mFragmentManager, listFragment));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ToastUtils.showShort(mActivity, position + 1 + "", false);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            switch (buttonView.getId()) {
                case R.id.btn_can_scroll:
                    mViewPager.setNoScroll(false);
                    break;
                case R.id.btn_no_scroll:
                    mViewPager.setNoScroll(true);
                    break;
            }
        }
    }

}
