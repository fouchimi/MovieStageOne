package com.example.ousmane.movies;

import android.content.Context;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MovieFragment extends Fragment {

    private List<Movie> movies = new ArrayList<>();
    private MovieAdapter mMovieAdapter;
    private Callbacks activity;

    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onStart() {
        super.onStart();
        final String baseUrl = "http://api.themoviedb.org/3/discover/movie?api_key=" + Constants.API_KEY.getValue();
        new FetchMovieTask().execute(baseUrl);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_movie, container, false);
        GridView gridView = (GridView) rootView.findViewById(R.id.gridview);
        mMovieAdapter = new MovieAdapter(getActivity(), R.id.image_thumbnail, movies);
        gridView.setAdapter(mMovieAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Movie movie = mMovieAdapter.getItem(position);
                activity.onItemSelected(movie);
            }
        });
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
       inflater.inflate(R.menu.moviefragment, menu);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (Callbacks) context;
    }

    public interface Callbacks {
        public void onItemSelected(Movie movie);
    }


    public class FetchMovieTask extends AsyncTask<String, Void, List<Movie>> {

        @Override
        protected List<Movie> doInBackground(String... params) {
            String jsonData = new HttpManager().getData(params[0]);
            movies = new MovieJSONParser().parseData(jsonData);
            return movies;
        }

        @Override
        protected void onPostExecute(List<Movie> movies) {
           if(movies != null) {
               mMovieAdapter.clear();
               for(Movie movie : movies) {
                   mMovieAdapter.add(movie);
               }
           }
        }
    }
}
