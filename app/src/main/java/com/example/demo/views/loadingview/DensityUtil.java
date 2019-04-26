package com.example.demo.views.loadingview;

import android.content.Context;
import android.util.TypedValue;

/**
 * @DESCRIBE dip 和 px 转换帮助类
 */
public class DensityUtil {

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		try {
			final float scale = context.getResources().getDisplayMetrics().density;
			return (int) (dpValue * scale + 0.5f);
		} catch (Exception e) {
		}
		return 200;
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	public static float sp2px(Context context, float spValue) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue,
				context.getResources().getDisplayMetrics());
	}

	public static float dp2px(Context context, float spValue) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, spValue,
				context.getResources().getDisplayMetrics());
	}

	public static float px2sp(Context context, float pxValue) {

		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}

}
