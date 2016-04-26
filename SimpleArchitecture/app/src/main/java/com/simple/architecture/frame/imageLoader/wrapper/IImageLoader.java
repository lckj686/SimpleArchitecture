package com.simple.architecture.frame.imageLoader.wrapper;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by sucer on 2016/4/17.
 */
public interface IImageLoader<T extends ImageView> {

//    void setConvert(IConvert convert);

    void init(Context context);
//    void setDefaultOption(T imageView, CustOption option);

    /**
     * 加载url
     */
    void setImageUrl(T imageView, String url);

  void setImageUrl(T imageView, CustOption option, String url);

//    <T extends ImageView> void setImageUrl(T imageView, String url, OnLoaderListener listener);


    interface OnLoaderListener {
        void onLoadCompleted(Bitmap bitmap, String url);

        void onLoadErrir(Throwable e);
    }
}
