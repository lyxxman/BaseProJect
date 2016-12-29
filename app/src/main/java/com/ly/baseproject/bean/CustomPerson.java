package com.ly.baseproject.bean;

/**
 * @author ly
 * @version V1.0
 * @Package com.blueteam.ganjiuhui.model
 * @Description: 客服人员
 * @date 2016/8/23 15:29
 */
public class CustomPerson {

    //联系电话
    private String Mobile;
    //服务类型
    private String ServiceType;
    //头像
    private String HeadImg;
    //名称
    private String Name;

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getServiceType() {
        return ServiceType;
    }

    public void setServiceType(String serviceType) {
        ServiceType = serviceType;
    }

    public String getHeadImg() {
        return HeadImg;
    }

    public void setHeadImg(String headImg) {
        HeadImg = headImg;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
