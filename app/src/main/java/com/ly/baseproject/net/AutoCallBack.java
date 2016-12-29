package com.ly.baseproject.net;

import com.ly.baseproject.net.response.BaseResponse;

import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author ly
 * @version V1.0
 * @Package com.ly.baseproject.net
 * @Description: 自定义Auto返回类
 * @date 2016/12/1 15:36
 */
public class AutoCallBack implements Callback<BaseResponse> {
    @Override
    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {

    }

    @Override
    public void onFailure(Call<BaseResponse> call, Throwable t) {


    }
}
