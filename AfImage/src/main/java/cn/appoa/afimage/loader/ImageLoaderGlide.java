package cn.appoa.afimage.loader;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import cn.appoa.afbase.loader.AfImageLoader;
import cn.appoa.afbase.loader.LoadingBitmapListener;

/**
 * Glide加载图片
 */
public class ImageLoaderGlide extends AfImageLoader {

    private static ImageLoaderGlide instance = null;

    private ImageLoaderGlide(Context context) {
        this.context = context;
    }

    public synchronized static AfImageLoader getInstance(Context context) {
        if (instance == null) {
            instance = new ImageLoaderGlide(context);
        }
        return instance;
    }

    private DiskCacheStrategy strategy;

    /**
     * 设置缓存方式
     */
    public void setDiskCacheStrategy(DiskCacheStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void init(int loadingImg, int errorImg, int defaultImg) {
        this.loadingImg = loadingImg;
        this.errorImg = errorImg;
        this.defaultImg = defaultImg;
        this.strategy = DiskCacheStrategy.ALL;
        isInited = true;
    }

    /**
     * 初始化请求
     *
     * @param url
     * @return
     */
    private RequestBuilder getRequestBuilder(String url) {
        RequestManager manager = Glide.with(context);
        if (!TextUtils.isEmpty(url) &&
                (url.endsWith(".gif") || url.endsWith(".GIF"))) {
            return manager.asGif().load(url);
        } else {
            return manager.asBitmap().load(url);
        }
    }

    @Override
    public void loadImage(String url, ImageView iv) {
        if (isInited && iv != null) {
            getRequestBuilder(url).apply(new RequestOptions()
                    .diskCacheStrategy(strategy)
                    .centerCrop()
                    .diskCacheStrategy(strategy)//
                    .placeholder(loadingImg)//
                    .error(errorImg)//
                    .fallback(defaultImg))
                    .into(iv);
        }
    }

    @Override
    public void loadImage(String url, ImageView iv, int defaultImg) {
        if (isInited && iv != null) {
            getRequestBuilder(url).apply(new RequestOptions()
                    .diskCacheStrategy(strategy)
                    .centerCrop()
                    .diskCacheStrategy(strategy)//
                    .placeholder(defaultImg)//
                    .error(defaultImg)//
                    .fallback(defaultImg))
                    .into(iv);
        }
    }

    @Override
    public void loadImage(String url, ImageView iv, int loadingImg, int errorImg, int defaultImg) {
        if (isInited && iv != null) {
            getRequestBuilder(url).apply(new RequestOptions()
                    .diskCacheStrategy(strategy)
                    .centerCrop()
                    .diskCacheStrategy(strategy)//
                    .placeholder(loadingImg)//
                    .error(errorImg)//
                    .fallback(defaultImg))
                    .into(iv);
        }
    }

    @Override
    public void loadImage(String url, ImageView iv, final LoadingBitmapListener listener) {
        if (isInited && iv != null && listener != null) {
            getRequestBuilder(url).apply(new RequestOptions()
                    .diskCacheStrategy(strategy)
                    .centerCrop()
                    .diskCacheStrategy(strategy)//
                    .placeholder(loadingImg)//
                    .error(errorImg)//
                    .fallback(defaultImg))
                    .listener(new RequestListener<Bitmap>() {

                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model,
                                                    Target<Bitmap> target, boolean isFirstResource) {
                            listener.loadingBitmapFailed();
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Bitmap resource, Object model,
                                                       Target<Bitmap> target, DataSource dataSource,
                                                       boolean isFirstResource) {
                            listener.loadingBitmapSuccess(resource);
                            return false;
                        }
                    }).into(iv);
        }
    }

    @Override
    public void loadImage(String url, ImageView iv, int defaultImg, final LoadingBitmapListener listener) {
        if (isInited && iv != null && listener != null) {
            getRequestBuilder(url).apply(new RequestOptions()
                    .diskCacheStrategy(strategy)
                    .centerCrop()
                    .diskCacheStrategy(strategy)//
                    .placeholder(defaultImg)//
                    .error(defaultImg)//
                    .fallback(defaultImg))
                    .listener(new RequestListener<Bitmap>() {

                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model,
                                                    Target<Bitmap> target, boolean isFirstResource) {
                            listener.loadingBitmapFailed();
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Bitmap resource, Object model,
                                                       Target<Bitmap> target, DataSource dataSource,
                                                       boolean isFirstResource) {
                            listener.loadingBitmapSuccess(resource);
                            return false;
                        }
                    }).into(iv);
        }
    }

    @Override
    public void loadImage(String url, ImageView iv, int loadingImg, int errorImg, int defaultImg,
                          final LoadingBitmapListener listener) {
        if (isInited && iv != null && listener != null) {
            getRequestBuilder(url).apply(new RequestOptions()
                    .diskCacheStrategy(strategy)
                    .centerCrop()
                    .diskCacheStrategy(strategy)//
                    .placeholder(loadingImg)//
                    .error(errorImg)//
                    .fallback(defaultImg))
                    .listener(new RequestListener<Bitmap>() {

                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model,
                                                    Target<Bitmap> target, boolean isFirstResource) {
                            listener.loadingBitmapFailed();
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Bitmap resource, Object model,
                                                       Target<Bitmap> target, DataSource dataSource,
                                                       boolean isFirstResource) {
                            listener.loadingBitmapSuccess(resource);
                            return false;
                        }
                    }).into(iv);
        }
    }

    @Override
    public void downloadImage(String url, final LoadingBitmapListener listener) {
        if (isInited && listener != null) {
            getRequestBuilder(url).apply(new RequestOptions()
                    .diskCacheStrategy(strategy))
                    .into(new SimpleTarget<Bitmap>() {

                        @Override
                        public void onResourceReady(@NonNull Bitmap resource,
                                                    @Nullable Transition<? super Bitmap> transition) {
                            if (resource == null) {
                                listener.loadingBitmapFailed();
                            } else {
                                listener.loadingBitmapSuccess(resource);
                            }
                        }
                    });
        }
    }

    @Override
    public void clearCache() {
        if (isInited) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Glide.get(context).clearDiskCache();
                }
            }).start();
            Glide.get(context).clearMemory();
        }
    }

}
