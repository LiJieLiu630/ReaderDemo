package com.liulije.readerdemo.base;

import com.allen.library.base.BaseResponse;

/**
 * @类名称: CLASS
 * @类描述:
 * @创建人：刘丽杰
 * @创建时间：2017/8/9 10:07
 * @备注：
 */
public interface MyListener {

    /**
     * code失败原因标记
     *
     * @param code
     */
    void onFailure(String code);

    void onSuccess(BaseResponse baseResponse);

}
