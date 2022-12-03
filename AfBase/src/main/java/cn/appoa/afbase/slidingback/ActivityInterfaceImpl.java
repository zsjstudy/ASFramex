package cn.appoa.afbase.slidingback;

import android.app.Application;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import cn.appoa.afhttp.net.NetStateChangeObserver;
import cn.appoa.afhttp.net.NetStateChangeReceiver;

/**
 * 这个类用来管理 activity 的栈
 *
 * @author lihong
 * @since 2016/10/28
 */
public abstract class ActivityInterfaceImpl extends AppCompatActivity
        implements ActivityInterface, NetStateChangeObserver {

    private Application.ActivityLifecycleCallbacks mActivityLifecycleCallbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        NetStateChangeReceiver.registerReceiver(this);
        super.onCreate(savedInstanceState);
        ActivityStackManager.addToStack(this);
    }

    @Override
    protected void onDestroy() {
        NetStateChangeReceiver.unRegisterReceiver(this);
        super.onDestroy();
        ActivityStackManager.removeFromStack(this);
        if (mActivityLifecycleCallbacks != null) {
            mActivityLifecycleCallbacks.onActivityDestroyed(this);
        }
    }

    @Override
    public void setActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks callbacks) {
        mActivityLifecycleCallbacks = callbacks;
    }

    @Override
    protected void onResume() {
        super.onResume();
        NetStateChangeReceiver.registerObserver(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        NetStateChangeReceiver.unRegisterObserver(this);
    }
}
