package com.liulije.readerdemo.utils;

import com.liulije.readerdemo.base.RxEvent;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * @类名称: CLASS
 * @类描述:
 * @创建人：LiuL1Jie
 * @创建时间：2017/8/14 14:36
 * @备注：
 */
public class RxBus2 {
    private static volatile RxBus2 myRxBus;
    private static CompositeDisposable compositeDisposable;
    private static doEevent dListener;

    private RxBus2() {
    }

    public static RxBus2 getDefaultRxBus() {
        if (myRxBus == null) {
            synchronized (RxBus2.class) {
                if (myRxBus == null) {
                    compositeDisposable = new CompositeDisposable();
                    myRxBus = new RxBus2();
                }
            }
        }
        return myRxBus;
    }

    private Subject<Object> _bus = PublishSubject.create().toSerialized();

    public void post(Object o) {
        _bus.onNext(o);
    }

    public boolean hasOBservable() {
        return _bus.hasObservers();
    }

    public <RxEvent> void toObservable(Class<RxEvent> type) {
        _bus.ofType(type).subscribe(new Observer<RxEvent>() {
            @Override
            public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(@io.reactivex.annotations.NonNull RxEvent t) {
                dListener.onNext((com.liulije.readerdemo.base.RxEvent) t);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


    public <T> Observable<T> toObservable1(Class<T> type) {
        return _bus.ofType(type);
    }


    public interface doEevent {

        void onNext(RxEvent t);
    }

    public static void clear() {
        compositeDisposable.clear();// 取消所有订阅

    }

}
