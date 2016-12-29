package com.ly.baseproject.net.response;

import com.ly.baseproject.bean.CustomPerson;

import java.util.ArrayList;

/**
 * @author ly
 * @version V1.0
 * @Package com.ly.baseproject.net.response
 * @Description: 客服列表返回类
 * @date 2016/12/2 16:12
 */
public class CustomListsResponse extends BaseResponse {

    private ArrayList<CustomPerson> Data;

    public ArrayList<CustomPerson> getData() {
        return Data;
    }

    public void setData(ArrayList<CustomPerson> data) {
        Data = data;
    }
}
