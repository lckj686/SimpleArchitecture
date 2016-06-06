package com.blue.frame.imageLoader.cust;

import android.content.Context;

import com.blue.frame.imageLoader.fresco.ConvertFresso;
import com.blue.frame.imageLoader.wrapper.CustOption;
import com.blue.frame.imageLoader.wrapper.IConvert;

import java.io.File;

/**
 * Description:
 * Created by liw on 2016/4/26.
 */
public class CustImageLoader implements IConvert<CustImageView> {
    private IConvert<CustImageView> convert = new ConvertFresso<>();

    static CustImageLoader custImageLoader;

    static public synchronized CustImageLoader getInstance(){
        if (custImageLoader == null){
            custImageLoader = new CustImageLoader();

        }
        return custImageLoader;
    }


    @Override
    public void init(Context context) {

        convert.init(context);
    }

    @Override
    public void setImageUrl(CustImageView imageView, String url) {
        convert.setImageUrl(imageView, url);
    }

    @Override
    public void setImageUrl(CustImageView imageView, CustOption option, File file) {
        convert.setImageUrl(imageView, option, file);
    }

    @Override
    public void setImageUrl(CustImageView imageView, CustOption option, String url) {
        convert.setImageUrl(imageView, option, url);
    }

    @Override
    public void setImageOption(CustImageView imageView, CustOption option) {
        convert.setImageOption(imageView, option);
    }

    @Override
    public void clearImage(File oldFile) {
        convert.clearImage(oldFile);
    }

}
