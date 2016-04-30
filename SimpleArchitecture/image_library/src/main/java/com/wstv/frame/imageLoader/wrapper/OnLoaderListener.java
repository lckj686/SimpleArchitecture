package com.wstv.frame.imageLoader.wrapper;

import android.graphics.Bitmap;

/**
 * Description:
 * Created by liw on 2016/4/28.
 */
public interface OnLoaderListener {
    void onLoadCompleted(Bitmap bitmap, String url);
    void onLoadErrir(Throwable e);
}
