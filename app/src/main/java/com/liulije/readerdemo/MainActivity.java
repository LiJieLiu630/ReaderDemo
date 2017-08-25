package com.liulije.readerdemo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.liulije.readerdemo.base.BaseActivity;
import com.liulije.readerdemo.base.BasePresenter;
import com.liulije.readerdemo.base.Constant;
import com.liulije.readerdemo.base.RxEvent;
import com.liulije.readerdemo.fragment.FourFragment;
import com.liulije.readerdemo.fragment.OneFragment;
import com.liulije.readerdemo.fragment.ThreeFragment;
import com.liulije.readerdemo.fragment.TwoFragment;
import com.liulije.readerdemo.utils.ActivitysManager;
import com.liulije.readerdemo.utils.RxBus2;
import com.liulije.readerdemo.utils.SettingManager;
import com.liulije.readerdemo.utils.SharedPreferencesUtil;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import widget.ResideLayout;


public class MainActivity extends BaseActivity implements RxBus2.doEevent, BottomNavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "MainActivity";
    BottomNavigationView bnvMain;
    ImageView mainIcon;
    ResideLayout rlPancel;
    //当前展示的fragment
    private int current = 0;
    //上一个展示的fragment
    private Fragment lastFragment;
    private OneFragment oneFragment;
    private TwoFragment twoFragment;
    private ThreeFragment threeFragment;
    private FourFragment fourFragment;
    TextView tvMainSearch;
    //退出应用的时间
    private long currentTimePressed = 0;
    //退出应用按返回键的时间间隔时间间隔
    private static final int KEY_BACK_INTERVAL = 2000;

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putInt("CURRENT", current);
        super.onSaveInstanceState(outState, outPersistentState);
    }


    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void loadViewLayout(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            showSavaInstanceFragment(savedInstanceState);
        } else {
            Log.w(TAG, "loadViewLayout: ");

            showInitFragment();
        }

    }

    @Override
    protected void findViewById() {

        bnvMain = (BottomNavigationView) findViewById(R.id.bnv_main);
        mainIcon = (ImageView) findViewById(R.id.main_icon);
        rlPancel = (ResideLayout) findViewById(R.id.rl_pancel);
        tvMainSearch = (TextView) findViewById(R.id.tv_main_search);
    }

    @Override
    protected void setListener() {
        bnvMain.setOnNavigationItemSelectedListener(this);
        mainIcon.setOnClickListener(this);
    }

    @Override
    protected void processLogic() {

        Log.e(TAG, "processLogic: " + SettingManager.getInstance().getUserChooseSex());
//        initRxBus2();
    }


    private void showInitFragment() {
        Log.w(TAG, "showInitFragment: ");
        oneFragment = new OneFragment();
        twoFragment = new TwoFragment();
        threeFragment = new ThreeFragment();
        fourFragment = new FourFragment();
        lastFragment = oneFragment;
        current = 0;
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, oneFragment, OneFragment.class.getName())
                .add(R.id.fragment_container, twoFragment, TwoFragment.class.getName())
                .add(R.id.fragment_container, threeFragment, ThreeFragment.class.getName())
                .add(R.id.fragment_container, fourFragment, FourFragment.class.getName())
                .hide(twoFragment)
                .hide(threeFragment)
                .hide(fourFragment)
                .commit();
    }

    /**
     * 意外启动状态设置fragment显示
     *
     * @param savedInstanceState
     */
    private void showSavaInstanceFragment(Bundle savedInstanceState) {
        oneFragment = (OneFragment) getSupportFragmentManager()
                .findFragmentByTag(OneFragment.class.getName());
        twoFragment = (TwoFragment) getSupportFragmentManager()
                .findFragmentByTag(TwoFragment.class.getName());
        threeFragment = (ThreeFragment) getSupportFragmentManager()
                .findFragmentByTag(ThreeFragment.class.getName());
        fourFragment = (FourFragment) getSupportFragmentManager()
                .findFragmentByTag(FourFragment.class.getName());
        getSupportFragmentManager()
                .beginTransaction()
                .hide(oneFragment)
                .hide(twoFragment)
                .hide(threeFragment)
                .hide(fourFragment)
                .commit();
        current = savedInstanceState.getInt("CURRENT");
        switch (current) {
            case 0:
                getSupportFragmentManager().beginTransaction().show(oneFragment).commit();
                lastFragment = oneFragment;
                break;
            case 1:
                getSupportFragmentManager().beginTransaction().show(twoFragment).commit();
                lastFragment = twoFragment;
                break;
            case 2:
                getSupportFragmentManager().beginTransaction().show(threeFragment).commit();
                lastFragment = threeFragment;
                break;
            case 3:
                getSupportFragmentManager().beginTransaction().show(fourFragment).commit();
                lastFragment = fourFragment;
                break;
            default:

                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        tvMainSearch.setVisibility(View.GONE);

        switch (item.getItemId()) {
            case R.id.navigation_one:
                if (lastFragment != oneFragment) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .hide(lastFragment)
                            .show(oneFragment)
                            .commit();
                    current = 0;
                    lastFragment = oneFragment;
                }
                break;
            case R.id.navigation_two:
                if (lastFragment != twoFragment) {
                    tvMainSearch.setVisibility(View.VISIBLE);
                    getSupportFragmentManager()
                            .beginTransaction()
                            .hide(lastFragment)
                            .show(twoFragment)
                            .commit();
                    current = 1;
                    lastFragment = twoFragment;
                }
                break;
            case R.id.navigation_three:
                if (lastFragment != threeFragment) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .hide(lastFragment)
                            .show(threeFragment)
                            .commit();
                    current = 2;
                    lastFragment = threeFragment;
                }
                break;
            case R.id.navigation_four:
                if (lastFragment != fourFragment) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .hide(lastFragment)
                            .show(fourFragment)
                            .commit();
                    current = 3;
                    lastFragment = fourFragment;
                }
                break;
            default:

                break;
        }
        return true;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_icon:
                if (rlPancel.isOpen()) {
                    rlPancel.closePane();
                } else {
                    rlPancel.openPane();
                }
                break;
            case R.id.tv_day_or_night:
                break;
            default:

                break;
        }
    }

