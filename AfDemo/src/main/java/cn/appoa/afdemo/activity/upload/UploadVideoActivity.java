package cn.appoa.afdemo.activity.upload;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cjt2325.cameralibrary.JCameraView;

import androidx.annotation.Nullable;
import cn.appoa.afbase.titlebar.BaseTitlebar;
import cn.appoa.afdemo.R;
import cn.appoa.afdemo.base.BaseImageActivity;
import cn.appoa.afmedia.activity.AfVideoPlayerActivity;
import cn.appoa.afrecorder.activity.JCameraViewActivity;
import cn.appoa.afui.titlebar.DefaultTitlebar;
import cn.appoa.afutils.app.FastClickUtils;
import cn.appoa.afutils.res.ViewUtils;
import cn.appoa.afutils.toast.ToastUtils;


/**
 * 视频上传
 */
public class UploadVideoActivity extends BaseImageActivity
        implements View.OnClickListener {

    @Override
    public BaseTitlebar initTitlebar() {
        return new DefaultTitlebar.Builder(this)
                .setTitle("视频上传")
                .setBackImage(R.drawable.back_black)
                .create();
    }

    @Override
    public void initContent(Bundle savedInstanceState) {
        setContent(R.layout.activity_upload_video);
    }

    private TextView tv_video_upload;
    private TextView tv_video_path;
    private TextView tv_video_play1;
    private TextView tv_video_play2;

    @Override
    public void initView() {
        super.initView();
        tv_video_upload = (TextView) findViewById(R.id.tv_video_upload);
        tv_video_path = (TextView) findViewById(R.id.tv_video_path);
        tv_video_play1 = (TextView) findViewById(R.id.tv_video_play1);
        tv_video_play2 = (TextView) findViewById(R.id.tv_video_play2);

        tv_video_upload.setOnClickListener(this);
        tv_video_play1.setOnClickListener(this);
        tv_video_play2.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        if (FastClickUtils.isFastClick()) {
            return;
        }
        switch (v.getId()) {
            case R.id.tv_video_upload://上传视频
                showUploadVideoDialog();
                break;
            case R.id.tv_video_play1://本地播放
                if (ViewUtils.isTextEmpty(tv_video_path)) {
                    ToastUtils.showShort(mActivity, "请先上传视频", false);
                } else {
                    startActivity(new Intent(mActivity, AfVideoPlayerActivity.class)
                            .putExtra("videoUrl", recorderUrl)
                            .putExtra("videoImage", recorderPath)
                    );
                }
                break;
            case R.id.tv_video_play2://网络播放
                startActivity(new Intent(mActivity, AfVideoPlayerActivity.class)
                        .putExtra("videoUrl", "http://200024424.vod.myqcloud.com/200024424_670222d0bdf811e6ad39991f76a4df69.f30.mp4")
                        .putExtra("videoImage", "https://mc.qcloudimg.com/static/img/c635908bebbdf8fb747388a83902886e/mlvb_basic_function+(2).jpeg")
                        .putExtra("videoTitle", "基础功能")
                );
                break;
        }
    }

    @Override
    public void selectVideoFromCamera() {
        //super.selectVideoFromCamera();
        startActivityForResult(new Intent(mActivity, JCameraViewActivity.class)
                        .putExtra("recorderState", JCameraView.BUTTON_STATE_ONLY_RECORDER)
                        .putExtra("recorderDuration", 15)
                , REQUEST_CODE_RECORD);
    }

    @Override
    public void selectVideoFromAlbum() {
        super.selectVideoFromAlbum();
    }

    private int recorderType;
    private String recorderPath;
    private String recorderUrl;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_RECORD && resultCode == Activity.RESULT_OK) {
            //拍摄成功
            recorderType = data.getIntExtra("recorderType", 1);
            recorderUrl = data.getStringExtra("recorderUrl");
            recorderPath = data.getStringExtra("recorderPath");
            getVideoPath(1, recorderUrl, recorderPath);
        }
    }

    @Override
    protected void getVideoPath(int type, String videoPath, String videoImage) {
        recorderUrl = videoPath;
        recorderPath = videoImage;
        tv_video_path.setText(videoPath);
    }

}
