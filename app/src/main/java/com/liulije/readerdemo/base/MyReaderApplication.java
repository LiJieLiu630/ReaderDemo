package com.liulije.readerdemo.base;

import android.app.Application;
import android.content.Context;

import com.allen.library.RxHttpUtils;
import com.allen.library.base.BaseRxHttpApplication;
import com.liulije.readerdemo.api.ApiBookService;
import com.liulije.readerdemo.utils.AppUtils;
import com.liulije.readerdemo.utils.SettingManager;
import com.liulije.readerdemo.utils.SharedPreferencesUtil;

/**
 * @类名称: CLASS
 * @类描述:
 * @创建人：LiuL1Jie
 * @创建时间：2017/8/9 13:17
 * @备注：
 */
public class MyReaderApplication extends BaseRxHttpApplication {
    private static volatile MyReaderApplication mInstance;
    private Context mContext;
    private static volatile ApiBookService apiServices;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        AppUtils.init(mContext);
        initPrefs();
//        SettingManager.getInstance().saveUserChooseSex("female");
    }

    /**
     * 初始化SharedPreference
     */
    protected void initPrefs() {
        SharedPreferencesUtil.init(getApplicationContext(), getPackageName() + "_preference", Context.MODE_MULTI_PROCESS);
    }

    /**
     * 获取接口实例
     *
     * @return
     */
    public static ApiBookService getApiServices() {
        if (apiServices == null) {
            synchronized (ApiBookService.class) {
                if (apiServices == null) {
                    apiServices = RxHttpUtils.getSInstance()
                            .baseUrl(Constant.API_BASE_URL)
                            .createSApi(ApiBookService.class);
                }
            }
        }
        return apiServices;
    }


    public static MyReaderApplication getmInstance() {
        if (mInstance == null) {
            synchronized (MyReaderApplication.class) {
                if (mInstance == null) {
                    mInstance = new MyReaderApplication();
                }
            }
        }
        return mInstance;
    }
}
