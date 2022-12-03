package cn.appoa.afdemo.activity;

import cn.appoa.afdemo.activity.utils.AESUtilsActivity;
import cn.appoa.afdemo.activity.utils.AtyUtilsActivity;
import cn.appoa.afdemo.activity.utils.DateUtilsActivity;
import cn.appoa.afdemo.activity.utils.JsonUtilsActivity;
import cn.appoa.afdemo.activity.utils.MapUtilsActivity;
import cn.appoa.afdemo.activity.utils.Md5UtilsActivity;
import cn.appoa.afdemo.activity.utils.SpannableStringUtilsActivity;
import cn.appoa.afdemo.activity.utils.ThreadUtilsActivity;

/**
 * 时间格式化
 */
public class CommonUtilsActivity extends AbsListActivity {

    @Override
    protected CharSequence initTitle() {
        return "常用工具类";
    }

    @Override
    protected String[] initTitles() {
        return new String[]{
                "AESUtils\n【AES加密工具类】",
                "ThreadUtils\n【主线程和子线程切换】",
                "AtyUtils\n【Activity常用方法封装】",
                "DateUtils\n【时间格式化】",
                "JsonUtils\n【Json解析工具类】",
                "MapUtils\n【地图导航工具类】",
                "Md5Utils\n【Md5加密工具类】",
                "SpannableStringUtils\n【SpannableString】",
        };
    }

    @Override
    protected Class[] initClass() {
        return new Class[]{
                AESUtilsActivity.class,
                ThreadUtilsActivity.class,
                AtyUtilsActivity.class,
                DateUtilsActivity.class,
                JsonUtilsActivity.class,
                MapUtilsActivity.class,
                Md5UtilsActivity.class,
                SpannableStringUtilsActivity.class,
        };
    }

}
