package com.lecai.quwen.AndroidRX;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class RxBus {
    private static volatile RxBus mInstance;
    private final Subject<Object> subject = PublishSubject.create().toSerialized();
    private Disposable dispoable;


    private RxBus() {
    }

    public static RxBus getInstance() {
        if (mInstance == null) {
            synchronized (RxBus.class) {
                if (mInstance == null) {
                    mInstance = new RxBus();
                }
            }
        }
        return mInstance;
    }


    /**
     * 发送事件
     *
     * @param object
     */
    public void send(Object object) {
        subject.onNext(object);
    }


    /**
     * @param classType
     * @param <T>
     * @return
     */
    public <T> Observable<T> toObservale(Class<T> classType) {
        return subject.ofType(classType);
    }


    /**
     * 订阅
     *
     * @param bean
     * @param consumer
     */
    public void subscribe(Class bean, Consumer consumer) {
        dispoable = toObservale(bean).observeOn(AndroidSchedulers.mainThread()).subscribe(consumer);
    }

    /**
     * 取消订阅
     */
    public void unSubcribe() {
        if (dispoable != null && !dispoable.isDisposed()) {
            dispoable.dispose();
            // subject.onComplete();
            Log.i("WXEntryActivity_TAG", "unSubcribe");
        }

    }

}

