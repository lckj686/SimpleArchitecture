package com.blue.frame.imageLoader.wrapper;

import java.io.File;

/**
 * Created by sucer on 2016/4/17.
 */
public interface IImageView {
    CustOption getCustOption();

    void setCustOption(CustOption option);
    void setCustUrl(String url);
    void setCustUrl(File file);
//    void setCommonUrl(String url, IImageLoader.OnLoaderListener listener);
}
