package com.ly.baseproject.ui;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.ly.baseproject.R;
import com.ly.baseproject.base.BaseActivity;
import com.ly.baseproject.bean.item.IndexNewTopV1Data;
import com.ly.baseproject.presenter.MainActivityPresenter;
import com.ly.baseproject.presenter.presenterImpl.MainActivityPresenterImpl;
import com.ly.baseproject.utils.LogUtil;
import com.ly.baseproject.utils.ToastUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author ly
 * @version V1.0
 * @Package com.ly.baseproject.view
 * @Description: 首页
 * @date 2016/11/16 11:27
 */
public class MainActivity extends BaseActivity implements MainActivityPresenterImpl.MainActivityLoadlistener {

    private final static String CACHE_NAME = "MAIN_ACTIVITY_TOP";
    @Bind(R.id.txt_name)
    TextView mTxtName;

    private MainActivityPresenterImpl mainActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainActivityPresenter = new MainActivityPresenterImpl(getApplicationContext(), CACHE_NAME);
        mainActivityPresenter.setMainActivityLoadlistener(this);
        mainActivityPresenter.getIndexNewTopData();
    }


    @Override
    public void onLoadIndexTopDataSuccess(IndexNewTopV1Data data) {
        mTxtName.setText("" + data.getBanner().get(0).getDisplayUrl());
    }

    @Override
    public void onLoadIndexTopError(String errorr) {
        LogUtil.getInstance().printErrorLog(CACHE_NAME, errorr);
    }
}
