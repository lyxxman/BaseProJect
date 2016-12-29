package com.ly.baseproject.presenter.presenterImpl;

import android.content.Context;

import com.ly.baseproject.R;
import com.ly.baseproject.bean.item.IndexNewTopV1Data;
import com.ly.baseproject.cache.CacheManager;
import com.ly.baseproject.net.RetrofitUtil;
import com.ly.baseproject.net.response.IndexNewTopResponse;
import com.ly.baseproject.net.resquest.MainActivityService;
import com.ly.baseproject.presenter.MainActivityPresenter;
import com.ly.baseproject.utils.LogUtil;
import com.ly.baseproject.utils.ToastUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author ly
 * @version V1.0
 * @Package com.ly.baseproject.presenter.presenterImpl
 * @Description: MainActiivty Prensenter实现
 * @date 2016/12/29 15:34
 */
public class MainActivityPresenterImpl implements MainActivityPresenter {
    private Context mContext;
    private String mTag = "";
    MainActivityLoadlistener mainActivityLoadlistener = null;

    public MainActivityPresenterImpl(Context mContext,String tag) {
        this.mContext = mContext;
        this.mTag = tag;
    }

    @Override
    public void getIndexNewTopData() {
        if(CacheManager.isCacheDataOnTime(mContext,mTag)){
                    IndexNewTopResponse response = (IndexNewTopResponse) CacheManager.readObject(mContext, mTag);
                    mainActivityLoadlistener.onLoadIndexTopDataSuccess(response.getData());
        }
        MainActivityService service = RetrofitUtil.getInstance().getRerofit(RetrofitUtil.API_V1).create(MainActivityService.class);
        Call<IndexNewTopResponse> call = service.getIndexTop();
        call.enqueue(new Callback<IndexNewTopResponse>() {
            @Override
            public void onResponse(Call<IndexNewTopResponse> call, Response<IndexNewTopResponse> response) {
                CacheManager.saveObject(mContext, response.body(), mTag);
                mainActivityLoadlistener.onLoadIndexTopDataSuccess(response.body().getData());
            }
            @Override
            public void onFailure(Call<IndexNewTopResponse> call, Throwable t) {
                mainActivityLoadlistener.onLoadIndexTopError(t.getMessage());
            }
        });


    }
    public interface MainActivityLoadlistener {
            //加载顶部数据成功
            void onLoadIndexTopDataSuccess(IndexNewTopV1Data data);
            //加载顶部数据失败
            void onLoadIndexTopError(String errorr);
    }

    public MainActivityLoadlistener getMainActivityLoadlistener() {
        return mainActivityLoadlistener;
    }

    public void setMainActivityLoadlistener(MainActivityLoadlistener mainActivityLoadlistener) {
        this.mainActivityLoadlistener = mainActivityLoadlistener;
    }
}
