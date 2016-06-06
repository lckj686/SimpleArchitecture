package com.blue.frame.imageLoader.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 * Created by liw on 2016/5/31.
 */
public class GlideRoundTransform extends BitmapTransformation {
    private static float radius = 0f;
    private int width;
    private int height;

    public GlideRoundTransform(Context context) {
        this(context, 0);
    }

    public GlideRoundTransform(Context context, int px) {
        super(context);
//        this.radius = Resources.getSystem().getDisplayMetrics().density * dp;
        radius = px;
    }

    public GlideRoundTransform(Context context, int px, int w, int h) {
        super(context);
//        this.radius = Resources.getSystem().getDisplayMetrics().density * dp;
        radius = px;
        width = w;
        height = h;
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return roundCrop(pool, toTransform);
    }

    private  Bitmap roundCrop(BitmapPool pool, Bitmap source) {
        if (source == null)
            return null;

        Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        if (result == null) {
            result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        RectF rectF = null;
        if (width != 0 && height != 0){
            rectF = new RectF(0f, 0f, width, height);
        }else{
            rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
        }


        Log.d("sorce", "sW= " + source.getWidth() + " sH =" + source.getHeight());
        canvas.drawRoundRect(rectF, radius, radius, paint);
        return result;
    }

    @Override
    public String getId() {
        return getClass().getName() + Math.round(radius);
    }
}