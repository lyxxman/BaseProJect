package com.ly.baseproject.bean.item;


import com.ly.baseproject.bean.AdsImage;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author ly
 * @version V1.0
 * @Package com.blueteam.ganjiuhui.model.item
 * @Description: 首页顶部第一个返回数据集
 * @date 2016/3/3 11:31
 */
public class IndexNewTopV1Data implements Serializable{
    private ArrayList<AdsImage> Banner; //首页banner
    private ArrayList<AdsImage> FunctionIcons;//功能呢Icon
    private int MfslActivityId;//免费申领活动Id
    private int QktmActivityId;//清苦特卖活动id
    private int ZcActivityId;//众筹活动Id
    private int YpsxActivityId;//样品试销

    public int getMfslActivityId() {
        return MfslActivityId;
    }

    public void setMfslActivityId(int mfslActivityId) {
        MfslActivityId = mfslActivityId;
    }

    public int getZcActivityId() {
        return ZcActivityId;
    }

    public void setZcActivityId(int zcActivityId) {
        ZcActivityId = zcActivityId;
    }

    public int getYpsxActivityId() {
        return YpsxActivityId;
    }

    public void setYpsxActivityId(int ypsxActivityId) {
        YpsxActivityId = ypsxActivityId;
    }

    public ArrayList<AdsImage> getBanner() {
        return Banner;
    }

    public void setBanner(ArrayList<AdsImage> banner) {
        Banner = banner;
    }

    public ArrayList<AdsImage> getFunctionIcons() {
        return FunctionIcons;
    }

    public void setFunctionIcons(ArrayList<AdsImage> functionIcons) {
        FunctionIcons = functionIcons;
    }
}
