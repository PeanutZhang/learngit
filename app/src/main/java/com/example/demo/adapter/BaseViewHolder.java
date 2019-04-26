package com.example.demo.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {


    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);

    }

    /**
     * 綁定數據
     * @param data
     * @param postion
     */
    abstract void setData(T data,int postion);

}
