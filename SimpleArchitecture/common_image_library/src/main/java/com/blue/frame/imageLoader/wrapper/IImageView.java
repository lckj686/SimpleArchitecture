package com.blue.frame.imageLoader.wrapper;

/**
 * Created by sucer on 2016/4/17.
 */
public interface IImageView {
    CustOption getCustOption();

    void setCustOption(CustOption option);
    void setCustUrl(String url);
//    void setCommonUrl(String url, IImageLoader.OnLoaderListener listener);
}
