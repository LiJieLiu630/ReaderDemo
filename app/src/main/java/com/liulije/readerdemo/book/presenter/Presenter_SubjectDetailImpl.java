package com.liulije.readerdemo.book.presenter;

import android.util.Log;

import com.allen.library.base.BaseResponse;
import com.liulije.readerdemo.base.BasePresenter;
import com.liulije.readerdemo.base.MyListener;
import com.liulije.readerdemo.book.bean.SubjectDetailBean;
import com.liulije.readerdemo.book.model.Model_SubjectDetail;
import com.liulije.readerdemo.book.model.Model_SubjectDetailImpl;
import com.liulije.readerdemo.book.view.View_SubjectDetail;

/**
 * @类名称: CLASS
 * @类描述:
 * @创建人：LiuL1Jie
 * @创建时间：2017/8/9 14:53
 * @备注：
 */
public class Presenter_SubjectDetailImpl extends BasePresenter<View_SubjectDetail> implements Presenter_SubjectDetail {
    private Model_SubjectDetail m;

    public Presenter_SubjectDetailImpl() {
        m = new Model_SubjectDetailImpl();
    }

    boolean ifCanGetBookList = true;

    @Override
    public void getBookList(String params) {
        if (ifCanGetBookList) {
            ifCanGetBookList = false;
            m.getBookList(params, new MyListener() {
                @Override
                public void onFailure(String code) {
                    ifCanGetBookList = true;
                    mView.getFailure();
                }

                @Override
                public void onSuccess(BaseResponse baseResponse) {
                    ifCanGetBookList = true;
                    SubjectDetailBean re = (SubjectDetailBean) baseResponse;
                    mView.getDetailSuccess(re.getBookList());
                    Log.w("88", "onSuccess: " + re.getTitle());
//                    Log.w("ddd", "onSuccess: " + re.toString());
                }

            });
        }
    }
}
