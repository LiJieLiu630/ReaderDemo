package com.allen.library.base;

/**
 * Created by allen on 2017/6/23.
 * <p/>
 * 请求结果基类   所有请求结果继承此类
 * <p> 001基层
 * 解析返回的数据基本格式
 */

public class BaseResponse {
//
//    @Override
//    public String toString() {
//        return "BaseResponse{" +
//                "ok='" + ok + '\'' +
//                '}';
//    }
//
//    public String getOk() {
//        return ok;
//    }
//
//    public void setOk(String ok) {
//        this.ok = ok;
//    }
//
    //    /**
//     * 错误码
//     */
    private int code;
    /**
     * 错误描述
     */
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }

}
