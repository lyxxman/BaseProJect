package com.ly.baseproject.bean;

import android.os.Parcel;
import android.os.Parcelable;


import java.io.Serializable;

/**
 * @author A18ccms A18ccms_gmail_com
 * @version V1.0
 * @Title: AdsImage.java
 * @Package com.blueteam.castwineparty.model
 * @Description: TODO(滚动广告图片模型)
 * @date 2015年8月4日 上午10:23:40
 */
public class AdsImage implements Parcelable,Serializable {
    /**
     * 图片地址
     */
    private String ImgUrl;
    /**
     * 排序num
     */
    private int SortNumber;
    /**
     * 广告名称
     */
    private String Name;
    /**
     * 显示地址
     */
    private String DisplayUrl;
    /**
     * 默认名称
     */
    private String DefaultName;
    /**
     * 广告类型：（厂商“factory”,单品：“product”,其它：“qita”）
     */
    private String AdvType;

    private long ProductId;
    private String FactoryPinyin;
    private String Desc;
    public AdsImage() {

    }

    public AdsImage(String imgUrl, String name) {
        ImgUrl = imgUrl;
        Name = name;
    }
    public AdsImage(String imgUrl, String name, String displayUrl) {
        this.ImgUrl = imgUrl;
        this.Name = name;
        this.DisplayUrl = displayUrl;
    }
    protected AdsImage(Parcel in) {
        ImgUrl = in.readString();
        SortNumber = in.readInt();
        Name = in.readString();
        DisplayUrl = in.readString();
        DefaultName = in.readString();
        AdvType = in.readString();
        ProductId = in.readLong();
        FactoryPinyin = in.readString();
    }

    public static final Creator<AdsImage> CREATOR = new Creator<AdsImage>() {
        @Override
        public AdsImage createFromParcel(Parcel in) {
            return new AdsImage(in);
        }

        @Override
        public AdsImage[] newArray(int size) {
            return new AdsImage[size];
        }
    };

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getFactoryPinyin() {
        return FactoryPinyin;
    }

    public void setFactoryPinyin(String factoryPinyin) {
        FactoryPinyin = factoryPinyin;
    }

    public long getProductId() {
        return ProductId;
    }

    public void setProductId(long productId) {
        ProductId = productId;
    }

    public String getAdvType() {
        return AdvType;
    }

    public void setAdvType(String advType) {
        AdvType = advType;
    }

    public String getDisplayUrl() {

        return DisplayUrl;
    }

    public void setDisplayUrl(String displayUrl) {
        DisplayUrl = displayUrl;
    }

    public String getName() {
        return  Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImgUrl() {
        return ImgUrl;
    }

    public void setImgUrl(String imgUrl) {
        ImgUrl = imgUrl;
    }

    public int getSortNumber() {
        return SortNumber;
    }

    public void setSortNumber(int sortNumber) {
        SortNumber = sortNumber;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(ImgUrl);
        parcel.writeInt(SortNumber);
        parcel.writeString(Name);
        parcel.writeString(DisplayUrl);
        parcel.writeString(DefaultName);
        parcel.writeString(AdvType);
        parcel.writeLong(ProductId);
        parcel.writeString(FactoryPinyin);
    }
}
