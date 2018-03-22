package com.ltg263.happy_dzh.utils;

import android.app.Application;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.widget.ImageView;


import com.ltg263.happy_dzh.R;
import com.squareup.picasso.Picasso;

import java.io.File;

/**
 * Created by Liyeyu on 2016/7/19.
 */
public class ImageLoader {

    public static int DEF_W = 400;
    public static int DEF_H = 400;
    private static Picasso picasso;
    private static ImageLoader loader = new ImageLoader();
    private static Application mContext;
    private int DEF_IMG = R.drawable.ic_my_head_portrait;

    private ImageLoader() {
    }

    public static ImageLoader get(){
        return loader;
    }

    public static void init(Application context){
        mContext = context;
        picasso = Picasso.with(context);
    }

    public void load(ImageView view, int res){
        picasso.load(res)
                .resize(DEF_W,DEF_H)
                .into(view);
    }
    public void load(ImageView view, Uri path){
        picasso.load(path)
                .resize(DEF_W,DEF_H)
                .into(view);
    }
    public void load(ImageView view, String path){
        picasso.load(path)
                .resize(DEF_W,DEF_H)
                .error(DEF_IMG)
                .placeholder(DEF_IMG)
                .into(view);
    }
    public void load(ImageView view, File file){
        picasso.load(file)
                .resize(DEF_W,DEF_H)
                .into(view);
    }
    public static int calculateInSampleSize(BitmapFactory.Options options,
                                             int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            while ((height / inSampleSize) >= reqHeight
                    && (width / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }
}
