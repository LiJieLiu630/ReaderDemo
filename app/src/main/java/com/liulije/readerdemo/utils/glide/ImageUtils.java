package com.liulije.readerdemo.utils.glide;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.liulije.readerdemo.base.MyReaderApplication;

/**
 * @类名称: CLASS
 * @类描述:
 * @创建人：LiuL1Jie
 * @创建时间：2017/8/9 17:19
 * @备注：
 */
public class ImageUtils {
    public static void setImageResource(ImageView viewId, int imgResId) {
        viewId.setImageResource(imgResId);
    }


    public static void setImageDrawable(ImageView viewId, Drawable drawable) {
        viewId.setImageDrawable(drawable);

    }


    public static void setImageUrl(ImageView viewId, String imgUrl) {
        Glide.with(MyReaderApplication.getContext()).load(imgUrl).into(viewId);

    }

    public static void setImageUrl(ImageView viewId, String imgUrl, int placeHolderRes) {
        Glide.with(MyReaderApplication.getContext()).load(imgUrl).placeholder(placeHolderRes).into(viewId);

    }

    public static void setCircleImageUrl(ImageView viewId, String imgUrl, int placeHolderRes) {

        Glide.with(MyReaderApplication.getContext()).load(imgUrl).placeholder(placeHolderRes)
                .transform(new GlideCircleTransform(MyReaderApplication.getContext())).into(viewId);

    }

    public static void setRoundImageUrl(ImageView viewId, String imgUrl, int placeHolderRes) {

        Glide.with(MyReaderApplication.getContext()).load(imgUrl).placeholder(placeHolderRes)
                .transform(new GlideRoundTransform(MyReaderApplication.getContext())).into(viewId);

    }

    public static void setImageBitmap(ImageView viewId, Bitmap imgBitmap) {
        viewId.setImageBitmap(imgBitmap);

    }
}
