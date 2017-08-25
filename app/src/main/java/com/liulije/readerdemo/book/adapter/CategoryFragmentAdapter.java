package com.liulije.readerdemo.book.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.liulije.readerdemo.book.activity.SubjectDetailActivity;
import com.liulije.readerdemo.book.bean.BookListBean;
import com.liulije.readerdemo.book.bean.CategoryList;
import com.liulije.readerdemo.utils.ImageUrlUtils;
import com.liulije.readerdemo.utils.glide.ImageUtils;

import java.util.List;

/**
 * @类名称: CLASS
 * @类描述:
 * @创建人：刘丽杰
 * @创建时间：2017/8/10 10:06
 * @备注：
 */
public class CategoryFragmentAdapter extends RecyclerView.Adapter<CategoryFragmentAdapter.BookViewHolder> {
    private List<CategoryList.MaleBean> mlist;
    private Context mContext;
    private LayoutInflater mInflater;

    public CategoryFragmentAdapter(List<CategoryList.MaleBean> mlist, Context cntext) {
        this.mlist = mlist;
        this.mContext = cntext;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BookViewHolder(mInflater.inflate(R.layout.item_book_category, parent, false));
    }

    private static final String TAG = "BookListAdapter";

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {

        final CategoryList.MaleBean bookListBean = mlist.get(position);
        ImageUtils.setImageUrl(holder.ivCategoryBookimg,
                Constant.IMG_BASE_URL + ImageUrlUtils.getOneUrl(), R.mipmap.ic_launcher);
        holder.tvCategoryBookname.setText(bookListBean.name);
        holder.tvCategoryBookdes.setText(bookListBean.bookCount + "本书");
        if (position == 0) {
            holder.llCategoryType.setVisibility(View.VISIBLE);
            holder.tvCategoryGender.setText("女生分类");
        } else {
            if (bookListBean.isFirstMale()) {
                holder.llCategoryType.setVisibility(View.VISIBLE);
                holder.tvCategoryGender.setText("男生分类");
            } else
                holder.llCategoryType.setVisibility(View.GONE);
        }

//        holder.llCategoryDetail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e(TAG, "2222222onClick: ");
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class BookViewHolder extends RecyclerView.ViewHolder {
        LinearLayout llCategoryType;
        TextView tvCategoryGender;
        LinearLayout llCategoryDetail;
        ImageView ivCategoryBookimg;
        TextView tvCategoryBookname;
        TextView tvCategoryBookdes;


        public BookViewHolder(View itemView) {
            super(itemView);
            ivCategoryBookimg = (ImageView) itemView.findViewById(R.id.iv_category_bookimg);
            tvCategoryBookname = (TextView) itemView.findViewById(R.id.tv_category_bookname);
            tvCategoryBookdes = (TextView) itemView.findViewById(R.id.tv_category_bookdes);
            tvCategoryGender = (TextView) itemView.findViewById(R.id.tv_category_gender);
            llCategoryDetail = (LinearLayout) itemView.findViewById(R.id.ll_category_detail);
            llCategoryType = (LinearLayout) itemView.findViewById(R.id.ll_category_type);
        }
    }
}
