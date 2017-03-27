package com.havrylyuk.animationexample.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.havrylyuk.animationexample.fragment.AnimationFragment;
import com.havrylyuk.animationexample.fragment.AnimationFragment.AnimationType;

import java.util.List;


/**
 *
 * Created by Igor Havrylyuk on 19.03.2017.
 */

public class AnimationsAdapter extends FragmentStatePagerAdapter {

    private List<AnimationType> itemList;

    public AnimationsAdapter(FragmentManager fm, List<AnimationType> animations) {
        super(fm);
        this.itemList = animations;
    }

    public void addItem(AnimationType item) {
        itemList.add(item);
        notifyDataSetChanged();
    }

    public void addItems(List<AnimationType> data) {
        itemList.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return itemList.get(position).getName();
    }

    @Override
    public Fragment getItem(int position) {
        return AnimationFragment.getInstance(itemList.get(position).ordinal());
    }

    @Override
    public int getCount() {
        return itemList != null ? itemList.size() : 0;
    }

    public void clear() {
        itemList.clear();
        notifyDataSetChanged();
    }
}