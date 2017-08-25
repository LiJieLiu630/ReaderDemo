package com.liulije.readerdemo.book.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * @类名称: CLASS
 * @类描述:
 * @创建人：刘丽杰
 * @创建时间：2017/8/9 14:02
 * @备注：
 */
public class MyFragmentAdapter extends FragmentPagerAdapter {
    private List<String> mTitles;
    private List<?> fragments;

    public MyFragmentAdapter(FragmentManager fm, List<?> mFragments) {
        super(fm);
        this.fragments = mFragments;
    }

    public MyFragmentAdapter(FragmentManager fm, List<String> mTitles, List<?> fragments) {
        super(fm);
        this.mTitles = mTitles;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return (Fragment) fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (mTitles != null && mTitles.size() > 0) {
            return mTitles.get(position);
        } else {
            return "";
        }
    }
}
