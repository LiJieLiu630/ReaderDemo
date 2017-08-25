package com.liulije.readerdemo.book.presenter;

import com.allen.library.http.CommonObserver;
import com.allen.library.interceptor.Transformer;
import com.liulije.readerdemo.base.BasePresenter;
import com.liulije.readerdemo.base.MyReaderApplication;
import com.liulije.readerdemo.book.bean.BookMixAToc;
import com.liulije.readerdemo.book.bean.ChapterRead;
import com.liulije.readerdemo.book.view.View_ReadBook;
import com.liulije.readerdemo.utils.ACache;

import io.reactivex.disposables.Disposable;

/**
 * @类名称: CLASS
 * @类描述:
 * @创建人：刘丽杰
 * @创建时间：2017/8/16 11:07
 * @备注：
 */
public class Presenter_BookReadImpl extends BasePresenter<View_ReadBook> {
    boolean ifCanGetChapter = true;

    public void getBookMixAToc(String bookid) {
        if (ifCanGetChapter) {
            ifCanGetChapter = false;
            BookMixAToc bookMixAToc = (BookMixAToc) ACache
                    .get(MyReaderApplication.getmInstance())
                    .getAsObject("book_chapter" + bookid);
            if (bookMixAToc != null) {
                ifCanGetChapter = true;
                mView.getChapterSuccess(bookMixAToc);
            } else
                MyReaderApplication
                        .getApiServices()
                        .getBookMixAToc(bookid, "chapters")
                        .compose(Transformer.<BookMixAToc>switchSchedulers())
                        .subscribe(new CommonObserver<BookMixAToc>() {
                            @Override
                            protected void getDisposable(Disposable d) {
                                mCompositeSubscription.add(d);
                            }

                            @Override
                            protected void onError(String errorMsg) {
                                ifCanGetChapter = true;

                            }

                            @Override
                            protected void onSuccess(BookMixAToc bookMixAToc) {
                                ifCanGetChapter = true;
                                mView.getChapterSuccess(bookMixAToc);
                            }
                        });
        }
    }


    boolean ifCanGetChapterContent = true;

    public void getChapterRead(String url , final int chapter) {
        if (ifCanGetChapterContent) {
            ifCanGetChapterContent = false;
            MyReaderApplication
                    .getApiServices()
                    .getChapterRead(url)
                    .compose(Transformer.<ChapterRead>switchSchedulers())
                    .subscribe(new CommonObserver<ChapterRead>() {
                        @Override
                        protected void getDisposable(Disposable d) {
                            mCompositeSubscription.add(d);
                        }

                        @Override
                        protected void onError(String errorMsg) {
                            ifCanGetChapterContent = true;

                        }

                        @Override
                        protected void onSuccess(ChapterRead bookMixAToc) {
                            ifCanGetChapterContent = true;
                            mView.getContentSuccess(bookMixAToc,chapter);
                        }
                    });
        }
    }

}
