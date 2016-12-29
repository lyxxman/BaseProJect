package com.ly.baseproject.net.resquest;

import com.ly.baseproject.base.AppConfig;
import com.ly.baseproject.net.response.CustomListsResponse;
import com.ly.baseproject.net.response.IndexNewTopResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * @author ly
 * @version V1.0
 * @Package com.ly.baseproject.net.resquest
 * @Description: 主页请求管理
 * @date 2016/11/25 15:56
 */
public interface MainActivityService {

    @GET(AppConfig.APP_MAIN_TOP_URL)
    Call<IndexNewTopResponse> getIndexTop();
}
