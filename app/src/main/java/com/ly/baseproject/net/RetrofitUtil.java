package com.ly.baseproject.net;

import com.google.gson.Gson;
import com.ly.baseproject.base.AppConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author ly
 * @version V1.0
 * @Package com.ly.baseproject.net
 * @Description: Retrofit工具类
 * @date 2016/12/1 14:34
 */
public class RetrofitUtil {

    private static RetrofitUtil instance = null;
    private static Retrofit retrofit = null;
    private static Retrofit retrofitV2 = null;
    public static String API_V1 = "API_V1"; //接口版本标识
    public static String API_V2 = "API_V2"; //接口标识

    private RetrofitUtil() {
    }

    public static RetrofitUtil getInstance() {
        if (instance == null) {
            instance = new RetrofitUtil();
        }
        return instance;
    }

    public Retrofit getRerofit(String apiVerison) {
        if (apiVerison.equals(API_V1)) {
            if (retrofit == null) {
                retrofit = buliderRetrofit(apiVerison);
            }
            return retrofit;
        } else if (apiVerison.equals(API_V2)) {
            if (retrofitV2 == null) {
                retrofitV2 = buliderRetrofit(apiVerison);
            }
            return retrofitV2;
        }
        return retrofit;
    }

    /**
     * 根据接口版本返回rerofit对象
     *
     * @param apiVersion
     * @return
     */
    public Retrofit buliderRetrofit(String apiVersion) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getBaseUrl(apiVersion))
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
        return retrofit;
    }

    /**
     * @param apiVersion 接口地址
     * @return baseUrl
     */
    public static String getBaseUrl(String apiVersion) {
        if (apiVersion.equals(API_V1)) {
            return AppConfig.ServiceAddress;
        } else {
            return AppConfig.ServiceAddressV2;
        }
    }
}
