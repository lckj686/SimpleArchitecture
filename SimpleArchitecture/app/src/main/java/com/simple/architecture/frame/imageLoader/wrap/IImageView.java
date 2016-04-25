package com.simple.architecture.frame.imageLoader.wrap;

/**
 * Created by sucer on 2016/4/17.
 */
public interface IImageView {
    SimpleOption getOption();

    void setOption(SimpleOption option);
    void setCommonUrl(String url);
    void setCommonUrl(String url, IImageLoader.OnLoaderListener listener);
}