//    private void initRxBus2() {
//        RxBus2.getDefaultRxBus().toObservable(RxEvent.class).subscribe(new Observer<RxEvent>() {
//            @Override
//            public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
//                compositeDisposable.add(d);
//            }
//
//            @Override
//            public void onNext(@io.reactivex.annotations.NonNull RxEvent rxEvent) {
//                Log.e(TAG, "onNext: mainactivity" + rxEvent.getType());
//
//            }
//
//            @Override
//            public void onError(@io.reactivex.annotations.NonNull Throwable e) {
//                Log.e(TAG, "onError: " + e.toString());
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });
//    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (event.getAction() == KeyEvent.ACTION_DOWN
                    && event.getRepeatCount() == 0) {
                exitApp();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /***
     * 连按两次返回键退出应用
     */
    @SuppressLint("WrongConstant")
    private void exitApp() {
        if ((System.currentTimeMillis() - currentTimePressed) > 2000) {
//            MyToast.instance().show("再按一次返回键退出");
            Toast.makeText(MainActivity.this, "退出需要按两次哦！！！", Toast.LENGTH_SHORT).show();
            currentTimePressed = System.currentTimeMillis();
        } else {
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        FragmentManager manager = getSupportFragmentManager();
        for (Fragment fragment : manager.getFragments()) {
            if (fragment != null) {
                handleFrament(fragment, requestCode, resultCode, data);
            } else {
                Log.w(TAG, "fragments: ");
            }
        }
    }

    private void handleFrament(Fragment fragment, int requestCode, int resultCode, Intent data) {
        fragment.onActivityResult(requestCode, resultCode, data);
        List<Fragment> fragments = fragment.getChildFragmentManager().getFragments();
        if (fragments != null && fragments.size() > 0) {
            for (Fragment fragment1 : fragments) {
                if (fragment1 != null) {
                    handleFrament(fragment1, requestCode, resultCode, data);
                } else Log.w(TAG, "fragments: ");
            }
        } else Log.w(TAG, "chlldfragments are null");
    }

    @Override
    public void onNext(RxEvent rxEvent) {
        if (rxEvent.getType() == 2) {
            bnvMain.setSelectedItemId(R.id.navigation_two);
        }
    }
}
