package com.renard.richtext.ig;

import android.widget.TextView;

import com.renard.richtext.ImageHolder;
import com.renard.richtext.RichTextConfig;
import com.renard.richtext.callback.ImageLoadNotify;
import com.renard.richtext.drawable.DrawableWrapper;
import com.renard.richtext.exceptions.ImageDecodeException;
import com.renard.richtext.ext.Base64;

/**
 * Created by zhou on 2016/12/9.
 * Base64格式图片解析器
 */
class Base64ImageLoader extends AbstractImageLoader<byte[]> implements Runnable {

    Base64ImageLoader(ImageHolder holder, RichTextConfig config, TextView textView, DrawableWrapper drawableWrapper, ImageLoadNotify iln) {
        super(holder, config, textView, drawableWrapper, iln, SourceDecode.BASE64_SOURCE_DECODE);
    }

    @Override
    public void run() {
        try {
            byte[] src = Base64.decode(holder.getSource());
            doLoadImage(src);
        } catch (Exception e) {
            onFailure(new ImageDecodeException(e));
        } catch (OutOfMemoryError error) {
            onFailure(new ImageDecodeException(error));
        }
    }

}