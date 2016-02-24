package com.mitsw.androidplayground.utils.log;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;

/**
 * Created by Hill on 2016/2/19.
 */



public class RxSubjectHelper {

    private static RxSubjectHelper instance;

    private PublishSubject<String> subject = PublishSubject.create();

    public static RxSubjectHelper instanceOf() {
        if (instance == null) {
            instance = new RxSubjectHelper();
        }
        return instance;
    }

    /**
     * Pass a String down to event listeners.
     */
    public void setString(String string) {
        subject.onNext(string);
    }

    /**
     * Subscribe to this Observable. On event, do something e.g. replace a fragment
     */
    public Observable<String> getStringObservable() {
        return subject;
    }

}