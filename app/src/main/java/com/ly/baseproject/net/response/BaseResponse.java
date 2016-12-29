package com.ly.baseproject.net.response;

/**
 * @author ly
 * @version V1.0
 * @Package com.ly.baseproject.net.response
 * @Description: 基本网络返回类
 * @date 2016/11/25 16:21
 */
public class BaseResponse {
    private int Status; //状态码
    private String ErrorMsg; //错误日志

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getErrorMsg() {
        return ErrorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        ErrorMsg = errorMsg;
    }
}
