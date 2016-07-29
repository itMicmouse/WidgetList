package com.example;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by yakun on 2016/7/29.
 */
public class Transforming {
    public static void main(String[] args) {
        buffer();
    }
    public static void buffer(){
        final String[] mails = new String[]{"Here is an email!", "Another email!", "Yet another email!"};
        Observable<String> endlessMail = Observable.create(new Observable.OnSubscribe<String>(){

            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    if (subscriber.isUnsubscribed()) return;
                    Random random = new Random();
                    while (true) {
                        String mail = mails[random.nextInt(mails.length)];
                        subscriber.onNext(mail);
                        Thread.sleep(1000);
                    }
                } catch (Exception ex) {
                    subscriber.onError(ex);
                }
            }
        }).subscribeOn(Schedulers.io());
        endlessMail.buffer(3, TimeUnit.SECONDS).subscribe(new Action1<List<String>>() {
            @Override
            public void call(List<String> list) {

                System.out.println(String.format("You've got %d new messages!  Here they are!", list.size()));
                for (int i = 0; i < list.size(); i++)
                    System.out.println("**" + list.get(i).toString());
            }
        });
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void taken(){
    }
}
