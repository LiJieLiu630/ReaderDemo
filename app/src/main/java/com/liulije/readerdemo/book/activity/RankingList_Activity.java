package com.liulije.readerdemo.book.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.liulije.readerdemo.R;
import com.liulije.readerdemo.base.BaseActivity;
import com.liulije.readerdemo.listener.rvItemClickListener;
import com.liulije.readerdemo.book.adapter.RankingListAdapter;
import com.liulije.readerdemo.book.bean.Rankings;
import com.liulije.readerdemo.book.presenter.Presenter_RankingListImpl;
import com.liulije.readerdemo.book.view.View_RandingList;

import java.util.ArrayList;
import java.util.List;

public class RankingList_Activity extends BaseActivity<View_RandingList, Presenter_RankingListImpl> implements View_RandingList {
    public List<Rankings.RankingBean.BooksBean> mlist = new ArrayList<>();
    RankingListAdapter madapter;
    private XRecyclerView xevRankinglist;
    TextView tvTilte;

    @Override
    protected Presenter_RankingListImpl initPresenter() {
        return new Presenter_RankingListImpl();
    }

    @Override
    protected void loadViewLayout(Bundle savedInstanceState) {
        setContentView(R.layout.activity_ranking_list_);
    }

    @Override
    protected void findViewById() {
        xevRankinglist = (XRecyclerView) findViewById(R.id.xev_rankinglist);
        tvTilte = (TextView) findViewById(R.id.tv_title);
    }

    @Override
    protected void setListener() {
        xevRankinglist.setPullRefreshEnabled(false);
        xevRankinglist.addOnItemTouchListener(new rvItemClickListener(xevRankinglist, new rvItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.w(TAG, "onItemClick: " + "click");
                Rankings.RankingBean.BooksBean bookListBean = mlist.get(position - 1);
                // 查看书单详情
                Intent intent = new Intent(RankingList_Activity.this, BookDetailActivity.class);
                intent.putExtra("BOOKID", bookListBean.get_id());
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));
    }

    @Override
    protected void processLogic() {
        String rankIngID = getIntent().getStringExtra("RANKINGID");
        tvTilte.setText(getIntent().getStringExtra("TITLE"));
        presenter.getRankBook(rankIngID);
        xevRankinglist.setHasFixedSize(true);
        xevRankinglist.setLayoutManager(new LinearLayoutManager(this));//这里用线性显示 类似于listview
    }

    private static final String TAG = "RankingList_Activity";

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
    public void Success(Rankings rankings) {
        if (rankings != null) {
            if (rankings.getRanking() != null) {
                if (rankings.getRanking().getBooks() != null && rankings.getRanking().getBooks().size() > 0) {
                    mlist = rankings.getRanking().getBooks();
                    if (madapter == null) {
                        madapter = new RankingListAdapter(mlist, this);
                        xevRankinglist.setAdapter(madapter);
                    }
                    madapter.notifyDataSetChanged();
                }
            }
        }
    }

    @Override
    public void failure() {

    }
}
