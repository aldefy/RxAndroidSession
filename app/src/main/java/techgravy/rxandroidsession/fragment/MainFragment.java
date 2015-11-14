package techgravy.rxandroidsession.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import techgravy.rxandroidsession.R;
import techgravy.rxandroidsession.adapter.MusicAdapter;
import techgravy.rxandroidsession.objects.Result;
import techgravy.rxandroidsession.utils.RecyclerViewItemClickListener;

/**
 * Created by aditlal on 14/11/15.
 */
public class MainFragment extends Fragment {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    MusicAdapter adapter;
    List<Result> resultList;

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
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new MusicAdapter(getActivity(), resultList);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerViewItemClickListener(getActivity(), new RecyclerViewItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //Handle onClick and open the Youtube app and search the video.
            }
        }));
        fetchData(""); // add an artist

    }

    public void fetchData(String artist) {
        //Fetch data here
        //Show a progress
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
