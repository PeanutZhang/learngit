package com.example.demo.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {


    private List<T> mdatas;
    private OnItemClickListener mItemClickListener;
    private OnItemLongClickListener mItemLongClickListener;

    public BaseAdapter(List<T> mdatas) {

        this.mdatas = mdatas == null ?new ArrayList<>():mdatas;
    }

    protected abstract BaseViewHolder onCreateVieiwHolder(View parent, int viewType);


    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewtype) {

       final BaseViewHolder viewHolder = onCreateVieiwHolder(viewGroup,viewtype);
         if(null !=mItemClickListener){
             viewHolder.itemView.setOnClickListener(v -> mItemClickListener.onItemClick(viewHolder.getAdapterPosition(), viewHolder.itemView));
         }
         if(null !=mItemLongClickListener){
             viewHolder.itemView.setOnLongClickListener(v -> mItemLongClickListener.onItemLongClick(viewHolder.getAdapterPosition()));
         }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, int position) {
          baseViewHolder.setData(getItem(position),position);
    }

    @Override
    public int getItemCount() {
        return mdatas.size();
    }

    public T getItem(int position){
        return mdatas.get(position);
    }

    public interface OnItemClickListener {
        void onItemClick(int position, View view);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mItemClickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener){
        this.mItemLongClickListener = listener;
    }
}
