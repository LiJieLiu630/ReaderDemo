package com.liulije.readerdemo.book.activity;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.ListPopupWindow;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liulije.readerdemo.R;
import com.liulije.readerdemo.base.BaseActivity;
import com.liulije.readerdemo.base.BasePresenter;
import com.liulije.readerdemo.base.Constant;
import com.liulije.readerdemo.base.RxEvent;
import com.liulije.readerdemo.bean.CollectionBookBean;
import com.liulije.readerdemo.book.adapter.TocListAdapter;
import com.liulije.readerdemo.book.bean.BookMixAToc;
import com.liulije.readerdemo.book.bean.ChapterRead;
import com.liulije.readerdemo.book.presenter.Presenter_BookReadImpl;
import com.liulije.readerdemo.book.view.View_ReadBook;
import com.liulije.readerdemo.utils.BookCacheManager;
import com.liulije.readerdemo.utils.CollectionManager;
import com.liulije.readerdemo.utils.RxBus2;
import com.liulije.readerdemo.utils.ScreenUtils;
import com.liulije.readerdemo.utils.SettingManager;
import com.liulije.readerdemo.utils.SharedPreferencesUtil;
import com.liulije.readerdemo.utils.StatusBarCompat;
import com.liulije.readerdemo.utils.ThemeManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.TimeUnit;

import widget.readview.BaseReadView;
import widget.readview.OnReadStateChangeListener;
import widget.readview.OverlappedWidget;
import widget.readview.PageWidget;

public class ReadBookActivity extends BaseActivity<View_ReadBook, Presenter_BookReadImpl> implements View_ReadBook {
    public static final String INTENT_BEAN = "collectionbean";
    public static final String INTENT_SD = "isFromSD";
    private CollectionBookBean bookBean;
    private String bookID;
    private TextView tvTilte;
    private IntentFilter intentFilter = new IntentFilter();
    private View decodeView;
    private LinearLayout ilReadTop;
    private FrameLayout flReadBookContainer;
    private LinearLayout ilReadBottom;
    private BaseReadView mPageWidget;
    private List<BookMixAToc> mChapterList = new ArrayList<>();
    private Receiver receiver = new Receiver();
    private int currentChapter = 1;
    //    private ListPopupWindow mTocListPopupWindow;
//    private TocListAdapter mTocListAdapter;
    private int curTheme = 5;
    /**
     * 是否开始阅读章节
     **/
    private boolean startRead = false;
    RelativeLayout rlReadRoot;

    @Override
    protected Presenter_BookReadImpl initPresenter() {
        return new Presenter_BookReadImpl();
    }

    @Override
    protected void loadViewLayout(Bundle savedInstanceState) {
        setContentView(R.layout.activity_read_book);
    }

    public static void startActivity(Context context) {
        startActivity(context, null, false);
    }

    public static void startActivity(Context context, CollectionBookBean bookBean) {
        startActivity(context, bookBean, false);
    }

