package com.simple.architecture.ImageLoaderUtil.fresco;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.RoundingParams;
import com.simple.architecture.ImageLoaderUtil.wrap.IImageLoader;
import com.simple.architecture.ImageLoaderUtil.wrap.IImageView;
import com.simple.architecture.ImageLoaderUtil.wrap.SimpleOption;

/**
 * Created by sucer on 2016/4/17.
 */
public class ImageViewFresso extends com.facebook.drawee.view.SimpleDraweeView implements IImageView {
    SimpleOption option;

    public ImageViewFresso(Context context) {
        super(context);
    }

    public ImageViewFresso(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageViewFresso(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public SimpleOption getOption() {
        return option;
    }

    @Override
    public void setOption(SimpleOption option) {
        this.option = option;
    }

    @Override
    public void setCommonUrl(String url) {
        parseOption(this);
        setImageURI(Uri.parse(url));

    }

    @Override
    public void setCommonUrl(String url, IImageLoader.OnLoaderListener listener) {

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

        RoundingParams roundingParams = hierarchy.getRoundingParams();


        //圆角
        if (option.corner != null) {
            if(roundingParams == null){
                roundingParams = new RoundingParams();
            }
//            roundingParams.setOverlayColor(Color.RED);
//            roundingParams.setBorder(Color.GREEN,1f);
            roundingParams.setCornersRadius(option.corner);

            hierarchy.setRoundingParams(roundingParams);
        }

        //圆圈
        if (option.isRound != null) {
            if(roundingParams == null){
                roundingParams = new RoundingParams();
            }
            roundingParams.setRoundAsCircle(option.isRound);
        }

    }
}
