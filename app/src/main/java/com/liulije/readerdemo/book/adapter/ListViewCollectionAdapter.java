package com.liulije.readerdemo.book.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liulije.readerdemo.R;
import com.liulije.readerdemo.base.Constant;
import com.liulije.readerdemo.book.bean.BookListBean;
import com.liulije.readerdemo.utils.glide.ImageUtils;

import java.util.List;

/**
 * @类名称: CLASS
 * @类描述:
 * @创建人：LiuL1Jie
 * @创建时间：2017/8/10 16:49
 * @备注：
 */
public class ListViewCollectionAdapter extends BaseAdapter {
    private List<BookListBean> mlist;
    private Context mContext;
    private LayoutInflater mInflater;

    public ListViewCollectionAdapter(List<BookListBean> mlist, Context cntext) {
        this.mlist = mlist;
        this.mContext = cntext;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View itemView, ViewGroup parent) {
        BookViewHolder holder = null;
        if (itemView == null) {
            itemView = mInflater.inflate(R.layout.item_collection_subject, parent, false);
            holder = new BookViewHolder();
            holder.ivSubjectImg = (ImageView) itemView.findViewById(R.id.iv_subject_img);
            holder.tvSubjectName = (TextView) itemView.findViewById(R.id.tv_subject_name);
            holder.tvSubjectShare = (TextView) itemView.findViewById(R.id.tv_subject_shared);
            holder.tvSubjectIntroduce = (TextView) itemView.findViewById(R.id.tv_subject_introduce);
            holder.llSubjectIteml = (LinearLayout) itemView.findViewById(R.id.ll_subject_item);
            itemView.setTag(holder);
        } else {
            holder = (BookViewHolder) itemView.getTag();
        }
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
//                Intent intent = new Intent(mContext, SubjectActivity.class);
//                intent.putExtra("SUBJECT", bookListBean);
//                mContext.startActivity(intent);
            }
        });
        return itemView;
    }

    class BookViewHolder {
        ImageView ivSubjectImg;
        TextView tvSubjectName;
        TextView tvSubjectShare;
        TextView tvSubjectIntroduce;
        LinearLayout llSubjectIteml;

    }
}
