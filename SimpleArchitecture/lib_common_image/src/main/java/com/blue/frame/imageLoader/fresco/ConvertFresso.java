package com.blue.frame.imageLoader.fresco;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;

import com.blue.frame.imageLoader.wrapper.CustOption;
import com.blue.frame.imageLoader.wrapper.IConvert;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import java.io.File;


/**
 * Description:
 * Created by liw on 2016/4/26.
 */
public class ConvertFresso<T extends SimpleDraweeView> implements IConvert<T> {


    // 根据应用程序最大可用内存获取图片压缩较优大小
    private long getCompress() {
        long maxMemory = Runtime.getRuntime().maxMemory();
        return maxMemory / (1024 * 1100);
    }

    @Override
    public void init(Context context) {

        ImagePipelineConfig config = null;
        if (getCompress() < 150) {
            config = ImagePipelineConfig.newBuilder(context)
                    .setBitmapsConfig(Bitmap.Config.RGB_565)
                    .build();
        } else {
            config = ImagePipelineConfig.newBuilder(context)
                    .setBitmapsConfig(Bitmap.Config.ARGB_8888)
                    .build();
        }

        Fresco.initialize(context, config);

    }

    @Override
    public void setImageUrl(T imageView, String url) {
        setImageUrl(imageView, null, url);
    }

    @Override
    public void setImageUrl(T imageView, CustOption option, File file) {
        if (option != null) {
            parseOption(imageView, option);
        }
        imageView.setImageURI(Uri.fromFile(file));

    }

    @Override
    public void clearImage(File oldFile) {
        Fresco.getImagePipeline().evictFromCache(Uri.fromFile(oldFile));
        Fresco.getImagePipeline().evictFromDiskCache(Uri.fromFile(oldFile));
        Fresco.getImagePipeline().evictFromMemoryCache(Uri.fromFile(oldFile));
    }

    @Override
    public void setImageOption(SimpleDraweeView image, CustOption option) {
        if (option != null) {
            parseOption(image, option);
        }
    }


    @Override
    public void setImageUrl(SimpleDraweeView imageView, CustOption option, String url) {
        if (option != null) {
            parseOption(imageView, option);
        }
        imageView.setImageURI(Uri.parse(url));
    }


    /**
     * 解析 option进行定制
     *
     * @param imageView
     */
    private void parseOption(SimpleDraweeView imageView, CustOption option) {
        if (option == null) {
            return;
        }

        GenericDraweeHierarchy hierarchy = imageView.getHierarchy();

//        hierarchy.setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY);
        //占位符
        if (option.defaultRes != null) {

            hierarchy.setPlaceholderImage(option.defaultRes);
        }

        RoundingParams roundingParams = hierarchy.getRoundingParams();


        //圆角
        if (option.corner != null) {
            if (roundingParams == null) {
                roundingParams = new RoundingParams();
            }
            roundingParams.setCornersRadius(option.corner);

            hierarchy.setRoundingParams(roundingParams);
        }

        //圆圈
        if (option.isRound != null) {
            if (roundingParams == null) {
                roundingParams = new RoundingParams();
            }
            roundingParams.setRoundAsCircle(option.isRound);
        }
//        hierarchy.setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY);
    }

}
