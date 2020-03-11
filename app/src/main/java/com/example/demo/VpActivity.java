package com.example.demo;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;

import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;


public class VpActivity extends AppCompatActivity {

    private ArrayList<Fragment> fragments;
    String [] titls = {"index0","index1","index2","index3"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vp);
        ViewPager viewPager = findViewById(R.id.vp);
//        viewPager.setOffscreenPageLimit(2);
        fragments = new ArrayList<>();

        for (int i = 0; i < titls.length; i++) {
            fragments.add(TitleFragment.newInstance(titls[i]));
        }
        MyVpAdapter adapter = new MyVpAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        SlidingTabLayout tabLayout = findViewById(R.id.tl);
        tabLayout.setViewPager(viewPager);

//        viewPager2.setOrientation(ViewPager2.ORIENTATION_VERTICAL);

    }

    private class  MyVpAdapter extends FragmentPagerAdapter{
        FragmentManager fragmentManager;
        public MyVpAdapter(FragmentManager fm) {
            super(fm);
            fragmentManager = fm;
        }

        @Override
        public Fragment getItem(int i) {
            return fragments.get(i);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titls[position];
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            Fragment fragment = (Fragment) super.instantiateItem(container, position);
            fragmentManager.beginTransaction().show(fragment).commitAllowingStateLoss();
            return fragment;
        }
//

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            Fragment fragment = fragments.get(position);
            fragmentManager.beginTransaction().hide(fragment).commitAllowingStateLoss();

        }
    }
}
