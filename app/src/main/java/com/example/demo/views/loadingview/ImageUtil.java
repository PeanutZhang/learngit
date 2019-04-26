package com.example.demo.views.loadingview;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import static android.graphics.BitmapFactory.decodeFile;

/**
 * Created by Administrator on 2016-07-18.
 */
public class ImageUtil {

    public static String uri2Path(final Context context, final Uri uri ) {
        if ( null == uri ) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if ( scheme == null )
            data = uri.getPath();
        else if ( ContentResolver.SCHEME_FILE.equals( scheme ) ) {
            data = uri.getPath();
        } else if ( ContentResolver.SCHEME_CONTENT.equals( scheme ) ) {
            Cursor cursor = context.getContentResolver().query( uri, new String[] { MediaStore.Images.ImageColumns.DATA }, null, null, null );
            if ( null != cursor ) {
                if ( cursor.moveToFirst() ) {
                    int index = cursor.getColumnIndex( MediaStore.Images.ImageColumns.DATA );
                    if ( index > -1 ) {
                        data = cursor.getString( index );
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    /**
     * 颜色加深处理
     *
     * @param RGBValues
     *            RGB的值，由alpha（透明度）、red（红）、green（绿）、blue（蓝）构成，
     *            Android中我们一般使用它的16进制，
     *            例如："#FFAABBCC",最左边到最右每两个字母就是代表alpha（透明度）、
     *            red（红）、green（绿）、blue（蓝）。每种颜色值占一个字节(8位)，值域0~255
     *            所以下面使用移位的方法可以得到每种颜色的值，然后每种颜色值减小一下，在合成RGB颜色，颜色就会看起来深一些了
     * @return
     */
    public static int colorBurn(int RGBValues) {
        int alpha = RGBValues >> 24;
        int red = RGBValues >> 16 & 0xFF;
        int green = RGBValues >> 8 & 0xFF;
        int blue = RGBValues & 0xFF;
        red = (int) Math.floor(red * (1 - 0.1));
        green = (int) Math.floor(green * (1 - 0.1));
        blue = (int) Math.floor(blue * (1 - 0.1));
        return Color.rgb(red, green, blue);
    }

    public static int resveColor(int RGBValues){
        int alpha = RGBValues >> 24;
        int red = RGBValues >> 16 & 0xFF;
        int green = RGBValues >> 8 & 0xFF;
        int blue = RGBValues & 0xFF;
        red = Math.abs(255 - red);
        green = Math.abs(255 - green);
        blue = Math.abs(255 - blue);
        return Color.rgb(red, green, blue);
    }

    public static Bitmap resizeImage(Bitmap bitmap, float w, float h)
    {
        Bitmap BitmapOrg = bitmap;
        int width = BitmapOrg.getWidth();
        int height = BitmapOrg.getHeight();
        float newWidth = w;
        float newHeight = h;

        float scaleWidth = newWidth / width;
        float scaleHeight = newHeight / height;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // if you want to rotate the Bitmap
        // matrix.postRotate(45);
        return  Bitmap.createBitmap(BitmapOrg, 0, 0, width,
                height, matrix, true);
    }

    public static Bitmap getBitmapByDrawable(Drawable drawable, float w, float h){
        Bitmap bitmap = Bitmap.createBitmap(
                drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(),
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return  resizeImage(bitmap, w, h);
    }

    public static Bitmap getBitmapByResId(Resources resources, int resId, float w, float h){
        return  resizeImage(BitmapFactory.decodeResource(resources, resId), w, h);
    }

    public static String getThumbnailBase64Str(Resources resources, Uri uri, int w, int h ){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;//采集宽高信息 ，不往内存加载
        decodeFile(uri.getPath(), options);//返回空bitmap ，options保存了宽高

        int fW = 0;
        int fH = 0;
        if (w > 0 && h > 0) {
            if (w >= h ){
                fW = w;
                Log.e("zyh",String.format("获取到的options.outHeight: %1$d, potions.outWidth-> %2$d",options.outHeight,options.outWidth));
                fH = (int) (w * options.outHeight * 1.0f / options.outWidth);
                Log.e("zyh","inGetThumBanBase64-calcuate-> ");
            }else {
                fH = h;
                fW = (int) (options.outWidth * h * 1.0f / options.outHeight);
            }
        }
        options.inSampleSize =(options.outHeight/fH + options.outWidth/fW) / 2;
        options.inJustDecodeBounds = false;
        Bitmap bitmap = decodeFile(uri.getPath(), options);
        //得到缩略图

        bitmap = ThumbnailUtils.extractThumbnail(bitmap, fW, fH);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        String imgageBase64 = Base64.encodeToString(byteArray, Base64.NO_WRAP);
        return  "data:image/png;base64," + imgageBase64;
    }

    public static Bitmap decodeSampleBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight){
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int outWidth = options.outWidth;
        int outHeight = options.outHeight;
        int sampleSize = 1;
        if (outWidth > reqWidth || outHeight > reqHeight) {
            while ((outWidth / sampleSize) > reqWidth && (outHeight / sampleSize) > reqHeight) {
                sampleSize *= 2;
            }
        }
        return sampleSize;
    }

   public static  Uri compressPic(Activity activity, Uri uri, int max){
        Log.e("zyh","--uri-"+uri.getPath());
       final BitmapFactory.Options options = new BitmapFactory.Options();
       options.inJustDecodeBounds = true;
       BitmapFactory.decodeFile(uri.getPath(), options);
       int simpleSize = calculateInSampleSize(options, max, max);
       if (simpleSize > 1){
           options.inSampleSize = simpleSize;
           options.inJustDecodeBounds = false;
           Bitmap bitmap = BitmapFactory.decodeFile(uri.getPath(), options);
           Uri result =constructImageUriByType(activity, "carmea");
           File file = new File(result.getPath());
           if (file != null) {
               saveBitmap(bitmap, file);
               Log.e("zyh","构造result---> "+result.getPath());
               return result;
           }
       }
       return uri;
   }
    public static Uri constructImageUriByType(Context context, String type){
        return Uri.fromFile(new File(getImageDirectoryByType(context, type), Calendar
                .getInstance().getTimeInMillis() + ".jpg"));
    }
    public static File getImageDirectoryByType(Context context, String type){
        File tempDirectory = new File(Environment.getExternalStorageDirectory()
                + "/" + context.getPackageName() + "/" + type + "/");// 自已项目 文件夹
        if (!tempDirectory.exists()) {
            tempDirectory.mkdirs();
        }
        return tempDirectory;
    }
    /** 保存方法 */
    public static void saveBitmap(Bitmap bm, File file) {
        try {
            FileOutputStream out = new FileOutputStream(file);
            bm.compress(Bitmap.CompressFormat.JPEG, 80, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
}
}
