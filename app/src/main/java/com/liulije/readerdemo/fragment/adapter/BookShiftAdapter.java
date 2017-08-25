package com.liulije.readerdemo.fragment.adapter;

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
import com.liulije.readerdemo.base.RxEvent;
import com.liulije.readerdemo.bean.CollectionBookBean;
import com.liulije.readerdemo.utils.CollectionManager;
import com.liulije.readerdemo.utils.RxBus2;
import com.liulije.readerdemo.utils.glide.ImageUtils;

import java.util.List;

/**
 * @类名称: CLASS
 * @类描述:
 * @创建人：LiuL1Jie
 * @创建时间：2017/8/14 12:34
 * @备注：
 */
public class BookShiftAdapter extends RecyclerView.Adapter<BookShiftAdapter.BookShiftViewHolder> {
    private List<CollectionBookBean> mlist;
    private Context mContext;
    private LayoutInflater mInflater;

    public BookShiftAdapter(List<CollectionBookBean> mlist, Context cntext) {
        this.mlist = mlist;
        this.mContext = cntext;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public BookShiftViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BookShiftViewHolder(mInflater.inflate(R.layout.item_book_shift, parent, false));
    }

    private static final String TAG = "BookListAdapter";
    public boolean isCanDelete = false;

    @Override
    public void onBindViewHolder(BookShiftViewHolder holder, int position) {

        final CollectionBookBean bookListBean = mlist.get(position);

        if (bookListBean.isEmptyAddMore()) {
            holder.ivBookShiftImg.setImageResource(R.mipmap.iv_add);
        }
        ImageUtils.setImageUrl(holder.ivBookShiftImg,
                Constant.IMG_BASE_URL + bookListBean.getCover(), R.mipmap.ic_launcher);
        holder.tvBookShiftName.setText(bookListBean.getTitle());
        if (isCanDelete) {
            if (bookListBean.isEmptyAddMore()) {
                holder.rlBookShift.setVisibility(View.GONE);
            }
            holder.ivBookDelete.setVisibility(View.VISIBLE);
            holder.ivBookDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CollectionManager.getInstance().remoreOne(bookListBean._id);
                    if (!CollectionManager.getInstance().isCollected(bookListBean._id)) {
                        mlist.remove(bookListBean);
                        isCanDelete = false;
                        if (mlist.size() == 1) {
                            RxBus2.getDefaultRxBus().post(new RxEvent(1));
                        } else {
                            notifyDataSetChanged();
                        }
                    }
                }
            });
        } else {
            holder.rlBookShift.setVisibility(View.VISIBLE);
            holder.ivBookDelete.setVisibility(View.GONE);
        }


    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class BookShiftViewHolder extends RecyclerView.ViewHolder {
        ImageView ivBookDelete;
        ImageView ivBookShiftImg;
        TextView tvBookShiftName;
        RelativeLayout rlBookShift;

        public BookShiftViewHolder(View itemView) {
            super(itemView);
            ivBookShiftImg = (ImageView) itemView.findViewById(R.id.iv_book_shift_img);
            ivBookDelete = (ImageView) itemView.findViewById(R.id.iv_book_delete);
            tvBookShiftName = (TextView) itemView.findViewById(R.id.tv_book_shift_name);
            rlBookShift = (RelativeLayout) itemView.findViewById(R.id.rl_book_shift);
        }
    }
}
