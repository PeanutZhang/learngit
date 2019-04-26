/**
 * @Title: LoadingView.java
 * @Package com.hongsong.korenpine.view
 * @author dkslbw@gmail.com
 * @date 2015年7月14日 下午5:43:55
 * @version V1.0
 */
package com.example.demo.views.loadingview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.example.demo.R;
import com.example.demo.views.loadingview.LoadingAnimationView;



public class LoadingView extends FrameLayout {


    private LoadingAnimationView mLoadingAnimationView;

    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.widget_loading, this);
        this.setVisibility(View.GONE);
        mLoadingAnimationView = (LoadingAnimationView) view.findViewById(R.id.loading_animation_view);
    }

    public void show() {
        if (this.getVisibility() == View.GONE) {
            mLoadingAnimationView.show();
            this.setVisibility(View.VISIBLE);
            invalidate();
        }
    }

    public void hide() {
        if (this.getVisibility() == View.VISIBLE) {
            mLoadingAnimationView.hide();
            setVisibility(View.GONE);
        }
    }


}

