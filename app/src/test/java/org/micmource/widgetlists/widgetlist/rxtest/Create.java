package org.micmource.widgetlists.widgetlist.rxtest;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;

import static org.junit.Assert.assertEquals;

/**
 * @author yangyakun
 * @date 2016/3/15
 * @email 921555613@qq.com
 */
public class Create {
    /**
     * 打Tag 专用
     */
    public static final String TAG = Create.class.getSimpleName();

    @Before
    public void getTAG() {

    }

    @Test
    public void create() throws Exception {
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> observer) {
                try {
                    if (!observer.isUnsubscribed()) {
                        for (int i = 1; i < 5; i++) {
                            observer.onNext(i);
                        }
                        observer.onCompleted();
                    }
                } catch (Exception e) {
                    observer.onError(e);
                }
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onNext(Integer item) {
                System.out.println("Next: " + item);
            }

            @Override
            public void onError(Throwable error) {
                System.err.println("Error: " + error.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("Sequence complete.");
            }
        });
    }

    int i = 0;

    /**
     *  defer操作符是直到有订阅者订阅时，才通过Observable的工厂方法创建Observable并执行，defer操作符能够保证Observable的状态是最新的，其流程实例如下：
     * @throws Exception 异常
     */
    @Test
    public void defertest() throws Exception {
        i = 10;
        Observable justObservable = Observable.just(i);
        i = 12;
        Observable deferObservable = Observable.defer(new Func0<Observable<Object>>(){
            @Override
            public Observable<Object> call() {
                return Observable.just((Object) i);
            }
        });
        i = 15;

        justObservable.subscribe(new Subscriber() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {
                System.out.println("just result:" + o.toString());
            }
        });

        deferObservable.subscribe(new Subscriber() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {
                System.out.println("defer result:" + o.toString());
            }
        });
    }
    @Test
    public void fromtest() throws Exception {
        Integer[] items = {1,2,3,4,6,8,7};
        Observable<Integer> from = Observable.from(items);
        from.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("item:" + integer);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                System.out.println("Throwable:" + throwable.getMessage());
            }
        }, new Action0() {
            @Override
            public void call() {
                System.out.println("\"Sequence complete\"");
            }
        });
    }
    @Test
    public void Intervaltest() throws Exception {

    }

}