    private static void startActivity(Context context, CollectionBookBean bookBean, boolean b) {
        context.startActivity(new Intent(context, ReadBookActivity.class)
                .putExtra(INTENT_BEAN, bookBean)
                .putExtra(INTENT_SD, b));

    }


    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        if (!CollectionManager.getInstance().isCollected(bookID)) {
            showCollectionDialog();
        } else {
            finish();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    private void showCollectionDialog() {
        new AlertDialog.Builder(this)
                .setTitle("添加到书架")
                .setMessage("是否添加到书架")
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        CollectionManager.getInstance().add(bookBean);
                        RxBus2.getDefaultRxBus().post(new RxEvent(1));
                        finish();
                    }
                })
                .setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                })
                .create()
                .show();
    }

    @Override
    protected void findViewById() {
//        tvTilte = (TextView) findViewById(R.id.tv_title);
        ilReadTop = (LinearLayout) findViewById(R.id.il_read_top);
        flReadBookContainer = (FrameLayout) findViewById(R.id.fl_read_book_container);
        ilReadBottom = (LinearLayout) findViewById(R.id.il_read_bottom);
        rlReadRoot = (RelativeLayout) findViewById(R.id.rl_read_root);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void processLogic() {
        initDatas();
        presenter.getBookMixAToc(bookID);
//        initTocList();
        hideStatusBar();
        initAASet();
        initPagerWidget();
        decodeView = getWindow().getDecorView();
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) ilReadTop.getLayoutParams();
        params.topMargin = ScreenUtils.getStatusBarHeight(this) - 2;
        ilReadTop.setLayoutParams(params);

    }

    private void initAASet() {
        curTheme = SettingManager.getInstance().getReadTheme();
        ThemeManager.setReaderTheme(curTheme, rlReadRoot);
    }


    private void initPagerWidget() {
        if (SharedPreferencesUtil.getInstance().getInt(Constant.FLIP_STYLE, 0) == 0) {
            mPageWidget = new PageWidget(this, bookID, mChapterList, new ReadListener());
        } else {
            mPageWidget = new OverlappedWidget(this, bookID, mChapterList, new ReadListener());
        }
        registerReceiver(receiver, intentFilter);
        if (SharedPreferencesUtil.getInstance().getBoolean(Constant.ISNIGHT, false)) {
            mPageWidget.setTextColor(ContextCompat.getColor(this, R.color.chapter_content_night),
                    ContextCompat.getColor(this, R.color.chapter_title_night));
        }
        flReadBookContainer.removeAllViews();
        flReadBookContainer.addView(mPageWidget);
    }


    /**
     * 初始化后一些数据处理
     */
    private void initDatas() {
        bookBean = (CollectionBookBean) getIntent().getSerializableExtra(INTENT_BEAN);
        bookID = bookBean.get_id();
//        tvTilte.setText(bookBean.getTitle());
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        intentFilter.addAction(Intent.ACTION_TIME_TICK);
        CollectionManager.getInstance().setRecentReadingTime(bookID);
        if (CollectionManager.getInstance().isCollected(bookID)) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    //延迟2秒刷新书架
                    RxBus2.getDefaultRxBus().post(new RxEvent(1));
                }
            }, 2000);
        }
    }

    private static final String TAG = "ReadBookActivity";

    private class ReadListener implements OnReadStateChangeListener {
        @Override
        public void onChapterChanged(int chapter) {
            Log.w(TAG, "onChapterChanged: " + chapter);
            currentChapter = chapter;
            // 加载前一节 与 后三节
            for (int i = chapter - 1; i <= chapter + 3 && i <= mChapterList.size(); i++) {
                if (i > 0 && i != chapter && BookCacheManager.getInstance().getChapterFile(bookID, i) == null) {
                    presenter.getChapterRead(mChapterList.get(i - 1).getLink(), i);
                }
            }
        }

        @Override
        public void onPageChanged(int chapter, int page) {

        }

        @Override
        public void onLoadChapterFailure(int chapter) {

            startRead = false;
            if (BookCacheManager.getInstance().getChapterFile(bookID, chapter) == null)
                presenter.getChapterRead(mChapterList.get(chapter - 1).getLink(), chapter);
        }

        @Override
        public void onCenterClick() {
        }

        @Override
        public void onFlip() {
        }
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void getChapterSuccess(BookMixAToc bookMixAToc) {
        mChapterList.clear();
        mChapterList.addAll(bookMixAToc.getMixToc().getChapters());
        readCurrentChapter();
    }

    private void readCurrentChapter() {
        if (BookCacheManager.getInstance().getChapterFile(bookID, currentChapter) != null) {
            getContentSuccess(null, currentChapter);
        } else {
            presenter.getChapterRead(mChapterList.get(currentChapter - 1).getLink(), currentChapter);
        }
    }

    @Override
    public synchronized void getContentSuccess(ChapterRead data, int chapter) {
        if (data != null) {
            BookCacheManager.getInstance().saveChapterFile(bookID, chapter, data);
        }

        if (!startRead) {
            startRead = true;
            currentChapter = chapter;
            if (!mPageWidget.isPrepared) {
                Log.w(TAG, "getContentSuccess: " + chapter);
                mPageWidget.init(curTheme);
            } else {
                Log.w(TAG, "getContentSuccess:001 " + chapter);
                mPageWidget.jumpToChapter(currentChapter);
            }
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            if (SettingManager.getInstance().isVolumeFlipEnable()) {
                mPageWidget.nextPage();
                return true;// 防止翻页有声音
            }
        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            if (SettingManager.getInstance().isVolumeFlipEnable()) {
                mPageWidget.prePage();
                return true;
            }
        }
        return super.onKeyUp(keyCode, event);
    }

    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

    class Receiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (mPageWidget != null) {
                if (Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())) {
                    int level = intent.getIntExtra("level", 0);
                    mPageWidget.setBattery(100 - level);
                } else if (Intent.ACTION_TIME_TICK.equals(intent.getAction())) {
                    mPageWidget.setTime(sdf.format(new Date()));
                }
            }
        }
    }

}
