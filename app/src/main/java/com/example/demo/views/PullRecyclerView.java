package com.example.demo.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import io.socket.emitter.Emitter;

/**
 * @Package: com.example.demo.views
 * @Description:
 * @Author: zyh
 * @CreateDate: 2020/3/31
 * @company: 上海若美科技有限公司
 */
public class PullRecyclerView extends RecyclerView {

    private int mthreshold = 120;
    private float mStartY = 0;
    private float mScrollDistance;
    private boolean pullRefresEnable = true;
    //是否往下拉
    private boolean isPullDown;
    private LinearLayoutManager mLayoutManager;
    private PullScrollListener mPullListener;


    public PullRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void addPullListener(PullScrollListener listener) {
        this.mPullListener = listener;
    }

    public void setPullRefresEnable(boolean enable) {
        this.pullRefresEnable = enable;
    }

    public void setMthreshold(int threshold){
        this.mthreshold = threshold > mthreshold ? threshold: mthreshold;
    }


    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);

        if (pullRefresEnable) {
            if (state == RecyclerView.SCROLL_STATE_IDLE) {
                mLayoutManager = (LinearLayoutManager) getLayoutManager();
                if (null != mLayoutManager && mLayoutManager.findFirstVisibleItemPosition() == 0 && mScrollDistance > mthreshold && isPullDown) {
                    mScrollDistance = 0;
                    isPullDown = false;
                    if (null != mPullListener) {
                        mPullListener.onPullDown();
                    }
                }
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {

        if (pullRefresEnable) {
            switch (e.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mStartY = e.getY();
                    break;
                case MotionEvent.ACTION_UP:
                    mScrollDistance = e.getY() - mStartY;
                    isPullDown = mScrollDistance > 0;
                    break;
                default:
                    break;
            }
        }
        return super.onTouchEvent(e);
    }

    public interface PullScrollListener {
        /**
         * 下拉
         */
        void onPullDown();

        /**
         * 上拉
         */
        void onPullUp();
    }
}
