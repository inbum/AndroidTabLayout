package com.inbum.tablayoutexample;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<TabModel> tabData;
    private Context context;

    public SampleFragmentPagerAdapter(FragmentManager fm, Context context, List<TabModel> tabDatas){
        super(fm);
        this.context = context;
        this.tabData = tabDatas;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabData.get(position).getTabTitle();
    }

    @Override
    public Fragment getItem(int position) {
        return TabContentFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return tabData.size();
    }
}
