package com.bawei.wushuai20200223.base;

import com.bawei.wushuai20200223.bean.NewBean;


public interface DataCall<T> {
    void success(NewBean<T> shopBean);
    void fail();
}