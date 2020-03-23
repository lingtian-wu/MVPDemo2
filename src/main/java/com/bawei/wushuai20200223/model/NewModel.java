package com.bawei.wushuai20200223.model;

import android.service.media.MediaBrowserService;
import android.util.Log;

import com.bawei.wushuai20200223.bean.NewBean;
import com.bawei.wushuai20200223.bean.UserBean;
import com.bawei.wushuai20200223.util.NetUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import javax.xml.transform.Result;

public class NewModel {
    public static NewBean<UserBean> usermodel(int pages){
        String doget = NetUtil.getInstance().doGet("http://blog.zhaoliang5156.cn/api/shop/shop1.json");
        Log.i("TAG",doget);
        Gson gson = new Gson();
        Type type = new TypeToken<NewBean<UserBean>>(){}.getType();
        NewBean<UserBean> shopBean = gson.fromJson(doget, type);
        return shopBean;
    }
}
