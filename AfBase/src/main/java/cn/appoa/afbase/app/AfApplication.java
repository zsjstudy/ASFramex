package cn.appoa.afbase.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import cn.appoa.afbase.R;
import cn.appoa.afbase.loader.AfImageLoader;
import cn.appoa.afhttp.AfHttp;
import cn.appoa.afutils.AfUtils;
import cn.appoa.afutils.app.CrashUtils;
import cn.appoa.afutils.app.Foreground;

/**
 * Application基类
 */
public abstract class AfApplication extends Application
        implements Foreground.ConfigurationsListener {

    /**
     * ApplicationContext
     */
    public static Context appContext;

    /**
     * Application
     */
    public static Application app;

    /**
     * ImageLoader
     */
    public static AfImageLoader imageLoader;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
        app = this;
        Foreground.init(app);
        Foreground.get().setConfigurationsListener(this);
        AfUtils.getInstance().init(appContext);
        AfHttp.getInstance().init(app);
        CrashUtils.getInstance().init(appContext);
        imageLoader = initImageLoader();
        if (imageLoader != null) {
            imageLoader.init(R.drawable.default_img, R.drawable.default_img, R.drawable.default_img);
        }
        //初始化App
        initApplication();
    }

    @Override
    public void onStarted(Activity activity) {

    }

    @Override
    public void onStopped(Activity activity) {

    }

    /**
     * 初始化ImageLoader
     */
    public abstract AfImageLoader initImageLoader();

    /**
     * 初始化App
     */
    public abstract void initApplication();
}
