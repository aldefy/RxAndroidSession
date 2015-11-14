package techgravy.rxandroidsession.network;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cc.soham.rxblrdroid.objects.NetworkResponse;
import cc.soham.rxblrdroid.objects.Result;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by aditlal on 17/10/15.
 */
public class Music {
    public static Observable<NetworkResponse> createMusicResponse() {

        /* One way of doing it
        Observable.create(new Observable.OnSubscribe<NetworkResponse>() {
            @Override
            public void call(Subscriber<? super NetworkResponse> subscriber) {

            }
        });*/

        return Observable.create(new Observable.OnSubscribe<NetworkResponse>() {
            @Override
            public void call(Subscriber<? super NetworkResponse> subscriber) {
                try {
                    NetworkResponse networkResponse = MusicApi.getApi().getMusic("Iron Maiden");
                    subscriber.onNext(networkResponse);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });

    }

    public static List<Result> sort(List<Result> list) {
        Collections.sort(list, new Comparator<Result>() {
            @Override
            public int compare(Result lhs, Result rhs) {
                return rhs.getTrackTimeMillis() - lhs.getTrackTimeMillis();
            }
        });
        return list;
    }
}
