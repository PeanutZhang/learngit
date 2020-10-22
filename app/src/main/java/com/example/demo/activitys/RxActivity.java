package com.example.demo.activitys;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.demo.R;
import com.taobao.tlog.adapter.TLogFileUploader;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);
        Observable observable1 =Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                    emitter.onNext("observable1");
                    Log.e("zyh","observable1-emitt-----");
            }
        });
        Observable observable2 = Observable.create(emitter -> {
            SystemClock.sleep(5000);
            emitter.onError(new Throwable("sssss"));
            Log.e("zyh","observable2 emitter--->   "+Thread.currentThread().getName());
        }).onErrorReturn((Function<Throwable, String>) throwable -> "catch错误信息").subscribeOn(Schedulers.io());

       findViewById(R.id.zip).setOnClickListener(v->{
           Observable.zip(observable1, observable2, new BiFunction<String, String, String>() {
               @Override
               public String apply(String s, String s2) throws Exception {
                   return s+ "   "+s2;
               }
           }).observeOn(AndroidSchedulers.mainThread())
                   .subscribe(o -> {
                       Log.e("zyh","-accept- "+o.toString()+ Thread.currentThread().getName());
                   }, throwable -> {
                       Log.e("zyh","onerror: ");
                   });
       });
    }

}
