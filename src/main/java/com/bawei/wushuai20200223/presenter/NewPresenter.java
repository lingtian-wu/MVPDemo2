package com.bawei.wushuai20200223.presenter;

import com.bawei.wushuai20200223.base.BasePresenter;
import com.bawei.wushuai20200223.base.DataCall;
import com.bawei.wushuai20200223.model.NewModel;
import com.bawei.wushuai20200223.util.NetUtil;

import javax.xml.transform.Result;

public class NewPresenter extends BasePresenter {
    public NewPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Result getModel(Object... args) {
        return NewModel.getNews((int)args[0]);
    }
}
