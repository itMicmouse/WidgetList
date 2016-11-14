package com.example;

import java.awt.SystemColor;

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

        Observable.merge(odds, evens)
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
        Thread.sleep(2000);
    }
}
