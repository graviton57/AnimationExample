package com.havrylyuk.animationexample.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.havrylyuk.animationexample.fragment.AnimationFragment;
import com.havrylyuk.animationexample.model.AnimationItem;

import java.util.List;


/**
 *
 * Created by Igor Havrylyuk on 19.03.2017.
 */

public class AnimationsAdapter extends FragmentStatePagerAdapter {

    private List<AnimationItem> itemList;

    public AnimationsAdapter(FragmentManager fm, List<AnimationItem> animations) {
        super(fm);
        this.itemList = animations;
    }

    public void addItem(AnimationItem item) {
        itemList.add(item);
        notifyDataSetChanged();
    }

    public void addItems(List<AnimationItem> data) {
        itemList.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return itemList.get(position).getName();
    }

    @Override
    public Fragment getItem(int position) {
        return AnimationFragment.getInstance(itemList.get(position).getAnimType().ordinal());
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