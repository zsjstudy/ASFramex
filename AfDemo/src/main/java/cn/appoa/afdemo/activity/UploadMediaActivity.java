package cn.appoa.afdemo.activity;

import cn.appoa.afdemo.activity.upload.UploadAvatarActivity;
import cn.appoa.afdemo.activity.upload.UploadImageActivity;
import cn.appoa.afdemo.activity.upload.UploadVideoActivity;
import cn.appoa.afdemo.activity.upload.UploadVoiceActivity;

/**
 * 多媒体上传
 */
public class UploadMediaActivity extends AbsListActivity {

    @Override
    protected CharSequence initTitle() {
        return "多媒体上传";
    }

    @Override
    protected String[] initTitles() {
        return new String[]{
                "UploadAvatar\n【头像上传】",
                "UploadVideo\n【视频上传】",
                "UploadVoice\n【音频上传】",
                "UploadImage\n【图片上传】",
        };
    }

    @Override
    protected Class[] initClass() {
        return new Class[]{
                UploadAvatarActivity.class,
                UploadVideoActivity.class,
                UploadVoiceActivity.class,
                UploadImageActivity.class,
        };
    }

}
