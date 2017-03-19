package com.havrylyuk.animationexample.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.view.View;
import android.view.ViewManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;


/**
 *
 * Created by Igor Havrylyuk on 19.03.2017.
 */

public class AnimationsUtil {


    private static final long SHOW_DURATION_FINGLE = 300;
    private static final long SHOW_DURATION_SHORT = 1200;

    private static final long JUMP_SHOW_DURATION = 400;
    private static final long JUMP_DURATION_TIME = 1200;
    private static final long JUMP_DURATION_SCALE = 100;


    private static final long JUMP_DURATION_RAY = 1500;

    private static final long REWARD_TODAY_DURATION = 800;


    private static int getDisplayHeight(View view) {
        return view.getContext().getResources().getDisplayMetrics().heightPixels;
    }

    private static int getDisplayWight(View view) {
        return view.getResources().getDisplayMetrics().widthPixels;
    }

    private static AnimatorListenerAdapter baseAnimationListener(final View animatedObject) {
        return new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                animatedObject.setLayerType(View.LAYER_TYPE_HARDWARE, null);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                animatedObject.setLayerType(View.LAYER_TYPE_NONE, null);
           }
        };
    }

    public static AnimatorSet jumpOne(final View view) {
        AnimatorSet downSet = new AnimatorSet();
        downSet.setDuration(JUMP_DURATION_SCALE)
                .playTogether(ObjectAnimator.ofFloat(view, View.SCALE_Y, 1.0f, 0.9f));
        AnimatorSet decompressSet = new AnimatorSet();
        decompressSet.setDuration(JUMP_DURATION_SCALE)
                .playTogether(ObjectAnimator.ofFloat(view, View.SCALE_Y, 0.9f, 1.0f));
        AnimatorSet toNormalSet = new AnimatorSet();
        toNormalSet.setDuration(JUMP_DURATION_TIME)
                .playTogether(ObjectAnimator.ofFloat(view, View.SCALE_Y, 1.0f, 1.0f));
        AnimatorSet moveUpSet = new AnimatorSet();
        moveUpSet.setDuration(JUMP_SHOW_DURATION)
                .playTogether(ObjectAnimator.ofFloat(view, "translationY", -8f));
        AnimatorSet moveNormalSet = new AnimatorSet();
        moveNormalSet.setDuration(JUMP_SHOW_DURATION)
                .playTogether(ObjectAnimator.ofFloat(view, "translationY", 8f));
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.addListener(baseAnimationListener(view));
        animatorSet.playSequentially(downSet, decompressSet, moveUpSet, moveNormalSet, toNormalSet);
        animatorSet.start();
        return animatorSet;
    }


    public static void starTodayRewardAnimation(Activity activity, AnimatedImage animatedImage) {
        AnimatedImage[] animatedImages = new AnimatedImage[10];
        for (int i = 0; i < animatedImages.length; i++) {
            animatedImages[i] = animatedImage.getClone();
            activity.addContentView(animatedImages[i], animatedImages[i].getLayoutParams());
        }
        for (int i=0; i<animatedImages.length; i++ ) {
            AnimationsUtil.coinsAnimation(animatedImages[i], i * 100, 60);
        }
    }

    private static void coinsAnimation(final AnimatedImage view, final int delay ,
                                       final int radius ) {
        double angleInDegrees = delay * 0.36;
        float x = (float) (radius * Math.cos(angleInDegrees * Math.PI / 180F))
                + getDisplayWight(view)/2 ;
        float y = (float) (radius * Math.sin(angleInDegrees * Math.PI / 180F))
                + getDisplayHeight(view)/2;

        final AnimatorSet setMoveUp = new AnimatorSet();
        setMoveUp.setDuration(REWARD_TODAY_DURATION).playTogether(
                ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, y, 0),
                ObjectAnimator.ofFloat(view, View.TRANSLATION_X, x, getDisplayWight(view))
        );
        setMoveUp.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                view.setLayerType(View.LAYER_TYPE_HARDWARE, null);
            }
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setVisibility(View.GONE);
                view.setImageDrawable(null);
                view.setLayerType(View.LAYER_TYPE_NONE, null);
                ((ViewManager)view.getParent()).removeView(view);//delete view
            }
        });
        setMoveUp.setStartDelay(delay + REWARD_TODAY_DURATION);
        AnimatorSet setExpand = new AnimatorSet();
        setExpand.setDuration(REWARD_TODAY_DURATION).playTogether(
                ObjectAnimator.ofFloat(view, View.ALPHA, 0.0f, 1.0f),
                ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, getDisplayHeight(view)/2, y),
                ObjectAnimator.ofFloat(view, View.TRANSLATION_X, getDisplayWight(view)/2, x)
        );
        setExpand.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                view.setVisibility(View.VISIBLE);
                view.setLayerType(View.LAYER_TYPE_HARDWARE, null);
            }
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setLayerType(View.LAYER_TYPE_NONE, null);
                setMoveUp.start();
            }
        });
        setExpand.start();
    }

    public static void animateRayLightLeft(final View imageView ) {
            final AnimatorSet set = new AnimatorSet();
            AnimatorSet moveLeftSet = new AnimatorSet(); //move left
            ObjectAnimator move = ObjectAnimator.ofFloat(imageView, View.TRANSLATION_X, -32.0f, 122);
            move.setRepeatCount(ValueAnimator.INFINITE);
            ObjectAnimator hide = ObjectAnimator.ofFloat(imageView, View.ALPHA, 1.0f, 0.3f);//hide
            hide.setRepeatCount(ValueAnimator.INFINITE);
            moveLeftSet.setDuration(JUMP_DURATION_RAY).playTogether(move, hide);
            set.playSequentially(moveLeftSet);
            set.addListener(baseAnimationListener(imageView));
            set.start();
    }

    public static void rotateAnim(final View view) {
        ObjectAnimator rotateAnimator = ObjectAnimator.ofFloat(view, "rotation", 0f, 360f);
        rotateAnimator.setDuration(JUMP_DURATION_TIME); // milliseconds
        rotateAnimator.setInterpolator(new LinearInterpolator());
        rotateAnimator.setRepeatCount(ValueAnimator.INFINITE);
        AnimatorSet syncAnimation = new AnimatorSet();
        syncAnimation.playSequentially(rotateAnimator);
        syncAnimation.addListener(baseAnimationListener(view));
        syncAnimation.start();
    }



    public static void animFireWork(final View imageView) {
        final ScaleAnimation scaleAnim = new ScaleAnimation(0.0f, 2.0f, 0.0f, 2.0f,
                Animation.RELATIVE_TO_PARENT, 0.5f, Animation.RELATIVE_TO_PARENT, 0.5f);
        scaleAnim.setRepeatCount(2);
        scaleAnim.setDuration(SHOW_DURATION_SHORT);
        scaleAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                imageView.setVisibility(View.VISIBLE);
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                imageView.setVisibility(View.GONE);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        imageView.startAnimation(scaleAnim);
    }

    public static void animateFingle(final ImageView imageView) {
        final ObjectAnimator scalePulse =
                ObjectAnimator.ofPropertyValuesHolder(imageView,
                PropertyValuesHolder.ofFloat("scaleX", 0.8f),
                PropertyValuesHolder.ofFloat("scaleY", 0.8f));
        scalePulse.setRepeatCount(ValueAnimator.INFINITE);
        scalePulse.setDuration(SHOW_DURATION_FINGLE);
        scalePulse.setRepeatMode(ObjectAnimator.REVERSE);
        scalePulse.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                imageView.setVisibility(View.VISIBLE);
                imageView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
            }
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                imageView.setVisibility(View.GONE);
                imageView.setLayerType(View.LAYER_TYPE_NONE, null);
            }
        });
        AnimatorSet animFingle = new AnimatorSet();
        animFingle.playTogether(scalePulse);
        animFingle.start();
    }
}
