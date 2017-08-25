package com.liulije.readerdemo.book.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;
import com.liulije.readerdemo.R;
import com.liulije.readerdemo.base.BaseActivity;
import com.liulije.readerdemo.base.Constant;
import com.liulije.readerdemo.base.RxEvent;
import com.liulije.readerdemo.bean.CollectionBookBean;
import com.liulije.readerdemo.book.bean.BookDetailBean;
import com.liulije.readerdemo.book.bean.BookListBean;
import com.liulije.readerdemo.book.bean.ReviewBean;
import com.liulije.readerdemo.book.presenter.Presenter_BookDetailImpl;
import com.liulije.readerdemo.book.view.View_BookDetail;
import com.liulije.readerdemo.utils.CollectionManager;
import com.liulije.readerdemo.utils.FormatUtils;
import com.liulije.readerdemo.utils.RxBus2;
import com.liulije.readerdemo.utils.glide.ImageUtils;

import java.util.List;

import widget.ExpendableTextView;

/**
 * 书籍详情类
 * <p/>
 * BookDetailBean 书籍详情实体
 */
public class BookDetailActivity extends BaseActivity<View_BookDetail, Presenter_BookDetailImpl> implements View_BookDetail {
    private TextView tvTitle;
    private ImageView ivBookImg;
    private TextView tvBookIntroduce;
    private TextView tvBookUpdate;
    private TextView tvBookAuthor;
    private TextView tvBookBilv;
    private ExpendableTextView etv;
    private TextView tvBookDesc;
    private FlexboxLayout fblBookTag;
    private LinearLayout llBookReview;
    private TextView tvBookMoreReview;
    private LinearLayout llBookRecommend;
    private TextView tvAddBook;

    private CollectionBookBean cpllectionBean;

    @Override
    protected Presenter_BookDetailImpl initPresenter() {
        return new Presenter_BookDetailImpl();
    }

    @Override
    protected void loadViewLayout(Bundle savedInstanceState) {
        setContentView(R.layout.activity_book_detail);
    }

    @Override
    protected void findViewById() {
        tvTitle = (TextView) findViewById(R.id.tv_title);
        ivBookImg = (ImageView) findViewById(R.id.iv_book_img);
        tvBookIntroduce = (TextView) findViewById(R.id.tv_book_introduce);
        tvBookUpdate = (TextView) findViewById(R.id.tv_book_update);
        tvBookAuthor = (TextView) findViewById(R.id.tv_book_author);
        tvBookBilv = (TextView) findViewById(R.id.tv_book_bilv);
        etv = (ExpendableTextView) findViewById(R.id.etv);
        tvBookDesc = (TextView) findViewById(R.id.tv_book_desc);
        fblBookTag = (FlexboxLayout) findViewById(R.id.fbl_book_tag);
        llBookReview = (LinearLayout) findViewById(R.id.ll_book_review);
        tvBookMoreReview = (TextView) findViewById(R.id.tv_book_more_review);
        tvAddBook = (TextView) findViewById(R.id.tv_book_add);
        llBookRecommend = (LinearLayout) findViewById(R.id.ll_book_recommend);

    }

    @Override
    protected void setListener() {

    }


