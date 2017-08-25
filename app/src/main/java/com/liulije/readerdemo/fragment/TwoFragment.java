package com.liulije.readerdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liulije.readerdemo.R;
import com.liulije.readerdemo.base.BaseFragment;
import com.liulije.readerdemo.base.BasePresenter;
import com.liulije.readerdemo.book.CategoryFragment;
import com.liulije.readerdemo.book.RankFragment;
import com.liulije.readerdemo.book.SubjectFragment;
import com.liulije.readerdemo.book.adapter.MyFragmentAdapter;
import com.liulije.readerdemo.book.bean.BookListParams;

import java.util.ArrayList;
import java.util.List;

/**
 * @类名称: CLASS
 * @类描述:书单fragment
 * @创建人：刘丽杰
 * @创建时间：2017/8/9 10:09
 * @备注：
 */
public class TwoFragment extends BaseFragment implements ViewPager.OnPageChangeListener {
    private static final String TAG = "TwoFragment";
    private List<String> tabTitles = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();

    private TabLayout tabGank;
    private ViewPager viewPager;

    @Override
    protected void findViewByID(View loadViewLayout) {
        tabGank = (TabLayout) loadViewLayout.findViewById(R.id.tab_gank);
        viewPager = (ViewPager) loadViewLayout.findViewById(R.id.viewpager);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected View loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_two, container, false);

    }

    @Override
    protected void processLogic() {
        prepareData();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MyFragmentAdapter adapter = new MyFragmentAdapter(getChildFragmentManager(), tabTitles, fragments);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);
        tabGank.setTabMode(TabLayout.MODE_FIXED);
        tabGank.setupWithViewPager(viewPager);
        adapter.notifyDataSetChanged();

    }

    /**
     * 初始化数据
     */
    private void prepareData() {
        tabTitles.add("主题书单");
        tabTitles.add("排行榜");
        tabTitles.add("分类");
        SubjectFragment fragment1 = new SubjectFragment();

        BookListParams params = new BookListParams();
        Bundle bundle1 = new Bundle();
        params.setDuration("last-seven-days");
        params.setSort("collectorCount");
        params.setStart("0");
        params.setLimit("");
        params.setGender("female");
        bundle1.putSerializable("params", params);
        fragment1.setArguments(bundle1);
        Log.w(TAG, "prepareData:111 " + params.toString());
        fragments.add(fragment1);

        RankFragment fragment2 = new RankFragment();
        fragments.add(fragment2);

        CategoryFragment fragment3 = new CategoryFragment();
        fragments.add(fragment3);
    }

    @Override
    protected void onVisible() {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.w(TAG, "onPageScrolled: ");
    }

    @Override
    public void onPageSelected(int position) {
        Log.w(TAG, "onPageSelected: ");
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        Log.w(TAG, "onPageScrollStateChanged: ");
    }


}
