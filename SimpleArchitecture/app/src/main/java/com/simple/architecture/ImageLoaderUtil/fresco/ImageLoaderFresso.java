package com.simple.architecture.ImageLoaderUtil.fresco;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.simple.architecture.ImageLoaderUtil.wrap.IImageLoader;
import com.simple.architecture.ImageLoaderUtil.wrap.SimpleOption;

/**
 * Created by sucer on 2016/4/17.
 */
public class ImageLoaderFresso implements IImageLoader<ImageViewFresso> {

    private static IImageLoader iImageLoader = new ImageLoaderFresso();

    public static IImageLoader getInstance() {
        if (iImageLoader == null) {
            iImageLoader = new ImageLoaderFresso();
            return iImageLoader;
        } else {
            return iImageLoader;
        }
    }

    public void init(Context context) {

        ImagePipelineConfig config;

        config = ImagePipelineConfig.newBuilder(context)
                .setBitmapsConfig(Bitmap.Config.ARGB_8888)
                .build();

        Fresco.initialize(context, config);

    }


//    @Override
//    public void setDefaultOption( SimpleOption option) {
//
//    }

    @Override
    public void setImageUrl(ImageViewFresso imageView, String url) {
        parseOption(imageView);
        imageView.setImageURI(Uri.parse(url));

    }

    @Override
    public void setImageUrl(ImageViewFresso imageView, String url, OnLoaderListener listener) {

    }

    /**
     * 解析 option进行定制
     *
     * @param imageView
     */
    public void parseOption(ImageViewFresso imageView) {
        SimpleOption option = imageView.getOption();
        if (option == null) {
            return;
        }

        GenericDraweeHierarchy hierarchy = imageView.getHierarchy();

        //占位符
        if (option.defaultRes != null) {

            hierarchy.setPlaceholderImage(option.defaultRes);
        }

        RoundingParams roundingParams = imageView.getHierarchy().getRoundingParams();
        //圆角
        if (option.corner != null) {
            roundingParams.setBorder(Color.GREEN, option.corner);
            hierarchy.setRoundingParams(roundingParams);
        }

        //圆圈
        if (option.isRound != null) {
            roundingParams.setRoundAsCircle(option.isRound);
        }

    }


}
