package cn.appoa.afdemo.activity.widget;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.appoa.afbase.titlebar.BaseTitlebar;
import cn.appoa.afdemo.R;
import cn.appoa.afdemo.base.BaseActivity;
import cn.appoa.afdemo.bean.GoodsCategoryList;
import cn.appoa.afdemo.bean.GoodsTypeList;
import cn.appoa.afhttp.okgo.AfOkGo;
import cn.appoa.afhttp.okgo.OkGoDatasListener;
import cn.appoa.afui.dialog.StringWheelDialog;
import cn.appoa.afui.titlebar.DefaultTitlebar;
import cn.appoa.afutils.listener.OnCallbackListener;

/**
 * 滚轮控件
 */
public class WheelViewActivity extends BaseActivity
        implements View.OnClickListener, OnCallbackListener {

    @Override
    public BaseTitlebar initTitlebar() {
        return new DefaultTitlebar.Builder(this)
                .setTitle("WheelView")
                .setBackImage(R.drawable.back_black)
                .create();
    }

    @Override
    public void initContent(Bundle savedInstanceState) {
        setContent(R.layout.activity_wheel_view);
    }

    private TextView tv_goods_type;
    private TextView tv_goods_category;

    @Override
    public void initView() {
        super.initView();
        tv_goods_type = (TextView) findViewById(R.id.tv_goods_type);
        tv_goods_category = (TextView) findViewById(R.id.tv_goods_category);

        tv_goods_type.setOnClickListener(this);
        tv_goods_category.setOnClickListener(this);
    }

    private StringWheelDialog dialogType;
    private StringWheelDialog dialogCategory;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_goods_type:
                if (dialogType == null) {
                    getGoodsTypeList();
                } else {
                    dialogType.showDialog();
                }
                break;
            case R.id.tv_goods_category:
                if (dialogCategory == null) {
                    getGoodsCategoryList();
                } else {
                    dialogCategory.showDialog();
                }
                break;
        }
    }

    @Override
    public void initData() {

    }

    /**
     * 获取商品类型
     */
    private void getGoodsTypeList() {
        Map<String, String> params = new HashMap<>();
        AfOkGo.request("", params).tag(this.getRequestTag())
                .execute(new OkGoDatasListener<GoodsTypeList>
                        (this, "商品类型", GoodsTypeList.class) {
                    @Override
                    public void onDatasResponse(List<GoodsTypeList> datas) {
                        setGoodsTypeList(datas);
                    }

                    @Override
                    public void onError(Response<String> error) {
                        super.onError(error);
                        //TODO 模拟数据
                        List<GoodsTypeList> datas = new ArrayList<>();
                        for (int i = 0; i < 10; i++) {
                            datas.add(new GoodsTypeList(i + 1, "商品类型" + (i + 1)));
                        }
                        setGoodsTypeList(datas);
                    }
                });
    }

    private List<GoodsTypeList> datasType;

    /**
     * 设置商品类型
     *
     * @param datas
     */
    private void setGoodsTypeList(List<GoodsTypeList> datas) {
        datasType = datas;
        if (datasType != null && datasType.size() > 0) {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < datasType.size(); i++) {
                list.add(datasType.get(i).Name);
            }
            dialogType = new StringWheelDialog(mActivity, this, 1);
            dialogType.showStringWheelDialog("商品类型", list);
        }
    }

    /**
     * 获取商品分类
     */
    private void getGoodsCategoryList() {
        Map<String, String> params = new HashMap<>();
        AfOkGo.request("", params).tag(this.getRequestTag())
                .execute(new OkGoDatasListener<GoodsCategoryList>
                        (this, "", GoodsCategoryList.class) {
                    @Override
                    public void onDatasResponse(List<GoodsCategoryList> datas) {
                        setGoodsCategoryList(datas);
                    }

                    @Override
                    public void onError(Response<String> error) {
                        super.onError(error);
                        //TODO 模拟数据
                        List<GoodsCategoryList> datas = new ArrayList<>();
                        for (int i = 0; i < 10; i++) {
                            datas.add(new GoodsCategoryList(i + 1, "商品分类" + (i + 1)));
                        }
                        setGoodsCategoryList(datas);
                    }
                });
    }

    private List<GoodsCategoryList> datasCategory;

    private void setGoodsCategoryList(List<GoodsCategoryList> datas) {
        datasCategory = datas;
        if (datasCategory != null && datasCategory.size() > 0) {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < datasCategory.size(); i++) {
                list.add(datasCategory.get(i).Name);
            }
            dialogCategory = new StringWheelDialog(mActivity, this, 2);
            dialogCategory.showStringWheelDialog("商品分类", list);
        }
    }

    /**
     * 类型id
     */
    private int typeId;

    /**
     * 分类id
     */
    private int categoryId;

    @Override
    public void onCallback(int type, Object... obj) {
        int position = (int) obj[0];
        String name = (String) obj[1];
        switch (type) {
            case 1:
                typeId = datasType.get(position).ID;
                tv_goods_type.setText(name);
                break;
            case 2:
                categoryId = datasCategory.get(position).ID;
                tv_goods_category.setText(name);
                break;
        }
    }

}
