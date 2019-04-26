package com.example.demo.views.loadingview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import com.example.demo.R;


/**
 * @name: com.nanyibang.nomi.view
 * @description:
 * @author：Administrator
 * @date: 2017-03-27 10:27
 * @company: 上海若美科技有限公司
 */

public class LoadingAnimationView extends View {

    private static final int INVALIDATE_TIME = 10;
    private static final int DEFAULT_DURING = 300;
    private static final int DEFAULT_RADIUS = 2;
    private static final int DEFAULT_SIDLE_LENGTH = 24;
    private static final int DEFAULT_SPACE = 1;

    private static final int COUNT = 3;

    private int mDuring;
    private float mPointRadius;
    private int mPointColor;

    //边长
    private float mSideLength;
    private float mSpace;

    private float mTranslateDistance;
    private float mRoationDistance;

    private float mTransLateOffset;
    private float mRoationOffset;
    private Bitmap mBitmap;
    private Paint mPaint;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mTransLateOffset = (mTransLateOffset + mTranslateDistance) % mSideLength;
            mRoationOffset = (mRoationOffset + mRoationDistance) % 360;
            invalidate();
        }
    };
    private double mL;

    public LoadingAnimationView(Context context) {
        this(context, null);
    }

    public LoadingAnimationView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingAnimationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LoadingViewAnimation);
        mDuring = a.getInt(R.styleable.LoadingViewAnimation_during, DEFAULT_DURING);
        mPointColor = a.getColor(R.styleable.LoadingViewAnimation_pointColor, getResources().getColor(android.R.color.black));
        mPointRadius = a.getDimension(R.styleable.LoadingViewAnimation_pointRadius, 2);
        mSideLength = a.getDimension(R.styleable.LoadingViewAnimation_sideLength, 48);
        mSpace = a.getDimension(R.styleable.LoadingViewAnimation_space, 2);
        Drawable drawable = a.getDrawable(R.styleable.LoadingViewAnimation_src);
        if (drawable != null) {
            mBitmap = ImageUtil.getBitmapByDrawable(drawable, mSideLength, mSideLength);
        } else {
            mBitmap = ImageUtil.getBitmapByResId(getResources(), R.mipmap.logo, mSideLength, mSideLength);
        }
        init();
    }

    private void init() {
        mL = Math.sqrt(Math.pow(mSideLength, 2) * 2);
        mTranslateDistance = mSideLength / (mDuring * 1.0f / INVALIDATE_TIME);
        mRoationDistance = 90 / (mDuring * 1.0f / INVALIDATE_TIME);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(mPointColor);
        mPaint.setStyle(Paint.Style.FILL);
    }

    public void show() {
        mTransLateOffset = 0;
        mRoationOffset = 0;
        invalidate();
    }

    public void hide() {
        mHandler.removeCallbacksAndMessages(null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension((int) (COUNT * mSideLength + mPointRadius * 2 + getPaddingLeft() + getPaddingRight()),
                (int) (2 * mPointRadius + mSpace + mL + getPaddingBottom() + getPaddingTop() + mSideLength));
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);

        int h = getMeasuredHeight();
        float pointY = h - getPaddingBottom() - mPointRadius - mSideLength / 2;

        //绘制原点
        int w = getMeasuredWidth();
        int i = 0;
        float x = getPaddingLeft() + mPointRadius + i * mSideLength - mTransLateOffset;
        while (x <= w) {
            canvas.drawCircle(x, pointY, mPointRadius, mPaint);
            i++;
            x = getPaddingLeft() + mPointRadius + i * mSideLength - mTransLateOffset;
        }

        //绘制图片
        Matrix matrix = new Matrix();
        float tranX = getPaddingLeft() + mSideLength + mPointRadius;
        float tranY = pointY - mPointRadius - mSpace - mSideLength / 2 -
                ((float) (mL / 2 * Math.sin(Math.toRadians(45 + mRoationOffset % 90))));
        matrix.postRotate(mRoationOffset, mSideLength / 2, mSideLength / 2);
        matrix.postTranslate(tranX, tranY);
        canvas.drawBitmap(mBitmap, matrix, mPaint);
        mHandler.sendEmptyMessageDelayed(0, INVALIDATE_TIME);
    }
}
