package com.liulije.readerdemo.book.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liulije.readerdemo.R;
import com.liulije.readerdemo.base.Constant;
import com.liulije.readerdemo.book.bean.Rankings;
import com.liulije.readerdemo.utils.glide.ImageUtils;

import java.util.List;

/**
 * @类名称: CLASS
 * @类描述:
 * @创建人：刘丽杰
 * @创建时间：2017/8/10 10:06
 * @备注：
 */
public class RankingListAdapter extends RecyclerView.Adapter<RankingListAdapter.BookViewHolder> {
    public List<Rankings.RankingBean.BooksBean> mlist;
    private Context mContext;
    private LayoutInflater mInflater;

    public RankingListAdapter(List<Rankings.RankingBean.BooksBean> mlist, Context cntext) {
        this.mlist = mlist;
        this.mContext = cntext;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BookViewHolder(mInflater.inflate(R.layout.item_subject_detail, parent, false));
    }

    private static final String TAG = "BookListAdapter";

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        final Rankings.RankingBean.BooksBean bookListBean = mlist.get(position);
        ImageUtils.setImageUrl(holder.ivBookImg,
                Constant.IMG_BASE_URL + bookListBean.getCover(), R.mipmap.ic_launcher);
        holder.tvBookTitle.setText(bookListBean.getTitle());
        holder.tvBookAuthor.setText(bookListBean.getAuthor());
        holder.tvBookDes.setText(bookListBean.getShortIntro());
        holder.tvBookMsg.setText("有:" + bookListBean.getRetentionRatio() + "%的读者喜欢");
//        holder.rlBookDetail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // 查看书单详情
//                Intent intent = new Intent(mContext, BookDetailActivity.class);
//                intent.putExtra("BOOKID", bookListBean.get_id());
//                mContext.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class BookViewHolder extends RecyclerView.ViewHolder {
        ImageView ivBookImg;
        TextView tvBookTitle;
        TextView tvBookAuthor;
        TextView tvBookMsg;
        TextView tvBookDes;
        RelativeLayout rlBookDetail;

        public BookViewHolder(View itemView) {
            super(itemView);
            ivBookImg = (ImageView) itemView.findViewById(R.id.iv_book_img);
            tvBookAuthor = (TextView) itemView.findViewById(R.id.tv_book_author);
            tvBookTitle = (TextView) itemView.findViewById(R.id.tv_book_title);
            tvBookMsg = (TextView) itemView.findViewById(R.id.tv_book_msg);
            tvBookDes = (TextView) itemView.findViewById(R.id.tv_book_desc);
            rlBookDetail = (RelativeLayout) itemView.findViewById(R.id.rl_book_detail);
        }
    }
}
