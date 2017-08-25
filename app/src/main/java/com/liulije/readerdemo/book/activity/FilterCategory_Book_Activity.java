package com.liulije.readerdemo.book.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.liulije.readerdemo.R;
import com.liulije.readerdemo.base.BaseActivity;
import com.liulije.readerdemo.book.adapter.FilterBookListAdapter;
import com.liulije.readerdemo.book.adapter.SubjectBookDetailAdapter;
import com.liulije.readerdemo.book.bean.BookDetailBean;
import com.liulije.readerdemo.book.bean.BooksByFilter;
import com.liulije.readerdemo.book.bean.CategoryList;
import com.liulije.readerdemo.book.presenter.Presenter_FilterCBookImpl;
import com.liulije.readerdemo.book.view.View_FilterCBook;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class FilterCategory_Book_Activity extends BaseActivity<View_FilterCBook, Presenter_FilterCBookImpl> implements View_FilterCBook {
    XRecyclerView xrv_list_book;
    TagFlowLayout flowLayout;
    TagFlowLayout flowLayout2;
    private String[] mValues;
    //hot(热门)、new(新书)、reputation(好评)、over(完结)
    private String[] mValues2 = {"热门", "新书", "好评", "完结"};
    TextView tvTitle;
    List<BookDetailBean> mList = new ArrayList<>();
    private String gender, type, major, minor;
    private int start = 0;
    private FilterBookListAdapter mAdapter;
    private boolean refreshMore;
    private boolean loadMore;

    private int total;

    @Override
    protected Presenter_FilterCBookImpl initPresenter() {
        return new Presenter_FilterCBookImpl();
    }

    @Override
    protected void loadViewLayout(Bundle savedInstanceState) {
        setContentView(R.layout.activity_filter_category_book);
    }

    @Override
    protected void findViewById() {
        flowLayout = (TagFlowLayout) findViewById(R.id.id_flowlayout);
        flowLayout2 = (TagFlowLayout) findViewById(R.id.id_flowlayout2);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        xrv_list_book = (XRecyclerView) findViewById(R.id.xrv_list_book);
    }

    @Override
    protected void setListener() {
        xrv_list_book.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                if (!loadMore) {
                    refreshMore = true;
                    start = 0;
                    presenter.getBooksByFilter(gender, type, major, minor, start);
                } else {
                    xrv_list_book.refreshComplete();
                }
            }

            @Override
            public void onLoadMore() {
                if (!refreshMore) {
                    if (mList.size() != total) {
                        loadMore = true;
                        presenter.getBooksByFilter(gender, type, major, minor, start);
                    } else
                        xrv_list_book.loadMoreComplete();
                } else
                    xrv_list_book.loadMoreComplete();
            }
        });
        flowLayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {

                Iterator<Integer> result = selectPosSet.iterator();
                if (result.hasNext()) {
                    Integer resu = result.next();
                    Log.i(TAG, "onSelected: " + resu.toString());
                    minor = mValues[resu];
                    start = 0;
                    presenter.getBooksByFilter(gender, type, major, minor, start);
                }
            }
        });

        flowLayout2.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                Iterator<Integer> result = selectPosSet.iterator();
                if (result.hasNext()) {
                    Integer resu = result.next();
                    Log.i(TAG, "onSelected: " + resu.toString());
                    if ("0".equals(resu)) {
                        minor = "hot";
                    } else if ("1".equals(resu)) {
                        minor = "new";
                    } else if ("2".equals(resu)) {
                        minor = "reputation";
                    } else {
                        minor = "over";
                    }
                    start = 0;
                    presenter.getBooksByFilter(gender, type, major, minor, start);
                }
            }
        });
    }

    private static final String TAG = "FilterCategory_Book_Activity";

    @Override
    protected void processLogic() {
        CategoryList.MaleBean bean = (CategoryList.MaleBean) getIntent().getSerializableExtra("FILTER_LIST");
        total = bean.getBookCount();
        gender = bean.isFemal();
        type = "hot";
        minor = "";
        major = bean.getName();
        tvTitle.setText(bean.getName());
        setFlexData(bean);
        xrv_list_book.setLayoutManager(new LinearLayoutManager(this));
        xrv_list_book.setHasFixedSize(true);
        presenter.getBooksByFilter(gender, type, major, minor, start);
    }

    /**
     * 设置筛选数据
     *
     * @param bean
     */
    private void setFlexData(CategoryList.MaleBean bean) {
        if (bean.getTags() != null && bean.getTags().size() > 0) {
            mValues = new String[bean.getTags().size() + 1];
            mValues[0] = "全部";
            Log.e(TAG, "processLogic: " + bean.getTags().size());
            for (int i = 0; i < bean.getTags().size(); i++) {
                mValues[i + 1] = bean.getTags().get(i);
            }
            final TagAdapter<String> adapter = new TagAdapter<String>(mValues) {
                @Override
                public View getView(FlowLayout parent, int position, String s) {
                    TextView tv = (TextView) LayoutInflater
                            .from(FilterCategory_Book_Activity.this)
                            .inflate(R.layout.item_flew_tv, null);
                    tv.setText(s);
                    return tv;
                }
            };
            flowLayout.setAdapter(adapter);
        } else {
            flowLayout.setVisibility(View.GONE);
        }


        flowLayout2.setAdapter(new TagAdapter<String>(mValues2) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) LayoutInflater
                        .from(FilterCategory_Book_Activity.this)
                        .inflate(R.layout.item_flew_tv, null);
                tv.setText(s);
                return tv;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            default:

                break;
        }
    }

    @Override
    public void getFilterSuccess(BooksByFilter booksByFilter) {
        if (booksByFilter != null) {
            if (booksByFilter.getBooks() != null && booksByFilter.getBooks().size() > 0) {
                total = booksByFilter.getTotal();
                if (loadMore) {
                    mList.addAll(booksByFilter.getBooks());
                } else {
                    mList.clear();
                    mList.addAll(booksByFilter.getBooks());
                }
                start += booksByFilter.getBooks().size();
                if (mAdapter == null) {
                    mAdapter = new FilterBookListAdapter(mList, FilterCategory_Book_Activity.this);
                    xrv_list_book.setAdapter(mAdapter);
                }
                mAdapter.notifyDataSetChanged();
                if (refreshMore) {
                    refreshMore = false;
                    xrv_list_book.refreshComplete();
                }
                if (loadMore) {
                    loadMore = false;
                    xrv_list_book.loadMoreComplete();
                    xrv_list_book.refreshComplete();
                }

            }
        }
    }
}
