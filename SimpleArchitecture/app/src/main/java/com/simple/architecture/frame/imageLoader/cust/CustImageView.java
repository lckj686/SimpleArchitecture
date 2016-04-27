package  com.simple.architecture.frame.imageLoader.cust;

import android.content.Context;
import android.util.AttributeSet;

import com.facebook.drawee.view.SimpleDraweeView;
import com.simple.architecture.frame.imageLoader.wrapper.CustOption;
import com.simple.architecture.frame.imageLoader.wrapper.IImageView;

/**
 * Description:纯粹imageview
 * Created by liw on 2016/4/26.
 */
public class CustImageView extends SimpleDraweeView implements IImageView {
    private CustOption option;


    public CustImageView(Context context) {
        super(context);
    }

    public CustImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public CustOption getCustOption() {
        return this.option;
    }

    @Override
    public void setCustOption(CustOption option) {
        this.option = option;
    }

    @Override
    public void setCustUrl(String url) {
        CustImageLoader.getInstance().setImageUrl(this, option, url);


    }

//    @Override
//    public void setCommonUrl(String url, IImageLoader.OnLoaderListener listener) {
//        CustImageLoader.getInstance().setImageUrl(this, url);
//    }
}
