package techgravy.rxandroidsession.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import techgravy.rxandroidsession.R;
import techgravy.rxandroidsession.objects.Result;

/**
 * Created by aditlal on 13/10/15.
 * Adapts the music {@Code Result} class to our {@code RecyclerView}
 */
public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder> {
    private List<Result> results;
    private Context mContext;

    public MusicAdapter(Context mContext, List<Result> results) {
        this.mContext = mContext;
        this.results = results;
    }

    @Override
    public MusicAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_music, null);
        return new ViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.track.setText(results.get(position).getTrackName());
        int millis = results.get(position).getTrackTimeMillis();
        viewHolder.time.setText(String.valueOf(String.format("%d min, %d sec",
                TimeUnit.MILLISECONDS.toMinutes(millis),
                TimeUnit.MILLISECONDS.toSeconds(millis) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))
        )));
        String url = results.get(position).getArtworkUrl100();
        url = url.replace("100x100bb", "600x600bb");
        Picasso.with(mContext).load(url)
                .fit().placeholder(R.drawable.ic_music_album).error(R.drawable.ic_music_album).into(viewHolder.albumArt);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_music_track)
        public TextView track;
        @Bind(R.id.item_music_time)
        public TextView time;
        @Bind(R.id.item_music_image)
        public ImageView albumArt;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            ButterKnife.bind(this, itemLayoutView);
        }
    }

    @Override
    public int getItemCount() {
        return results.size();
    }
}