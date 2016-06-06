package com.blue.frame.imageLoader.wrapper;

import android.content.Context;

import java.io.File;

/**
 * Description:
 * Created by liw on 2016/4/26.
 */
public interface IConvert<T> {


    void init(Context context);
    void setImageUrl(T imageView, String url);
    void setImageUrl(T imageView, CustOption option, File file);
    void setImageUrl(T imageView, CustOption option, String url);
    void setImageOption(T imageView, CustOption option);

//    void setImageUrl(T imageView,String url, IImageLoader.OnLoaderListener listener);


     void clearImage(File oldFile);
}