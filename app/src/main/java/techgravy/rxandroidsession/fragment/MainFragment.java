package techgravy.rxandroidsession.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import techgravy.rxandroidsession.R;
import techgravy.rxandroidsession.adapter.MusicAdapter;
import techgravy.rxandroidsession.network.Music;
import techgravy.rxandroidsession.objects.NetworkResponse;
import techgravy.rxandroidsession.objects.Result;
import techgravy.rxandroidsession.utils.IntentUtils;
import techgravy.rxandroidsession.utils.RecyclerViewItemClickListener;

/**
 * Created by aditlal on 14/11/15.
 */
public class MainFragment extends Fragment {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    MusicAdapter adapter;
    List<Result> resultList;
    ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, rootView);
        init();
        return rootView;
    }

    private void init() {
        resultList = new ArrayList<>();
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading");
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new MusicAdapter(getActivity(), resultList);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerViewItemClickListener(getActivity(), new RecyclerViewItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(), "Loading video", Toast.LENGTH_SHORT).show();
                IntentUtils.openYoutubeVideoSearch(getActivity(), resultList.get(position).getTrackName() + " " + resultList.get(position).getArtistName());
            }
        }));
        fetchData("Linkin Park");

    }

    public void fetchData(String artist) {
        progressDialog.show();
        Music.createMusicResponse(artist)
                .map(new Func1<NetworkResponse, List<Result>>() {
                    @Override
                    public List<Result> call(NetworkResponse networkResponse) {
                        return networkResponse.getResults();
                    }
                })
                .subscribeOn(Schedulers.io()) // What is io and newThread
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<List<Result>, List<Result>>() {
                    @Override
                    public List<Result> call(List<Result> list) {
                        return Music.sort(list);
                    }
                })
                .subscribe(new Subscriber<List<Result>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<Result> results) {
                        resultList.clear();
                        resultList.addAll(results);
                        progressDialog.cancel();
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
