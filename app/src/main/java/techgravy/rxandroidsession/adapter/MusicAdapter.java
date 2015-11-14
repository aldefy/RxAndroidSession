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
        viewHolder.track.setText(results.get(position).getArtistName() + ", " + results.get(position).getTrackName());
        viewHolder.time.setText(String.valueOf(results.get(position).getTrackTimeMillis()));

        Picasso.with(mContext).load(results.get(position).getArtworkUrl60())
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