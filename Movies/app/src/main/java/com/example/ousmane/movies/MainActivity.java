package com.example.ousmane.movies;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements MovieFragment.Callbacks {
    private boolean isTwoPane = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(!isOnline()) {
            alertDialog();
        }
        if(findViewById(R.id.detailContainer) != null) {
            isTwoPane = true;
        }

    }

    private boolean isOnline(){
        ConnectivityManager cm =
                (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if(activeNetwork != null && activeNetwork.isConnected()) {
            return true;
        }else {
            return false;
        }
    }

    private void alertDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("Internet Connection not available !!!");
        alertDialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        AlertDialog dialog = alertDialog.create();
        dialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        MovieFragment movieFragment = (MovieFragment)
                getSupportFragmentManager().findFragmentById(R.id.movie_fragment);
        MovieFragment.FetchMovieTask fetchMovieTask = movieFragment.new FetchMovieTask();
        String baseUrl = "";
        switch (id) {
            case R.id.popular_ation:
                baseUrl = "http://api.themoviedb.org/3/movie/popular?api_key=" + Constants.API_KEY.getValue();
                fetchMovieTask.execute(baseUrl);
                return true;
            case R.id.top_rated_ation:
                baseUrl = "http://api.themoviedb.org/3/movie/top_rated?api_key=" + Constants.API_KEY.getValue();
                fetchMovieTask.execute(baseUrl);
                return true;
            case R.id.settings_ation:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onItemSelected(Movie movie) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.MOVIE_KEY.getValue(), movie);

        if(isTwoPane) {
            DetailMovieFragment detailMovieFragment = new DetailMovieFragment();
            detailMovieFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detailContainer, detailMovieFragment)
                    .commit();
        }else {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}
