package com.havrylyuk.animationexample;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.havrylyuk.animationexample.adapter.AnimationsAdapter;
import com.havrylyuk.animationexample.model.AnimationType;
import com.havrylyuk.animationexample.model.AnimationItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private AnimationsAdapter animationsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        FragmentManager fragmentManager = getSupportFragmentManager();
        animationsAdapter = new AnimationsAdapter(fragmentManager, new ArrayList<AnimationItem>());
        viewPager.setAdapter(animationsAdapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        populateData();
    }

    private void populateData() {
        ArrayList<AnimationItem> data = new ArrayList<>();
        data.add(new AnimationItem(AnimationType.MOVE_COINS,"Coins"));
        data.add(new AnimationItem(AnimationType.PURCHASE,"Purchase"));
        data.add(new AnimationItem(AnimationType.ROTATE,"Rotate"));
        data.add(new AnimationItem(AnimationType.FIREWORK,"Fireworks"));
        data.add(new AnimationItem(AnimationType.JUMPING,"Jumping"));
        data.add(new AnimationItem(AnimationType.PULSE,"Pulse"));
        if (animationsAdapter != null) {
            animationsAdapter.addItems(data);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_about) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
