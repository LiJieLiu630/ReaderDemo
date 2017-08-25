package com.liulije.readerdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.liulije.readerdemo.R;
import com.liulije.readerdemo.base.BaseFragment;
import com.liulije.readerdemo.base.BasePresenter;
import com.liulije.readerdemo.base.RxEvent;
import com.liulije.readerdemo.bean.CollectionBookBean;
import com.liulije.readerdemo.book.activity.ReadBookActivity;
import com.liulije.readerdemo.fragment.adapter.BookShiftAdapter;
import com.liulije.readerdemo.listener.rvItemClickListener;
import com.liulije.readerdemo.utils.CollectionManager;
import com.liulije.readerdemo.utils.RxBus2;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @类名称: CLASS
 * @类描述:书架fragment
 * @创建人：LiuL1Jie
 * @创建时间：2017/8/9 10:09
 * @备注：
 */
public class OneFragment extends BaseFragment {
    private static final String TAG = "OneFragment";
    private XRecyclerView xrvBookList;
    private RelativeLayout rlEmptyBook;
    private List<CollectionBookBean> mList = new ArrayList<>();
    private BookShiftAdapter adapter;
    private CollectionBookBean emptyAddBook = new CollectionBookBean();


    @Override
    protected void findViewByID(View loadViewLayout) {
        xrvBookList = (XRecyclerView) loadViewLayout.findViewById(R.id.xrv_book_list);
        rlEmptyBook = (RelativeLayout) loadViewLayout.findViewById(R.id.rl_empty_book);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initRxBus2();
    }


    @Override
    protected void setListener() {
        rlEmptyBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxBus2.getDefaultRxBus().post(new RxEvent(2));
            }
        });
        xrvBookList.addOnItemTouchListener(new rvItemClickListener(xrvBookList,
                new rvItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        CollectionBookBean currentBean = mList.get(position - 1);
                        if (currentBean.isEmptyAddMore()) {//添加更多书籍
                            RxBus2.getDefaultRxBus().post(new RxEvent(2));
                        } else {//进入阅读界面
                            ReadBookActivity.startActivity(getActivity(), mList.get(position - 1));
                        }
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {
                        CollectionBookBean currentBean = mList.get(position - 1);
                        if (!currentBean.isEmptyAddMore()) {
                            adapter.isCanDelete = true;
                            adapter.notifyDataSetChanged();
                            xrvBookList.invalidate();
                            Log.e("444444", "onItemLongClick: " + mList.get(position - 1).getTitle());
                        }
                    }
                }));
        xrvBookList.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                deatList();
            }

            @Override
            public void onLoadMore() {

            }
        });
    }

    /**
     * 处理书架数据
     */
    private void deatList() {
        if (mList == null) {
            mList = new ArrayList<>();
        }
        mList.clear();
        mList.addAll(CollectionManager.getInstance().getCollectionListBySort());
        if (mList != null && mList.size() > 0) {
            mList.add(emptyAddBook);
            rlEmptyBook.setVisibility(View.GONE);
            xrvBookList.setVisibility(View.VISIBLE);
            if (adapter == null) {
                adapter = new BookShiftAdapter(mList, getActivity());
                xrvBookList.setAdapter(adapter);
            }
            adapter.notifyDataSetChanged();
        } else {
            rlEmptyBook.setVisibility(View.VISIBLE);
            xrvBookList.setVisibility(View.GONE);
        }
    }

    @Override
    protected View loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_one, container, false);
    }

    @Override
    protected void processLogic() {
        initRecyclerView();
        emptyAddBook.setTitle("更多书籍");
        emptyAddBook.setEmptyAddMore(true);
        mList = CollectionManager.getInstance().getCollectionListBySort();
        if (mList != null && mList.size() > 0) {
            mList.add(emptyAddBook);
            rlEmptyBook.setVisibility(View.GONE);
            xrvBookList.setVisibility(View.VISIBLE);
            adapter = new BookShiftAdapter(mList, getActivity());
            xrvBookList.setAdapter(adapter);
        } else {
            rlEmptyBook.setVisibility(View.VISIBLE);
            xrvBookList.setVisibility(View.GONE);
        }

    }

    private void initRecyclerView() {
        xrvBookList.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        xrvBookList.setHasFixedSize(true);
    }

    @Override
    protected void onVisible() {

    }

    private void initRxBus2() {
        RxBus2.getDefaultRxBus().toObservable1(RxEvent.class).subscribe(new Observer<RxEvent>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(@NonNull RxEvent rxEvent) {
                Log.e(TAG, "onNext: " + rxEvent.getType());
                if (rxEvent.getType() == 1) {
                    deatList();
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e(TAG, "onError: " + e.toString());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != compositeDisposable && !compositeDisposable.isDisposed()) {
            compositeDisposable.clear();
        }
    }

}
