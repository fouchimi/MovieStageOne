package com.example.ousmane.movies;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailMovieFragment extends Fragment {
    private Movie movie;

    public DetailMovieFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle != null && bundle.containsKey(Constants.MOVIE_KEY.getValue())) {
            movie = bundle.getParcelable(Constants.MOVIE_KEY.getValue());
        }else {
            movie = savedInstanceState.getParcelable(Constants.MOVIE_KEY.getValue());
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.getParcelable(Constants.MOVIE_KEY.getValue());
        super.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_movie, container, false);
        if(movie != null) {
            TextView mTitleTextView = (TextView) view.findViewById(R.id.detail_title);
            mTitleTextView.setText(movie.getTitle());

            TextView mYearTextView = (TextView) view.findViewById(R.id.detail_year);
            mYearTextView.setText("Year: " + getYear(movie.getDate()));

            TextView mRatingTextView = (TextView) view.findViewById(R.id.detail_rating);
            mRatingTextView.setText("Rating: " + movie.getRating() + "/10");

            TextView mSynopsisTextView = (TextView) view.findViewById(R.id.detail_synopsis);
            mSynopsisTextView.setText("Description: " + movie.getSynopsis());

            ImageView mThumbnailImageView = (ImageView) view.findViewById(R.id.detail_thumbnail);
            Picasso.with(getActivity())
                    .load(movie.getImage())
                    .into(mThumbnailImageView);
        }
        return view;
    }

    private String getYear(String date) {
        return date.split("-")[0];
    }

}
