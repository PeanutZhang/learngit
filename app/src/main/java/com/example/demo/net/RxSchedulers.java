package com.example.demo.net;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @Package: com.nanyibang.rm.net
 * @Description:
 * @Author: zyh
 * @CreateDate: 2019/4/29
 * @company: 上海若美科技有限公司
 */
public class RxSchedulers {
    public static <T> ObservableTransformer<T, T> io_main() {
        return upstream ->
                upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
    }

}
