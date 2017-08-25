package com.liulije.readerdemo.book;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.liulije.readerdemo.R;
import com.liulije.readerdemo.base.BaseFragment;
import com.liulije.readerdemo.book.activity.FilterCategory_Book_Activity;
import com.liulije.readerdemo.book.adapter.BookListAdapter;
import com.liulije.readerdemo.book.adapter.CategoryFragmentAdapter;
import com.liulije.readerdemo.book.bean.BookListBean;
import com.liulije.readerdemo.book.bean.BookListParams;
import com.liulije.readerdemo.book.bean.CategoryList;
import com.liulije.readerdemo.book.bean.CategoryListLv2;
import com.liulije.readerdemo.book.presenter.Presenter_BookListImpl;
import com.liulije.readerdemo.book.presenter.Presenter_CategoryFragmentImpl;
import com.liulije.readerdemo.book.view.View_BookList;
import com.liulije.readerdemo.book.view.View_CategoryFragment;
import com.liulije.readerdemo.listener.rvItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @类名称: CLASS
 * @类描述:分类
 * @创建人：LiuL1Jie
 * @创建时间：2017/8/9 13:57
 * @备注：
 */
public class CategoryFragment extends BaseFragment<View_CategoryFragment, Presenter_CategoryFragmentImpl> implements View_CategoryFragment {
    private CategoryFragmentAdapter mAdapter;
    private List<CategoryList.MaleBean> mList = new ArrayList<>();

    private SwipeRefreshLayout swipeRefreshLayout;
    private XRecyclerView moreRecyclerVIew;


    @Override
    protected void findViewByID(View loadViewLayout) {
        swipeRefreshLayout = (SwipeRefreshLayout) loadViewLayout.findViewById(R.id.srl_refresh);
        moreRecyclerVIew = (XRecyclerView) loadViewLayout.findViewById(R.id.lmrv_book_list);
    }

    @Override
    protected Presenter_CategoryFragmentImpl createPresenter() {
        return new Presenter_CategoryFragmentImpl(this);
    }

    private static final String TAG = "CategoryFragment";

    @Override
    protected void setListener() {
        moreRecyclerVIew.setPullRefreshEnabled(false);
        moreRecyclerVIew.addOnItemTouchListener(new rvItemClickListener(moreRecyclerVIew, new rvItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), FilterCategory_Book_Activity.class);
                intent.putExtra("FILTER_LIST", mList.get(position - 1));
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));
    }

    @Override
    protected View loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    /**
     */
    @Override
    protected void processLogic() {
        presenter.getCategoryList();
        moreRecyclerVIew.setHasFixedSize(true);
        moreRecyclerVIew.setLayoutManager(new LinearLayoutManager(getContext()));//这里用线性显示 类似于listview

    }


    @Override
    protected void onVisible() {


    }


    @Override
    public void getSuccess(CategoryList list) {

        if (list.getFemale() != null && list.getFemale().size() > 0) {
            for (int i = 0; i < list.getFemale().size(); i++) {
                list.getFemale().get(i).setFemal("female");
            }
            mList.addAll(list.getFemale());
        }
        if (list.getMale() != null && list.getMale().size() > 0) {
            for (int i = 0; i < list.getMale().size(); i++) {
                list.getMale().get(i).setFemal("male");
            }
            list.getMale().get(0).setFirstMale(true);
            mList.addAll(list.getMale());
        }
        if (mAdapter == null) {
            mAdapter = new CategoryFragmentAdapter(mList, getActivity());
            moreRecyclerVIew.setAdapter(mAdapter);
        }
        presenter.getCategoryListLv2();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void getL2Success(CategoryListLv2 categoryListLv2) {
        if (categoryListLv2 != null) {
            for (int i = 0; i < categoryListLv2.getFemale().size(); i++) {
                mList.get(i).setTags(categoryListLv2.getFemale().get(i).mins);
            }
            for (int i = 0; i < categoryListLv2.getMale().size(); i++) {
                mList.get(categoryListLv2.getFemale().size() - 1)
                        .setTags(categoryListLv2.getMale().get(i).mins);
            }
        }
    }
}