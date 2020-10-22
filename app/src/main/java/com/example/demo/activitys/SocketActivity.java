package com.example.demo.activitys;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demo.BuildConfig;
import com.example.demo.DemoService;
import com.example.demo.R;
import com.example.demo.broadrecieve.TestBroadcastReciver;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.common.ImageDecodeOptionsBuilder;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.URISyntaxException;

import io.socket.client.Ack;
import io.socket.client.IO;
import io.socket.client.Socket;

public class SocketActivity extends AppCompatActivity {

    private static final String TAG = "zyh";
    protected ImageView imageview1;
    protected ImageView imageview2;
    private TextView msg;
    private Socket socket;
    private ServiceConnection mconnection;
    private DemoService demoService;
    private TestBroadcastReciver mreciver;
    private SimpleDraweeView sdv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_socket);
        msg = findViewById(R.id.msg);
        String token = "%7B%22member_id%22%3A%221806128%22%2C%22time%22%3A1587465632%2C%22identity%22%3A2%2C%22token%22%3A%223b04ef9a43dc0286f3b8a5efb7f521baa3eadb315841080dd170d11595fb22f4%22%7D";
        try {
            IO.Options opts = new IO.Options();
            opts.forceNew = false;
            opts.reconnection = true;
            opts.reconnectionDelay = 3000;
            opts.reconnectionDelayMax = 5000;
            opts.query = token;
            socket = IO.socket("http://192.168.72.166:3120", opts);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        mconnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                DemoService.CountBinder countBinder = (DemoService.CountBinder) service;
                demoService = countBinder.getDemoService();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        initView();

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void event(View view) {
        switch (view.getId()) {
            case R.id.connect:
                socket.connect();
                socket.on(Socket.EVENT_CONNECT, args -> {

                    Log.e("zyh", "onpen  thread " + Thread.currentThread().getName());
                    runOnUiThread(() -> {
                        msg.setText("连接成功");
                    });
                })

                        .on(Socket.EVENT_CONNECT_ERROR, args -> {
                            Log.e("zyh", "连接错误--  " + args[0].toString());
                        })
                        .on(Socket.EVENT_CONNECT_TIMEOUT, args ->
                                Log.e("zyh", "event: 连接chaos " + args[0].toString()))
                        .on(Socket.EVENT_DISCONNECT, args -> {
                            runOnUiThread(() -> {
                                msg.setText("断开连接");
                            });
                        }).on(Socket.EVENT_MESSAGE, args -> {
                    Log.e(TAG, "event:message   " + args[0].toString());
                }).on("sendNotice", args -> {
                    Log.e(TAG, "event:message   " + args[0].toString());

                })
                ;
                Log.e("zyh", "点击了连接");
                break;
            case R.id.send:
                socket.emit("msg", new Ack() {
                    @Override
                    public void call(Object... args) {
                        runOnUiThread(() -> {
                            msg.setText(args[0].toString());
                        });
                    }
                });
                break;
            case R.id.listener:
//                  socket.on("msg", args -> {
//                      Log.e("zyh","on msg  thread "+Thread.currentThread().getName());
//                  });
                mreciver = new TestBroadcastReciver();
                IntentFilter filter = new IntentFilter();
                filter.addAction(BuildConfig.APPLICATION_ID + "czzz");
                registerReceiver(mreciver, filter);

                break;
            case R.id.discontect:
//                  socket.disconnect();
                Log.e(TAG, "event: 点击了断开");
                Intent e = new Intent();
                e.setAction(BuildConfig.APPLICATION_ID + "czzz");
                sendBroadcast(e);
                break;

            case R.id.bind:
                Intent intent = new Intent(this, DemoService.class);
                bindService(intent, mconnection, BIND_AUTO_CREATE);

                break;
            case R.id.getcount:
//
                if (null != demoService) {
                    int count = demoService.getCount();
                    Log.e(TAG, "event: act counts:  " + count);
                }

                break;
            case R.id.shownotify:
                if (null != demoService) {
                    demoService.showNotifiction();
                }
//                  Intent i = new Intent(this, DemoService.class);
//                  startForegroundService(i);                break;

            case R.id.clearnotify:
                if (demoService != null) {
                    demoService.clearNotification();
                }
                break;
            case R.id.display:
//                Bitmap bitmap1 = decodeRes(Bitmap.Config.ARGB_8888);
                Bitmap bitmap2 = decodeRes(Bitmap.Config.RGB_565);
//                Bitmap bm3 = decodeRes(Bitmap.Config.ALPHA_8);
//                imageview1.setImageBitmap(bitmap1);
                imageview2.setImageBitmap(bitmap2);
//                ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                bm3.compress(Bitmap.CompressFormat.PNG,100,baos);
//                Log.e("zyh",String.format("baos.length: %1$d  bitmapgetcount: %2$d bm1= %3$d  bbm2= %4$d ",baos.toByteArray().length,bitmap2.getByteCount(),bitmap1.hashCode(),bitmap2.hashCode()));
                break;
            case R.id.frescoshow:
                ImageRequestBuilder imageRequestBuilder = ImageRequestBuilder.newBuilderWithResourceId(R.drawable.anita);
                ImageDecodeOptions options = new ImageDecodeOptions(new ImageDecodeOptionsBuilder().setBitmapConfig(Bitmap.Config.RGB_565));
                imageRequestBuilder.setImageDecodeOptions(options);
                PipelineDraweeControllerBuilder builder = Fresco.getDraweeControllerBuilderSupplier().get();
                ImagePipelineFactory imagePipelineFactory = Fresco.getImagePipelineFactory();
                try {
                    ImagePipelineConfig config = (ImagePipelineConfig) getPrivateField(imagePipelineFactory, "mConfig");
                    Bitmap.Config bconfig= (Bitmap.Config) getPrivateField(config,"mBitmapConfig");
                    Log.e("zyh","getPrivateConfig "+bconfig.name());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                DraweeController controller = sdv.getController();
                builder.setOldController(sdv.getController());
                builder.setImageRequest(imageRequestBuilder.build());
sdv.setImageResource(R.drawable.anita);
                break;
            default:
                break;
        }
    }

    public static Object getPrivateField(Object instance, String filedName) throws NoSuchFieldException, IllegalAccessException {
        Field field = instance.getClass().getDeclaredField(filedName);
        field.setAccessible(true);
        return field.get(instance);
    }

    private Bitmap decodeRes(Bitmap.Config config){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = config;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.anita, options);
        return  bitmap;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mconnection!=null)unbindService(mconnection);
        if (null != mreciver) unregisterReceiver(mreciver);
    }

    private void initView() {
        msg = (TextView) findViewById(R.id.msg);
        imageview1 = (ImageView) findViewById(R.id.imageview1);
        imageview2 = (ImageView) findViewById(R.id.imageview2);
        sdv = findViewById(R.id.sdv1);
    }
}
