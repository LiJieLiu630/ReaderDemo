package com.liulije.readerdemo.book.activity;

import android.graphics.Color;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.liulije.readerdemo.R;
import com.liulije.readerdemo.base.BaseActivity;
import com.liulije.readerdemo.base.BasePresenter;
import com.liulije.readerdemo.book.adapter.ListViewCollectionAdapter;
import com.liulije.readerdemo.book.bean.BookListBean;
import com.liulije.readerdemo.utils.BookCacheManager;
import com.liulije.readerdemo.utils.MyMenuUtil;

import java.util.ArrayList;
import java.util.List;

import widget.CustomLeftListView;

public class CollectionActivity extends BaseActivity {
    private TextView tvTitle;
    private CustomLeftListView xRecyclerView;
    private ListViewCollectionAdapter mAdapter;
    private List<BookListBean> mList = new ArrayList<>();

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void loadViewLayout(Bundle savedInstanceState) {
        setContentView(R.layout.activity_collection);
    }

    @Override
    protected void findViewById() {
        tvTitle = (TextView) findViewById(R.id.tv_title);
        xRecyclerView = (CustomLeftListView) findViewById(R.id.xrv_collection_subject);
    }

    @Override
    protected void setListener() {
    }

    @Override
    protected void processLogic() {
        intiListview();
        mList = BookCacheManager.getInstance().getBookCollectionList();
        if (mList != null) {
            mAdapter = new ListViewCollectionAdapter(mList, this);
            xRecyclerView.setAdapter(mAdapter);

        } else {
            showToast("暂无收藏");
        }
        tvTitle.setText("我的收藏");

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

    private void intiListview() {
        xRecyclerView.setMenuCreator(MyMenuUtil.addMenusImg(mContext, Color.parseColor("#F3F3F3")));
        xRecyclerView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int pos, SwipeMenu menu, int index) {
                BookCacheManager.getInstance().removeCollection(mList.get(pos).get_id());
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mList.clear();
                        mList.addAll(BookCacheManager.getInstance().getBookCollectionList());
                        if (mList != null && mList.size() > 0) {
                            mAdapter.notifyDataSetChanged();
                        } else {
                            showToast("暂无收藏");
                        }
                    }
                }, 3000);
                return true;
            }
        });
    }
}
