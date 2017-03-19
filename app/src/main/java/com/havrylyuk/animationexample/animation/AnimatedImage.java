package com.havrylyuk.animationexample.animation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 *
 * Created by Igor Havrylyuk on 19.03.2017.
 */

@SuppressLint("AppCompatCustomView")
public class AnimatedImage extends ImageView implements Cloneable {

    public AnimatedImage(Context context) {
        super(context);
    }

    public AnimatedImage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnimatedImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public AnimatedImage getClone() {
        try {
            return (AnimatedImage)this.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        super.clone();
        AnimatedImage obj = new AnimatedImage(this.getContext());
        obj.setImageDrawable(this.getDrawable());
        obj.setScaleType(this.getScaleType());
        try{
            obj.setLayoutParams(this.getLayoutParams());
        }catch(Exception e){
            e.printStackTrace();
        }
        obj.setId((int)(Math.random() * 100));
        return obj;
    }
}
