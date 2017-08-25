package com.liulije.readerdemo.book.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.liulije.readerdemo.R;
import com.liulije.readerdemo.base.BaseActivity;
import com.liulije.readerdemo.base.Constant;
import com.liulije.readerdemo.book.adapter.SubjectBookDetailAdapter;
import com.liulije.readerdemo.book.bean.BookListBean;
import com.liulije.readerdemo.book.bean.SubjectBookDetailBean;
import com.liulije.readerdemo.book.bean.SubjectDetailBean;
import com.liulije.readerdemo.book.presenter.Presenter_SubjectDetailImpl;
import com.liulije.readerdemo.book.view.View_SubjectDetail;
import com.liulije.readerdemo.utils.BookCacheManager;
import com.liulije.readerdemo.utils.glide.ImageUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 书单主题详情类
 */
public class SubjectDetailActivity extends BaseActivity<View_SubjectDetail, Presenter_SubjectDetailImpl> implements View_SubjectDetail {

    private XRecyclerView xRecyclerView;
    private SubjectBookDetailAdapter mAdapter;
    private List<SubjectBookDetailBean> mBookList = new ArrayList<>();
    private View headerView = null;
    private FloatingActionButton floatingCollection;
    private BookListBean bean;
    private TextView tvTitle;
    private ImageView ivCollection;

    @Override
    protected Presenter_SubjectDetailImpl initPresenter() {
        return new Presenter_SubjectDetailImpl();
    }

    @Override
    protected void loadViewLayout(Bundle savedInstanceState) {
        setContentView(R.layout.activity_subject);
    }

    @Override
    protected void findViewById() {
        xRecyclerView = (XRecyclerView) findViewById(R.id.xrv_subject_detail);
        floatingCollection = (FloatingActionButton) findViewById(R.id.fab_collection);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        ivCollection = (ImageView) findViewById(R.id.iv_right_img);
    }

    @Override
    protected void setListener() {
        floatingCollection.setOnClickListener(this);
        xRecyclerView.setPullRefreshEnabled(false);
        ivCollection.setOnClickListener(this);
    }

    @Override
    protected void processLogic() {
        bean = (BookListBean) getIntent().getSerializableExtra("SUBJECT");
        presenter.getBookList(bean.get_id());
        xRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        xRecyclerView.setHasFixedSize(true);
        headerView = LayoutInflater.from(this).inflate(R.layout.subject_book_header, null);
        LinearLayoutCompat.LayoutParams params = new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        headerView.setLayoutParams(params);
        xRecyclerView.addHeaderView(headerView);
        mAdapter = new SubjectBookDetailAdapter(mBookList, this);
        xRecyclerView.setAdapter(mAdapter);
        tvTitle.setText("书单详情");
        ivCollection.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;

            case R.id.fab_collection://收藏
//                showToast("收藏");
                BookCacheManager.getInstance().addBookCollection(bean);
                break;

            case R.id.iv_right_img:
                startActivity(new Intent(this, CollectionActivity.class));
                break;
            default:

                break;
        }
    }


    @Override
    public void getDetailSuccess(SubjectDetailBean bean) {
        if (bean != null) {
            setBaseData(bean);
            if (bean.getBooks() != null && bean.getBooks().size() > 0) {
                for (SubjectBookDetailBean subjectBookDetailBean : bean.getBooks()) {
                    mBookList.add(subjectBookDetailBean.getBook());
                    mAdapter.notifyDataSetChanged();
                }
            }
        }
    }

    private void setBaseData(SubjectDetailBean bean) {
        TextView headerName = (TextView) headerView.findViewById(R.id.tv_subject_title);
        TextView headerDes = (TextView) headerView.findViewById(R.id.tv_subject_desc);
        TextView headerAuthorName = (TextView) headerView.findViewById(R.id.tv_subject_author_name);
        TextView headerLikeCount = (TextView) headerView.findViewById(R.id.tv_subject_like_count);
        ImageView headerImg = (ImageView) headerView.findViewById(R.id.iv_suject_author_img);
        headerName.setText(bean.getTitle());
        headerDes.setText(bean.getDesc());
        headerAuthorName.setText(bean.getAuthor().getNickname());
        headerLikeCount.setText(bean.getCollectorCount() + "");
        ImageUtils.setCircleImageUrl(headerImg, Constant.IMG_BASE_URL + bean.getAuthor().getAvatar(), R.mipmap.ic_launcher);

    }


    @Override
    public void getFailure() {

    }
}
