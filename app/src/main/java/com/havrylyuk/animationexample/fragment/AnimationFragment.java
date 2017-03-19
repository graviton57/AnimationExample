package com.havrylyuk.animationexample.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.havrylyuk.animationexample.animation.AnimatedImage;
import com.havrylyuk.animationexample.model.AnimationType;
import com.havrylyuk.animationexample.animation.AnimationsUtil;
import com.havrylyuk.animationexample.R;

/**
 *
 * Created by Igor Havrylyuk on 19.03.2017.
 */

public class AnimationFragment extends Fragment {


    private static final String ARG_ITEM_ID = "ARG_ITEM_ID";
    private AnimationType animType;
    private  AnimatedImage animateView;

    public static AnimationFragment getInstance(int itemId) {
        Bundle args = new Bundle();
        args.putInt(ARG_ITEM_ID, itemId);
        AnimationFragment animationFragment = new AnimationFragment();
        animationFragment.setArguments(args);
        return animationFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_animation, container, false);
        if (getArguments() != null) {
            animType = AnimationType.values()[getArguments().getInt(ARG_ITEM_ID)];
        }
        initUI(rootView);
        prepareViews();
        return rootView;
    }

    private void initUI (View rootView) {
        animateView = ( AnimatedImage) rootView.findViewById(R.id.animate_image);
        Button startButton = (Button) rootView.findViewById(R.id.start_button);
        if (startButton != null) {
            startButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (animateView != null) {
                        startAnimation();
                    }
                }
            });
        }
    }

    private void prepareViews() {
        switch (animType) {
            case MOVE_COINS:
                animateView.setImageResource(R.drawable.gold_coin_single);
                break;
            case FIREWORK:
                animateView.setImageResource(R.drawable.image_fireworks);
                animateView.setVisibility(View.GONE);
                break;
            case ROTATE:
                animateView.setImageResource(R.drawable.sync);
                break;
            case JUMPING:
                animateView.setImageResource(R.drawable.heart);
                break;
            case PULSE:
                animateView.setImageResource(R.drawable.icon_fingle);
                break;
        }
    }

    private void startAnimation() {
        switch (animType) {
            case MOVE_COINS:
                AnimationsUtil.starTodayRewardAnimation(getActivity(), animateView);
                break;
            case ROTATE:
                AnimationsUtil.rotateAnim(animateView);
                break;
            case JUMPING:
                 AnimationsUtil.jumpOne(animateView);
                break;
            case FIREWORK:
                AnimationsUtil.animFireWork(animateView);
                break;
            case PULSE:
                AnimationsUtil.animateFingle(animateView);
                break;
        }
    }

}