package com.liulije.readerdemo.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liulije.readerdemo.R;
import com.liulije.readerdemo.base.BaseFragment;
import com.liulije.readerdemo.base.BasePresenter;

/**
 * @类名称: CLASS
 * @类描述:
 * @创建人：LiuL1Jie
 * @创建时间：2017/8/9 10:09
 * @备注：
 */
public class FourFragment extends BaseFragment {
    @Override
    protected void findViewByID(View loadViewLayout) {

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
        return inflater.inflate(R.layout.fragment_four, container, false);

    }

    @Override
    protected void processLogic() {

    }

    @Override
    protected void onVisible() {

    }
}
