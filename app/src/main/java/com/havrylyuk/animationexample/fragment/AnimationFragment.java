package com.havrylyuk.animationexample.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.havrylyuk.animationexample.animation.AnimatedImage;
import com.havrylyuk.animationexample.animation.AnimationsUtil;
import com.havrylyuk.animationexample.R;

/**
 *
 * Created by Igor Havrylyuk on 19.03.2017.
 */

public class AnimationFragment extends Fragment {


    private static final String ARG_ITEM_ID = "ARG_ITEM_ID";
    private AnimationType animType;
    private AnimatedImage animateView;
    private FrameLayout buttonLayout;
    private ImageView rayLayout;

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
        prepareAnimations();
        return rootView;
    }

    private void initUI(View rootView) {
        animateView = (AnimatedImage) rootView.findViewById(R.id.animate_image);
        buttonLayout = (FrameLayout) rootView.findViewById(R.id.get_it_layout);
        rayLayout = (ImageView) rootView.findViewById(R.id.blink_image);
        if (buttonLayout != null) {
            buttonLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "Thank you for your purchase", Toast.LENGTH_SHORT).show();
                }
            });
        }
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

    private void prepareAnimations() {
        switch (animType) {
            case MOVE_COINS:
                animateView.setImageResource(R.drawable.icon_coin);
                break;
            case FIREWORK:
                animateView.setImageResource(R.drawable.image_fireworks);
                animateView.setVisibility(View.GONE);
                break;
            case ROTATE:
                animateView.setImageResource(R.drawable.icon_sync);
                break;
            case JUMPING:
                animateView.setImageResource(R.drawable.icon_heart);
                break;
            case PULSE:
                animateView.setImageResource(R.drawable.icon_fingle);
                break;
            case PURCHASE:
                buttonLayout.setVisibility(View.VISIBLE);
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
            case PURCHASE:
                if (rayLayout != null) rayLayout.setVisibility(View.VISIBLE);
                AnimationsUtil.animateRayLightLeft(rayLayout);
                break;
        }
    }

    public enum AnimationType {

        MOVE_COINS("Coins"),
        ROTATE("Rotate"),
        JUMPING("Jumping"),
        FIREWORK("Fireworks"),
        PULSE("Pulse"),
        PURCHASE("Purchase");

        private String name;

        AnimationType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

    }
}