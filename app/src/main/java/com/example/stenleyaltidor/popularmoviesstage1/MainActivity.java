package com.example.stenleyaltidor.popularmoviesstage1;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import SaveMovies.FavoriteMovies;
import SaveMovies.SaveFavoriteMovies;

public class MainActivity extends AppCompatActivity implements CustomAdapterListener {

    private final static String FAVORITE_MOVIES = "favorite_movies";
    private List<Movies> movieslist;
    private ArrayList<String> savedInstanceMovies;

    private Toolbar toolbar;
    private ImageButton favoriteButton;
    private RecyclerView.Adapter customAdapter;
    private SharedPreferences sharedPreferences;
    private String onsaveInstanceMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.app_toolbar);
        toolbar.setTitle("Popular Movies");
        setSupportActionBar(toolbar);


        movieslist = new ArrayList<Movies>();


        if (!isOnline()) {
            buildDialog(MainActivity.this).show();
        } else {

            if (savedInstanceState != null) {

                ArrayList<String> x = savedInstanceState.getStringArrayList(onsaveInstanceMovies);
                int curent = 0;
                while (curent < x.size()) {
                    Movies m = new Movies();
                    m.setPoster(x.get(curent));
                    m.setRelease_date(x.get(curent + 1));
                    m.setOriginal_title(x.get(curent + 2));
                    m.setOverview(x.get(curent + 3));
                    m.setVote_average(x.get(curent + 4));
                    m.setId(x.get(curent + 5));


                    movieslist.add(m);

                    curent += 6;
                }


            } else {
                new BackgroundTask().execute(Utilities.POPULAR_URL);
            }

        }


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.main_recyclerView);
        recyclerView.setHasFixedSize(true);


        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);

        recyclerView.setLayoutManager(layoutManager);


        customAdapter = new CustomAdapter(movieslist, this, this);
        recyclerView.setAdapter(customAdapter);


    }

//    End of onCreate method ......................................................................

    protected void onSaveInstanceState(Bundle outState) {

        ArrayList<String> savingInstance = new ArrayList<String>();
        for (Movies movies : movieslist) {

            savingInstance.add(movies.getPoster());
            savingInstance.add(movies.getRelease_date());
            savingInstance.add(movies.getOriginal_title());
            savingInstance.add(movies.getOverview());
            savingInstance.add(movies.getVote_average());
            savingInstance.add(movies.getId());
        }

        outState.putStringArrayList(onsaveInstanceMovies, savingInstance);


        super.onSaveInstanceState(outState);

    }

    private boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        return isConnected;

    }


    private AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet Connection");
        builder.setMessage("Connect to your Mobile Data or wifi and try again. Press ok to Exit");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        });

        return builder;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mvsetting, menu);
        return true;

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int i = item.getItemId();
        if (i == R.id.popularmv) {

            movieslist.clear();
            toolbar.setTitle("Popular Movies");

            new BackgroundTask().execute(Utilities.POPULAR_URL);


        } else if (i == R.id.topratedmv) {

            movieslist.clear();
            toolbar.setTitle("Top Rated Movies");
            new BackgroundTask().execute(Utilities.TOP_RATED_URL);

        } else if (i == R.id.my_favorite_movie) {
            SharedPreferences sharedPreferences = getSharedPreferences(SaveFavoriteMovies.MOVIE_PREFS, 0);

            if (!sharedPreferences.getAll().isEmpty() == true) {


                FavoriteMovies favoriteMovies = new FavoriteMovies(sharedPreferences);

                movieslist.clear();
                List<Movies> listMovies = favoriteMovies.getFavoriteMovies();


                for (Movies m : listMovies) {
                    movieslist.add(m);
                }


                customAdapter.notifyDataSetChanged();
            } else {
                Toast.makeText(this, "Your Favorite Movie " +
                        "List is Empty Add some Movies to Your List", Toast.LENGTH_SHORT).show();
            }

        }


        return super.onOptionsItemSelected(item);


    }

    @Override
    public void onCustomAdapterItemClick(int clickedIndex) {
        Movies movie = movieslist.get(clickedIndex);

        ArrayList<String> movieDetailList = new ArrayList<>();
        movieDetailList.add(movie.getOriginal_title());
        movieDetailList.add(movie.getPoster());
        movieDetailList.add(movie.getRelease_date());
        movieDetailList.add(movie.getVote_average());
        movieDetailList.add(movie.getOverview());
        movieDetailList.add(movie.getId());


        Intent intent = new Intent(this, MovieDetail.class);
        intent.putExtra("movie", movieDetailList);


        startActivity(intent);


    }


    public class BackgroundTask extends AsyncTask<String, Integer, String> {


        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        final Data data = new Data();



        @Override
        protected void onPreExecute() {
            super.onPreExecute();


            progressDialog.setMessage("\tLoading ...");
            progressDialog.setCancelable(false);
            progressDialog.show();


        }


        @Override
        protected String doInBackground(String... strings) {


            String movie_data = data.getData(strings[0]);



            return movie_data;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


            try {

                if (s != null) {
                    JSONObject jsonObject = Utilities.getJsonObject(s);

                    String results = jsonObject.getString("results");


                    JSONArray jsonArray = jsonObject.getJSONArray("results");


                    for (int i = 0; i < jsonArray.length(); i++) {


                        String movies_string = jsonArray.getString(i);



                        JSONObject jsonObject_from_array = Utilities.getJsonObject(movies_string);


                        Movies movies = new Movies(jsonObject_from_array.getString("poster_path"),


                                jsonObject_from_array.getString("release_date"),
                                jsonObject_from_array.getString("original_title"),
                                jsonObject_from_array.getString("overview"),
                                jsonObject_from_array.getString("vote_average"),
                                jsonObject_from_array.getString("id")


                        );


                        movieslist.add(movies);


                    }

                }

            } catch (JSONException e) {
                e.printStackTrace();

            }


            progressDialog.dismiss();


            customAdapter.notifyDataSetChanged();


        }


    }


}
