package com.example.ousmane.movies;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {
    private TextView mTitleTextView;
    private TextView mYearTextView;
    private TextView mRatingTextView;
    private TextView mSynopsisTextView;
    private ImageView mThumbnailImageView;
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mTitleTextView = (TextView) findViewById(R.id.detail_title);
        mYearTextView = (TextView) findViewById(R.id.detail_year);
        mRatingTextView = (TextView) findViewById(R.id.detail_rating);
        mSynopsisTextView = (TextView) findViewById(R.id.detail_synopsis);
        mThumbnailImageView = (ImageView) findViewById(R.id.detail_thumbnail);

        Bundle b = getIntent().getExtras();
        if(b != null && b.containsKey(Constants.MOVIE_KEY.getValue())) {
            movie = b.getParcelable(Constants.MOVIE_KEY.getValue());
            mTitleTextView.setText(movie.getTitle());
            mYearTextView.setText("Year: " + getYear(movie.getDate()));
            mRatingTextView.setText("Rating: " + movie.getRating() + "/10");
            mSynopsisTextView.setText("Description: " + movie.getSynopsis());

            Picasso.with(this)
                    .load(movie.getImage())
                    .into(mThumbnailImageView);
        }else {
            movie = savedInstanceState.getParcelable(Constants.MOVIE_KEY.getValue());
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putParcelable(Constants.MOVIE_KEY.getValue(), movie);
        super.onSaveInstanceState(outState, outPersistentState);
    }

    private String getYear(String date) {
        return date.split("-")[0];
    }

}
