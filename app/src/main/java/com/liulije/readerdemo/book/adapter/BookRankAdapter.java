package com.liulije.readerdemo.book.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liulije.readerdemo.R;
import com.liulije.readerdemo.base.Constant;
import com.liulije.readerdemo.book.bean.RankingList;
import com.liulije.readerdemo.utils.glide.ImageUtils;

import java.util.List;

/**
 * @类名称: CLASS
 * @类描述:
 * @创建人：刘丽杰
 * @创建时间：2017/8/10 10:06
 * @备注：
 */
public class BookRankAdapter extends RecyclerView.Adapter<BookRankAdapter.BookViewHolder> {
    private List<RankingList.MaleBean> mlist;
    private Context mContext;
    private LayoutInflater mInflater;

    public BookRankAdapter(List<RankingList.MaleBean> mlist, Context cntext) {
        this.mlist = mlist;
        this.mContext = cntext;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BookViewHolder(mInflater.inflate(R.layout.item_rank_book, parent, false));
    }

    private static final String TAG = "RankingListAdapter";

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {

        final RankingList.MaleBean bookListBean = mlist.get(position);
        if (!bookListBean.collapse) {
            Log.w(TAG, "onBindViewHolder: " + bookListBean.toString());
            ImageUtils.setImageUrl(holder.ivSubjectImg,
                    Constant.IMG_BASE_URL + bookListBean.getCover(), R.mipmap.ic_launcher);
            holder.tvSubjectName.setText(bookListBean.getTitle());
            holder.ll_item.setVisibility(View.VISIBLE);
        } else {
            holder.ll_item.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class BookViewHolder extends RecyclerView.ViewHolder {
        ImageView ivSubjectImg;
        TextView tvSubjectName;
        LinearLayout ll_item;

        public BookViewHolder(View itemView) {
            super(itemView);
            ivSubjectImg = (ImageView) itemView.findViewById(R.id.iv_subject_img);
            tvSubjectName = (TextView) itemView.findViewById(R.id.tv_subject_name);
            ll_item = (LinearLayout) itemView.findViewById(R.id.ll_item);
        }
    }
}
