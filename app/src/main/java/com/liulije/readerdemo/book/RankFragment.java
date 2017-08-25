package com.liulije.readerdemo.book;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.liulije.readerdemo.R;
import com.liulije.readerdemo.base.BaseFragment;
import com.liulije.readerdemo.listener.rvItemClickListener;
import com.liulije.readerdemo.book.activity.RankingList_Activity;
import com.liulije.readerdemo.book.adapter.BookRankAdapter;
import com.liulije.readerdemo.book.bean.RankingList;
import com.liulije.readerdemo.book.presenter.Presenter_RankBookImpl;
import com.liulije.readerdemo.book.view.View_RankBook;
import com.liulije.readerdemo.utils.SettingManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @类名称: CLASS
 * @类描述:排行
 * @创建人：刘丽杰
 * @创建时间：2017/8/9 13:57
 * @备注：
 */
public class RankFragment extends BaseFragment<View_RankBook, Presenter_RankBookImpl> implements View_RankBook {
    //    private int start;
    private BookRankAdapter mAdapter;
    private List<RankingList.MaleBean> mList = new ArrayList<>();
    //
//    private SwipeRefreshLayout swipeRefreshLayout;
    private XRecyclerView xRecyclerVIew;
//
//
//    private boolean refreshMore;
//    private boolean loadMore;
//
//    private int total;
//    private BookListParams params;
//    Handler mHandler = new Handler(Looper.getMainLooper());



    @Override
    protected void findViewByID(View loadViewLayout) {
//        swipeRefreshLayout = (SwipeRefreshLayout) loadViewLayout.findViewById(R.id.srl_refresh);
        xRecyclerVIew = (XRecyclerView) loadViewLayout.findViewById(R.id.lmrv_book_list);
    }

    @Override
    protected Presenter_RankBookImpl createPresenter() {
        return new Presenter_RankBookImpl(this);
    }

    @Override
    protected void setListener() {
        xRecyclerVIew.setPullRefreshEnabled(false);
        xRecyclerVIew.addOnItemTouchListener(new rvItemClickListener(xRecyclerVIew, new rvItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                RankingList.MaleBean bean = mList.get(position - 1);
                // 查看书单详情
                Intent intent = new Intent(mContext, RankingList_Activity.class);
                intent.putExtra("RANKINGID", bean.get_id());
                intent.putExtra("TITLE", bean.getTitle());
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));
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
        presenter.getRankBook();
        initRecyclerView();

//        params = (BookListParams) getArguments().getSerializable("params");
//        Log.w("dd", "processLogic: " + params.toString());
//        presenter.getBookList(params);
////
//        start = Integer.parseInt(params.getStart());
//
//        moreRecyclerVIew.setHasFixedSize(true);
//        moreRecyclerVIew.setLayoutManager(new LinearLayoutManager(getContext()));//这里用线性显示 类似于listview

    }

    private void initRecyclerView() {
        xRecyclerVIew.setLayoutManager(new LinearLayoutManager(getActivity()));
        xRecyclerVIew.setHasFixedSize(true);

    }


    @Override
    protected void onVisible() {


    }

    private static final String TAG = "RankFragment";

    @Override
    public void getSuccess(RankingList rankingList) {
        if (rankingList != null) {
            if (SettingManager.getInstance().getUserChooseSex().equals("male")) {
                Log.e(TAG, "getSuccess: " + "male");
                mList = rankingList.getMale();
            } else {
                Log.e(TAG, "getSuccess: " + "female");
                mList = rankingList.getFemale();
            }
            if (mAdapter == null) {
                mAdapter = new BookRankAdapter(mList, getActivity());
                xRecyclerVIew.setAdapter(mAdapter);
            }
            mAdapter.notifyDataSetChanged();
            Log.w(TAG, "getSuccess: " + rankingList.getFemale().size() + rankingList.getFemale().size());
        }

    }
}