package com.blue.frame.imageLoader.glide;

import android.content.Context;
import android.widget.ImageView;

import com.blue.frame.imageLoader.wrapper.CustOption;
import com.blue.frame.imageLoader.wrapper.IConvert;
import com.bumptech.glide.Glide;

import java.io.File;


/**
 * Description:
 * Created by liw on 2016/4/26.
 */
public class ConvertGlide<T extends ImageView> implements IConvert<T> {


    @Override
    public void init(Context context) {

    }

    @Override
    public void setImageUrl(T imageView, String url) {
        setImageUrl(imageView, null, url);
    }

    @Override
    public void setImageUrl(T imageView, CustOption option, File file) {
        if (option != null && option.corner != null && option.corner > 0) {
            float val = option.corner;
            Glide.with(imageView.getContext())
                    .load(file)
                    .placeholder(option.defaultRes)
//                    .transform(new GlideRoundTransform(imageView.getContext(), (int) val))
                    .crossFade(0)
                    .into(imageView);
        } else if (option != null && option.isRound != null && option.isRound == true) {

        } else {
            Glide.with(imageView.getContext())
                    .load(file)
                    .placeholder(option.defaultRes)
                    .crossFade(0)
                    .into(imageView);
        }
    }

    @Override
    public void setImageUrl(T imageView, CustOption option, String url) {
        if (option != null && option.corner != null && option.corner > 0) {
            float val = option.corner;

//            imageView.setCornerRadius((float) val);

//            if (imageView.getWidth() != 0){
//                Glide.with(imageView.getContext())
//                        .load(url)
//                        .placeholder(option.defaultRes)
//                        .centerCrop()
//                        .transform(new GlideRoundTransform(imageView.getContext(), (int) val, imageView.getWidth(), imageView.getHeight()))
//                        .into(imageView);
//            }else {

                Glide.with(imageView.getContext())
                        .load(url)
                        .centerCrop()
                        .placeholder(option.defaultRes)
                        .transform(new GlideRoundTransform(imageView.getContext(), (int) val))
                        .into(imageView);

//            }

        } else if (option != null && option.isRound != null && option.isRound == true) {

        } else {
            Glide.with(imageView.getContext())
                    .load(url)
                    .placeholder(option.defaultRes)
                    .into(imageView);
        }


    }

    @Override
    public void setImageOption(T imageView, CustOption option) {
        Glide.with(imageView.getContext())
                .load("")
                .centerCrop()
                .placeholder(option.defaultRes)
//                .crossFade()
                .into(imageView);


    }

    @Override
    public void clearImage(File oldFile) {

    }
}
