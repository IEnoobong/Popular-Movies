package co.enoobong.popularmovies.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import co.enoobong.popularmovies.R;
import co.enoobong.popularmovies.model.Trailer;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder> {

    public static final String TRAILER = "trailers";
    private Context mContext;
    private ArrayList<Trailer> mTrailers;

    public TrailerAdapter(Context context, ArrayList<Trailer> trailers) {
        mContext = context;
        mTrailers = trailers;
    }

    @Override
    public TrailerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.trailer_item, parent, false);
        return new TrailerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TrailerViewHolder holder, int position) {
        holder.setTrailerName(mContext.getString(R.string.no_trailers));
        holder.setTrailerName(mTrailers.get(position).getName());
    }

    @Override
    public int getItemCount() {
        if (mTrailers == null) return 0;

        return mTrailers.size();
    }

    class TrailerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final TextView mTrailerName;

        public TrailerViewHolder(View itemView) {
            super(itemView);
            mTrailerName = (TextView) itemView.findViewById(R.id.tv_trailer_name);
            itemView.setOnClickListener(this);
        }

        /**
         * sets trailer name
         *
         * @param trailerName name of trailer
         */
        void setTrailerName(final String trailerName) {
            mTrailerName.setText(trailerName);
        }

        @Override
        public void onClick(View v) {
            String videoUrl = mTrailers.get(getAdapterPosition()).getVideoUrl();
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl));
            mContext.startActivity(intent);
        }
    }
}
