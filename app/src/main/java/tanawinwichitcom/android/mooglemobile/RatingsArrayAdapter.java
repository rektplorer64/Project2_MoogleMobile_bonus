package tanawinwichitcom.android.mooglemobile;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Map;

import tanawinwichitcom.android.mooglemobile.moviefetcher.Movie;
import tanawinwichitcom.android.mooglemobile.moviefetcher.Rating;

/**
 * Created by tanaw on 3/24/2018.
 */

public class RatingsArrayAdapter extends RecyclerView.Adapter<RatingsArrayAdapter.ViewHolder>{
    private Context context;
    private ArrayList<Rating> ratings;

    public RatingsArrayAdapter(Context context, Movie movie){
        this.context = context;
        ratings = new ArrayList<>(movie.getRating().values());
    }

    @Override
    public RatingsArrayAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View rootView = LayoutInflater.from(context).inflate(R.layout.ratingcard_layout, parent, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(RatingsArrayAdapter.ViewHolder holder, int position){
        Rating userRatingEntry = ratings.get(position);
        holder.userID.setText(Integer.toString(userRatingEntry.getUser().getID()));
        holder.ratingBar.setRating((float) userRatingEntry.getRating());
        holder.score.setText(String.format("%.1f ", userRatingEntry.getRating()));

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(userRatingEntry.getTimestamp());

        holder.reviewDate.setText(" • " + formatter.format(calendar.getTime()));

        holder.reviewText.setEllipsize(TextUtils.TruncateAt.END);
        holder.reviewText.setMaxLines(4);
        //reviewText.setText();
    }

    @Override
    public int getItemCount(){
        return ratings.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView userID;
        private RatingBar ratingBar;
        private TextView score;
        private TextView reviewDate;
        private TextView reviewText;

        public ViewHolder(View itemView){
            super(itemView);
            userID = itemView.findViewById(R.id.userID);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            score = itemView.findViewById(R.id.score);
            reviewDate = itemView.findViewById(R.id.reviewDate);
            reviewText = itemView.findViewById(R.id.reviewText);
        }
    }
}
