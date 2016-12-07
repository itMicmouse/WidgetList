package com.example;

import java.awt.SystemColor;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by yakun on 2016/11/14.
 */

public class Merge {
    public static void main(String[] args) throws InterruptedException {
        Observable<Integer> odds = Observable.just(1, 3, 5).subscribeOn(Schedulers.newThread());
        Observable<Integer> evens = Observable.just(2, 4, 6);
        Observable<Integer> integerObservable = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                try {
                    Thread.sleep(16000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                subscriber.onNext(18);
                subscriber.onCompleted();
            }
        });

        Observable.merge(odds, evens, integerObservable).timeout(15, TimeUnit.SECONDS, Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                subscriber.onNext(110);
                subscriber.onCompleted();
            }
        }), Schedulers.newThread())
                .subscribe(new Subscriber<Integer>() {
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
        Thread.sleep(20000);
    }
}
