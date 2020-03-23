package com.bawei.wushuai20200223.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.wushuai20200223.R;
import com.bawei.wushuai20200223.bean.NewBean;
import com.bawei.wushuai20200223.bean.UserBean;
import com.bawei.wushuai20200223.util.NetUtil;

import java.util.ArrayList;
import java.util.List;

public class NewAdapter extends BaseAdapter {
    private List<UserBean> list = new ArrayList<>();
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout,viewGroup,false);
            holder = new ViewHolder();
            holder.images = view.findViewById(R.id.images);
            holder.price = view.findViewById(R.id.price);
            holder.title = view.findViewById(R.id.tetil);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        UserBean dataBean = list.get(i);
        holder.title.setText(dataBean.goods_name);
        holder.price.setText(dataBean.currency_price);
        NetUtil.getInstance().getPost(dataBean.goods_thumb,holder.images);
        return null;
    }

    public void addAll(List<UserBean> dataBeans){
        list.addAll(dataBeans);
    }

    public void clear() {
        list.clear();
    }

    class ViewHolder{
        public ImageView images;
        public TextView title,price;
    }
}
