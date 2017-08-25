package com.liulije.readerdemo.book;

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
import com.liulije.readerdemo.book.adapter.BookListAdapter;
import com.liulije.readerdemo.book.bean.BookListBean;
import com.liulije.readerdemo.book.bean.BookListParams;
import com.liulije.readerdemo.book.presenter.Presenter_BookListImpl;
import com.liulije.readerdemo.book.view.View_BookList;

import java.util.ArrayList;
import java.util.List;

/**
 * @类名称: CLASS
 * @类描述:主题书单界面
 * @创建人：LiuL1Jie
 * @创建时间：2017/8/9 13:57
 * @备注：
 */
public class SubjectFragment extends BaseFragment<View_BookList, Presenter_BookListImpl> implements View_BookList {
    private int start;
    private BookListAdapter mAdapter;
    private List<BookListBean> mList = new ArrayList<>();

    private SwipeRefreshLayout swipeRefreshLayout;
    private XRecyclerView moreRecyclerVIew;


    private boolean refreshMore;
    private boolean loadMore;

    private int total;
    private BookListParams params;
    Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void findViewByID(View loadViewLayout) {
        swipeRefreshLayout = (SwipeRefreshLayout) loadViewLayout.findViewById(R.id.srl_refresh);
        moreRecyclerVIew = (XRecyclerView) loadViewLayout.findViewById(R.id.lmrv_book_list);
    }

    @Override
    protected Presenter_BookListImpl createPresenter() {
        return new Presenter_BookListImpl(this);
    }

    @Override
    protected void setListener() {
//        moreRecyclerVIew.setPullRefreshEnabled(false);

        moreRecyclerVIew.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                if (!loadMore) {
                        refreshMore = true;
                        start = 0;
                        // ;//刷新不传time值
                        presenter.getBookList(params);

                } else {
                    moreRecyclerVIew.refreshComplete();
                    return;
                }
            }

            @Override
            public void onLoadMore() {
                if (!refreshMore) {
                    if (total != mList.size()) {
                        loadMore = true;
                        presenter.getBookList(params);
                    } else {
                        moreRecyclerVIew.loadMoreComplete();
                    }
                } else
                    moreRecyclerVIew.loadMoreComplete();
            }
        });
    }

    @Override
    protected View loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_subject, container, false);
    }

    /**
     * http://api.zhuishushenqi.com/book-list?duration=last-seven-days&sort=collectorCount&start=0&limit=20&gender=female
     */
    @Override
    protected void processLogic() {
        params = (BookListParams) getArguments().getSerializable("params");
        Log.w("dd", "processLogic: " + params.toString());
        presenter.getBookList(params);
//
        start = Integer.parseInt(params.getStart());

        moreRecyclerVIew.setHasFixedSize(true);
        moreRecyclerVIew.setLayoutManager(new LinearLayoutManager(getContext()));//这里用线性显示 类似于listview

    }


    @Override
    protected void onVisible() {


    }

    /**
     * 获取数据成功
     *
     * @param mlist
     */
    @Override
    public void getBookList(BookListBean mlist) {
        if (mlist != null && mlist.getBookLists().size() > 0) {
            total = mlist.getTotal();
            if (loadMore) {
                mList.addAll(mlist.getBookLists());
            } else {
                mList.clear();
                mList.addAll(mlist.getBookLists());
            }
            if (mAdapter == null) {
                mAdapter = new BookListAdapter(mList, getActivity());
                moreRecyclerVIew.setAdapter(mAdapter);
            }
            for (BookListBean bookListBean : mList) {
                Log.w("ll", "getBookList: " + bookListBean.toString());
            }
            mAdapter.notifyDataSetChanged();
            start = mList.size();
            params.setStart(start + "");
            if (refreshMore) {
                refreshMore = false;
                moreRecyclerVIew.refreshComplete();
            }
            if (loadMore) {
                loadMore = false;
                moreRecyclerVIew.loadMoreComplete();
            }
        }
    }

    @Override
    public void getBookListFailure() {
        if (refreshMore) {
            refreshMore = false;
            moreRecyclerVIew.refreshComplete();
        }
        if (loadMore) {
            loadMore = false;
            moreRecyclerVIew.loadMoreComplete();
        }
    }
}