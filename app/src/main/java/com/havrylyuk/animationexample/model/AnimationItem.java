package com.havrylyuk.animationexample.model;

/**
 * Created by Igor Havrylyuk on 19.03.2017.
 */

public class AnimationItem {

    private AnimationType animType;
    private String name;

    public AnimationItem(AnimationType animType, String name) {
        this.animType = animType;
        this.name = name;
    }

    public AnimationType getAnimType() {
        return animType;
    }

    public void setAnimType(AnimationType animType) {
        this.animType = animType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
