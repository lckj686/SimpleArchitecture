package com.simple.architecture.frame.imageLoader.cust;

import android.content.Context;

import com.simple.architecture.frame.imageLoader.fresco.ConvertFresso;
import com.simple.architecture.frame.imageLoader.wrapper.CustOption;
import com.simple.architecture.frame.imageLoader.wrapper.IConvert;
import com.simple.architecture.frame.imageLoader.wrapper.IImageLoader;


/**
 * Description:
 * Created by liw on 2016/4/26.
 */
public class CustImageLoader implements IImageLoader<CustImageView> {
    private IConvert<CustImageView> convert = new ConvertFresso<>();

    static CustImageLoader custImageLoader;

    static public synchronized CustImageLoader getInstance() {
        if (custImageLoader == null) {
            custImageLoader = new CustImageLoader();
        }

        return custImageLoader;
    }


    public void addConverterFactory(IConvert<CustImageView> convert) {
        this.convert = convert;
    }

    @Override
    public void init(Context context) {

        convert.init(context);
    }

    @Override
    public void setImageUrl(CustImageView imageView, String url) {
        convert.setImageUrl(imageView, null, url);
    }

    @Override
    public void setImageUrl(CustImageView imageView, CustOption option, String url) {
        convert.setImageUrl(imageView, option, url);
    }

}
