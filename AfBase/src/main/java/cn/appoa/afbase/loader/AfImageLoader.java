package cn.appoa.afbase.loader;

import android.content.Context;
import android.widget.ImageView;

/**
 * 加载网络图片
 */
public abstract class AfImageLoader {

    /**
     * 上下文
     */
    public Context context;

    /**
     * 是否初始化
     */
    public boolean isInited;

    /**
     * 加载中图片
     */
    public int loadingImg;

    /**
     * 加载失败图片
     */
    public int errorImg;

    /**
     * 默认图片
     */
    public int defaultImg;

    /**
     * 初始化
     *
     * @param loadingImg 加载中图片
     * @param errorImg   加载失败图片
     * @param defaultImg 默认图片
     */
    public abstract void init(int loadingImg, int errorImg, int defaultImg);

    /**
     * 清除缓存
     */
    public abstract void clearCache();

    /**
     * @param url 图片地址
     * @param iv  ImageView
     */
    public abstract void loadImage(String url, ImageView iv);

    /**
     * @param url        图片地址
     * @param iv         ImageView
     * @param defaultImg 默认图片
     */
    public abstract void loadImage(String url, ImageView iv, int defaultImg);

    /**
     * @param url        图片地址
     * @param iv         ImageView
     * @param loadingImg 加载中图片
     * @param errorImg   加载失败图片
     * @param defaultImg 默认图片
     */
    public abstract void loadImage(String url, ImageView iv, int loadingImg, int errorImg, int defaultImg);

    /**
     * @param url      图片地址
     * @param iv       ImageView
     * @param listener 加载图片监听
     */
    public abstract void loadImage(String url, ImageView iv, final LoadingBitmapListener listener);

    /**
     * @param url        图片地址
     * @param iv         ImageView
     * @param defaultImg 默认图片
     * @param listener   加载图片监听
     */
    public abstract void loadImage(String url, ImageView iv, int defaultImg, final LoadingBitmapListener listener);

    /**
     * @param url        图片地址
     * @param iv         ImageView
     * @param loadingImg 加载中图片
     * @param errorImg   加载失败图片
     * @param defaultImg 默认图片
     * @param listener   加载图片监听
     */
    public abstract void loadImage(String url, ImageView iv, int loadingImg, int errorImg, int defaultImg,
                                   final LoadingBitmapListener listener);

    /**
     * 下载图片
     *
     * @param url      图片地址
     * @param listener 加载图片监听
     */
    public abstract void downloadImage(String url, final LoadingBitmapListener listener);
}
