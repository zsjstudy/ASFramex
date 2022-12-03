package cn.appoa.afui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import cn.appoa.afbase.app.AfApplication;
import cn.appoa.afbase.loader.LoadingBitmapListener;
import cn.appoa.afui.R;
import cn.appoa.afui.widget.photoview.OnPhotoTapListener;
import cn.appoa.afui.widget.photoview.PhotoView;

/**
 * 大图预览
 */
public class ShowBigImageListActivity extends AppCompatActivity {

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initContent();
    }

    protected ViewPager vp_image;
    protected TextView tv_image_page;
    protected int pageMax;

    protected void initContent() {
        setContentView(R.layout.activity_show_big_image_list);

        vp_image = (ViewPager) findViewById(R.id.vp_image);
        tv_image_page = (TextView) findViewById(R.id.tv_image_page);

        Intent intent = getIntent();
        int page = intent.getIntExtra("page", 0);
        ArrayList<String> images = intent.getStringArrayListExtra("images");
        if (images != null && images.size() > 0) {
            pageMax = images.size();
            vp_image.setOffscreenPageLimit(pageMax);
            tv_image_page.setText("1/" + pageMax);
            PhotoAdapter adapter = new PhotoAdapter(images);
            vp_image.setAdapter(adapter);
            vp_image.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    tv_image_page.setText(position + 1 + "/" + pageMax);
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
            vp_image.setCurrentItem(page);
        }
    }

    public class PhotoAdapter extends PagerAdapter {

        private List<String> list;

        public PhotoAdapter(List<String> list) {
            super();
            this.list = list;
        }

        @Override
        public int getCount() {
            return list == null ? 0 : list.size();
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            final PhotoView photoView = new PhotoView(container.getContext());
            String path = list.get(position);
            AfApplication.imageLoader.downloadImage(path, new LoadingBitmapListener() {
                @Override
                public void loadingBitmapSuccess(Bitmap bitmap) {
                    photoView.setImageBitmap(bitmap);
                }

                @Override
                public void loadingBitmapFailed() {

                }
            });
            photoView.setOnPhotoTapListener(new OnPhotoTapListener() {
                @Override
                public void onPhotoTap(ImageView view, float x, float y) {
                    finish();
                }
            });
            // Now just add PhotoView to ViewPager and return it
            ((ViewPager) container).addView(photoView,
                    ViewPager.LayoutParams.MATCH_PARENT,
                    ViewPager.LayoutParams.MATCH_PARENT);
            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    }

    @Override
    public void finish() {
        super.finish();
    }
}
