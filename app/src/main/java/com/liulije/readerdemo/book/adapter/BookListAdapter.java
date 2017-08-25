package com.liulije.readerdemo.book.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liulije.readerdemo.R;
import com.liulije.readerdemo.base.Constant;
import com.liulije.readerdemo.book.activity.SubjectDetailActivity;
import com.liulije.readerdemo.book.bean.BookListBean;
import com.liulije.readerdemo.utils.glide.ImageUtils;

import java.util.List;

/**
 * @类名称: CLASS
 * @类描述:
 * @创建人：刘丽杰
 * @创建时间：2017/8/10 10:06
 * @备注：
 */
public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.BookViewHolder> {
    private List<BookListBean> mlist;
    private Context mContext;
    private LayoutInflater mInflater;

    public BookListAdapter(List<BookListBean> mlist, Context cntext) {
        this.mlist = mlist;
        this.mContext = cntext;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BookViewHolder(mInflater.inflate(R.layout.item_booklist, parent, false));
    }

    private static final String TAG = "BookListAdapter";

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {

        final BookListBean bookListBean = mlist.get(position);
        ImageUtils.setImageUrl(holder.ivSubjectImg,
                Constant.IMG_BASE_URL + bookListBean.getCover(), R.mipmap.ic_launcher);
        holder.tvSubjectName.setText(bookListBean.getTitle());
        holder.tvSubjectShare.setText(bookListBean.getAuthor());
        holder.tvSubjectIntroduce.setText(bookListBean.getDesc());
        holder.llSubjectIteml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查看书单详情
                Intent intent = new Intent(mContext, SubjectDetailActivity.class);
                intent.putExtra("SUBJECT", bookListBean);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class BookViewHolder extends RecyclerView.ViewHolder {
        ImageView ivSubjectImg;
        TextView tvSubjectName;
        TextView tvSubjectShare;
        TextView tvSubjectIntroduce;
        LinearLayout llSubjectIteml;

        public BookViewHolder(View itemView) {
            super(itemView);
            ivSubjectImg = (ImageView) itemView.findViewById(R.id.iv_subject_img);
            tvSubjectName = (TextView) itemView.findViewById(R.id.tv_subject_name);
            tvSubjectShare = (TextView) itemView.findViewById(R.id.tv_subject_shared);
            tvSubjectIntroduce = (TextView) itemView.findViewById(R.id.tv_subject_introduce);
            llSubjectIteml = (LinearLayout) itemView.findViewById(R.id.ll_subject_item);
        }
    }
}