    @Override
    protected void processLogic() {
        String bookId = getIntent().getStringExtra("BOOKID");
        if (CollectionManager.getInstance().isCollected(bookId)) {
            tvAddBook.setVisibility(View.GONE);
        } else {
            tvAddBook.setText("加入书架");
        }
        presenter.getBookDetail(bookId);
        presenter.getReview(bookId);
        presenter.getRecommendBookList(bookId);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_book_add:
                CollectionManager.getInstance().add(cpllectionBean);
                if (CollectionManager.getInstance().isCollected(cpllectionBean._id)) {
                    tvAddBook.setVisibility(View.GONE);
                    showToast("加入书架成功!");
                    RxBus2.getDefaultRxBus().post(new RxEvent(1));
                } else {
                    showToast("加入书架失败，请稍后重试！");
                    tvAddBook.setText("加入书架");
                }
                break;

            case R.id.tv_book_read:
                ReadBookActivity.startActivity(this, cpllectionBean);
                finish();
            default:

                break;
        }
    }


    @Override
    public void getFailure() {

    }


    /**
     * =================================================================================
     * 书评数据处理
     * =================================================================================
     */
    @Override
    public void getReviewSuccess(ReviewBean bean) {
        Log.e("dd", "setBaseData: 0" + bean.toString());
        setReviewData(bean.getReviews());

    }

    /**
     * 设置评论数据
     *
     * @param reviews
     */
    private void setReviewData(List<ReviewBean> reviews) {
        llBookReview.removeAllViews();
        for (int i = 0; i < reviews.size(); i++) {
            ReviewBean review = reviews.get(i);
            View view = LayoutInflater.from(this).inflate(R.layout.item_book_review, null);
            LinearLayout llSubjectItem = (LinearLayout) view.findViewById(R.id.ll_subject_item);
            ImageView ivReviewImg = (ImageView) view.findViewById(R.id.iv_review_img);
            TextView tvReviewShared = (TextView) view.findViewById(R.id.tv_review_shared);
            TextView tvReviewIntroduce = (TextView) view.findViewById(R.id.tv_review_introduce);

            ImageUtils.setCircleImageUrl(ivReviewImg,
                    Constant.IMG_BASE_URL + review.getAuthor().getAvatar(), R.mipmap.ic_launcher);
            tvReviewShared.setText(review.getAuthor().getNickname());
            tvReviewIntroduce.setText(review.getContent());
            llBookReview.addView(view);

        }
    }


    /**
     * =================================================================================
     * 基础数据处理
     * =================================================================================
     */
    @Override
    public void getSuccess(BookDetailBean bean) {
        tvTitle.setText(bean.getTitle());

        ImageUtils.setImageUrl(ivBookImg, Constant.IMG_BASE_URL + bean.getCover());
        tvBookIntroduce.setText(bean.getMajorCate() + "|字数：" + FormatUtils.formatWordCount(bean.getWordCount()));
        tvBookAuthor.setText("作者：" + bean.getAuthor());
        if (TextUtils.isEmpty(bean.getLastChapter())) {
            tvBookUpdate.setText("已完结|共" + bean.getChaptersCount() + "章");
        } else {
            tvBookUpdate.setText("最近更新：" + bean.getLastChapter());
        }

        if (bean.getRetentionRatio().isEmpty()) {
            tvBookBilv.setText("快来一起阅读吧！！！");
        } else {
            tvBookBilv.setText(bean.getRetentionRatio() + "%的读者喜欢这本书O(∩_∩)O~~~");
        }

        tvBookDesc.setText("    " + bean.getLongIntro());
        if (bean.getTags().size() > 0) {

            addTags(bean.getTags());
        }
        Log.w("88", "onSuccess: " + bean.getTitle());

        setCollectionBean(bean);
    }

    /**
     * 书架收藏实体
     *
     * @param
     */
    private void setCollectionBean(BookDetailBean data) {
        cpllectionBean = new CollectionBookBean();
        cpllectionBean.title = data.getTitle();
        cpllectionBean._id = data.get_id();
        cpllectionBean.cover = data.getCover();
        cpllectionBean.lastChapter = data.getLastChapter();
        cpllectionBean.updated = data.getUpdated();
    }

    /**
     * 设置书籍基本数据
     *
     * @param bean
     */
    private void setBaseData(BookDetailBean bean) {

    }

    /**
     * 类型
     *
     * @param mTags
     */
    private void addTags(List<String> mTags) {
        fblBookTag.removeAllViews();
        for (int i = 0; i < mTags.size(); i++) {
            TextView textView = createBaseFlexItemTypeTextView(mTags.get(i));
            textView.setLayoutParams(createDefaultLayoutParams());
            textView.setText(mTags.get(i));
            textView.setTextSize(13);
            fblBookTag.addView(textView);
        }
    }

    private ViewGroup.LayoutParams createDefaultLayoutParams() {
        FlexboxLayout.LayoutParams lp = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //它假设每个子项的 layout_flexGrow 属性的值设为 1，那么剩余空间将均匀分配到每个子项。默认为0
//        lp.flexGrow = 1;
        lp.setMargins(0, 40, 40, 0);
//        lp.setMargins(10, 5, 10, 5);
        return lp;
    }

    private TextView createBaseFlexItemTypeTextView(String s) {
        final TextView textView = new TextView(this);
        textView.setText(s);
        textView.setPadding(30, 8, 30, 8);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.WHITE);
        textView.setBackgroundColor(Color.parseColor("#55c8e7"));
        return textView;
    }

    /**
     * =================================================================================
     * 推荐数据处理
     * =================================================================================
     */
    @Override
    public void getRecommendSuccess(BookListBean bean) {

        setRecommendData(bean.getBooklists());
    }

    private void setRecommendData(List<BookListBean> bookLists) {
        llBookRecommend.removeAllViews();
        for (int i = 0; i < bookLists.size(); i++) {
            BookListBean review = bookLists.get(i);
            View view = LayoutInflater.from(this).inflate(R.layout.item_booklist, null);
            LinearLayout llSubjectItem = (LinearLayout) view.findViewById(R.id.ll_subject_item);
            ImageView ivSubjectImg = (ImageView) view.findViewById(R.id.iv_subject_img);
            TextView tvSubjectName = (TextView) view.findViewById(R.id.tv_subject_name);
            TextView tvSubjectShared = (TextView) view.findViewById(R.id.tv_subject_shared);
            TextView tvSubjectIntroduce = (TextView) view.findViewById(R.id.tv_subject_introduce);
            ImageUtils.setCircleImageUrl(ivSubjectImg,
                    Constant.IMG_BASE_URL + review.getCover(), R.mipmap.ic_launcher);
            tvSubjectShared.setText(review.getAuthor());
            tvSubjectName.setText(review.getTitle());
            tvSubjectIntroduce.setText(review.getDesc());
            llBookRecommend.addView(view);
        }
    }


}

