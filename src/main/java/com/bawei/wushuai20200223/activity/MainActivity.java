package com.bawei.wushuai20200223.activity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.bawei.wushuai20200223.R;
import com.bawei.wushuai20200223.adapter.NewAdapter;
import com.bawei.wushuai20200223.base.BaseActivity;
import com.bawei.wushuai20200223.base.DataCall;
import com.bawei.wushuai20200223.bean.NewBean;
import com.bawei.wushuai20200223.bean.UserBean;
import com.bawei.wushuai20200223.presenter.NewPresenter;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import javax.xml.transform.Result;

public class MainActivity extends BaseActivity implements DataCall<UserBean> {
    private NewPresenter newPresenter;
    private PullToRefreshListView list;
    NewAdapter newAdapter;
    private int page = 1;

    @Override
    protected void initView(Bundle savedInstanceState) {
        newPresenter = new NewPresenter(this);
        list = findViewById(R.id.list);

        newAdapter = new NewAdapter();
        list.setAdapter(newAdapter);
        list.setMode(PullToRefreshBase.Mode.BOTH);
        list.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                page = 1;
                newPresenter.request(page);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                page++;
                newPresenter.request(page);
            }
        });
        newPresenter.request(page);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void success(NewBean<UserBean> shopBean) {
        list.onRefreshComplete();
        if (shopBean.hashCode() == 200){
            if (page==1){
                newAdapter.clear();
            }
            newAdapter.addAll(shopBean.data);
            newAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void fail() {

    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
