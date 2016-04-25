package com.simple.architecture.business.demo;

import android.app.Activity;
import android.os.Bundle;

import com.simple.architecture.frame.imageLoader.fresco.ImageViewFresso;
import com.simple.architecture.frame.imageLoader.wrap.SimpleOption;
import com.simple.architecture.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by sucer on 2016/4/17.
 */
public class ImageLoadActivity extends Activity {


    @Bind(R.id.iv_imageloader_bg)
    ImageViewFresso ivImageloaderBg;
    @Bind(R.id.iv_imageloader_icon)
    ImageViewFresso ivImageloaderIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco_demo);
        ButterKnife.bind(this);


        ivImageloaderBg.setOption(new SimpleOption().setDefaultRes(R.mipmap.ic_launcher));
        ivImageloaderBg.setCommonUrl("http://img2.imgtn.bdimg.com/it/u=2799938883,1493643089&fm=21&gp=0.jpg");

        ivImageloaderIcon.setOption(new SimpleOption().setDefaultRes(R.mipmap.ic_launcher).setCorner(50f));
        ivImageloaderIcon.setCommonUrl("http://t3.s1.dpfile.com/pc/mc/91d7b84e0a8e8fe3bea3b5f604792b24%28640x1024%29/thumb.jpg");
    }
}


