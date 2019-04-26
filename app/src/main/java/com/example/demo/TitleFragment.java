package com.example.demo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.demo.beans.MessageEvent;
import com.example.demo.beans.Test;

import org.greenrobot.eventbus.EventBus;


public class TitleFragment extends Fragment {


    private String mParam1;
    static String  tag = "zyh";
    private boolean isVisible = false;
    private boolean isInitView = false;
    private boolean isHasLoadData =false;
    private TextView textView;
    private  int  mCViewtimes = 0;

    public TitleFragment() {
        // Required empty public constructor
    }

    private static final String ARG_PARAM1 = "tag1";
    // TODO: Rename and change types and number of parameters
    public static TitleFragment newInstance(String param1) {
        TitleFragment fragment = new TitleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            Log.e(tag,"--onCreate---"+mParam1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(null !=savedInstanceState){
            String zj = savedInstanceState.getString("index");
            Log.e(tag,mParam1+ "saveInstance--!=null--->  " +zj);

        }
        View view = inflater.inflate(R.layout.fragment_title, container, false);
        textView = view.findViewById(R.id.ftv);
        textView.setText(mParam1);
        if(TextUtils.equals("index0",mParam1)){
            textView.setOnClickListener(v -> {
                Test test = new Test();
                test.name ="fighting";
                EventBusUtil.postStickEvent(5,test);
            });
        }
        Log.e(tag,"onCreateView--> "+mParam1+" mcviewTimes  "+mCViewtimes++);
        isInitView = true;
        isCanLoadData();
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
      Log.e(tag,mParam1+"  onSaveInstance--> ");
      outState.putString("zj","foromeSave");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.e(tag,"setUserViewHint--> "+ mParam1 +" "+isVisibleToUser);
        if(isVisibleToUser){
            isVisible = true;
            isCanLoadData();
        }else {
            isVisible = false;
        }
    }

    private void isCanLoadData() {

        if(isVisible && isInitView && !isHasLoadData){
            lazyLoadData();
            isHasLoadData = true;
        }

    }

    private void lazyLoadData() {
        // TODO: 2019/4/25 写懒加载逻辑
        Log.e(tag,"layLoaddata--> "+mParam1);
        textView.setText(mParam1+" 已经加载过数据");
        mCViewtimes++;


    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(tag,"onPause---------"+mParam1);

    }

    @Override
    public void onStop() {
        super.onStop();
     Log.e(tag,"onStop---------"+mParam1);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
      Log.e(tag,"onDestory--> "+mParam1);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e(tag,"onDestoryView----"+mParam1);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.e(tag,"onViewStateRestored----"+ mParam1);

    }
}
