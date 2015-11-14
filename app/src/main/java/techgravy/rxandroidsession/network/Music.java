package techgravy.rxandroidsession.network;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import techgravy.rxandroidsession.objects.NetworkResponse;
import techgravy.rxandroidsession.objects.Result;

/**
 * Created by aditlal on 17/10/15.
 */
public class Music {
    public static Observable<NetworkResponse> createMusicResponse(final String artist) {

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
                    NetworkResponse networkResponse = MusicApi.getApi().getMusic(artist);
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
                try {
                    String lhsString = lhs.getReleaseDate();
                    lhsString = lhsString.split("T")[0];
                    String rhsString = rhs.getReleaseDate();
                    rhsString = rhsString.split("T")[0];
                    // Date : 2008-06-10
                    DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
                    Date lhsDate = df.parse(lhsString);
                    Date rhsDate = df.parse(rhsString);

                    return rhsDate.compareTo(lhsDate);
                } catch (ParseException e) {
                    return rhs.getTrackTimeMillis() - lhs.getTrackTimeMillis();
                }
            }
        });
        return list;
    }
}
