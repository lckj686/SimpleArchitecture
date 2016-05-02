package com.blue.frame.imageLoader.cust;

import android.content.Context;

import com.blue.frame.imageLoader.fresco.ConvertFresso;
import com.blue.frame.imageLoader.wrapper.CustOption;
import com.blue.frame.imageLoader.wrapper.IConvert;

/**
 * Description:
 * Created by liw on 2016/4/26.
 */
public class CustImageLoader implements IConvert<CustImageView> {
    private IConvert<CustImageView> convert = new ConvertFresso<CustImageView>();

    static CustImageLoader custImageLoader;

    static public synchronized CustImageLoader getInstance(){
        if (custImageLoader == null){
            custImageLoader = new CustImageLoader();

        }


        return custImageLoader;
    }


//    public void addConverterFactory(IConvert<CustImageView> convert) {
//        this.convert = convert;
//    }

    @Override
    public void init(Context context) {

        convert.init(context);
    }

    @Override
    public void setImageUrl(CustImageView imageView, String url) {
        convert.setImageUrl(imageView, url);
    }

    @Override
    public void setImageUrl(CustImageView imageView, CustOption option, String url) {
        convert.setImageUrl(imageView, option, url);
    }

    @Override
    public void setImageOption(CustImageView imageView, CustOption option) {
        convert.setImageOption(imageView, option);
    }

}
