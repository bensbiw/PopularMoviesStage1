package com.example.stenleyaltidor.popularmoviesstage1;


import android.content.Context;
import android.content.SharedPreferences;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import java.util.ArrayList;

import java.util.List;


import SaveMovies.FavoriteMovies;
import SaveMovies.SaveFavoriteMovies;


public class MovieDetail extends AppCompatActivity {


    private ArrayList movieDtails;
    private ArrayList<String> movieSavedSate;

    private String title;
    private String image_path;
    private String rls_date;
    private String rating_str;
    private String overview_str;
    private String id;
    private ArrayList<Trailers> trailersArrayList;
    private ArrayList<Reviews> reviewsArrayList;
    private ArrayList<String> reviewsArrayListstr;
    private BaseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_toolbar);
        toolbar.setTitle("Movie Detail");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        TextView mele = (TextView) findViewById(R.id.mele);

        TextView detail_title = (TextView) findViewById(R.id.detail_title);

        TextView release_date = (TextView) findViewById(R.id.release_date_date);
        TextView rating = (TextView) findViewById(R.id.rating);
        TextView overview = (TextView) findViewById(R.id.overview);
        ImageView tnImage = (ImageView) findViewById(R.id.imagethumbnail);
        ImageButton favorite_btn = (ImageButton) findViewById(R.id.favorite_btn);
        ListView trailer_views = (ListView) findViewById(R.id.trailer_list);

        favorite_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveFavoriteMovies.saveFavoriteMovie(view, movieDtails, getApplicationContext());

            }
        });


        if (savedInstanceState == null) {
            Bundle movie = getIntent().getExtras();

            movieDtails = movie.getCharSequenceArrayList("movie");
            String movie_id = movieDtails.get(5).toString();
            SharedPreferences preferences = getSharedPreferences(SaveFavoriteMovies.MOVIE_PREFS, 0);
            FavoriteMovies fv_movies = new FavoriteMovies(preferences);
            List<Movies> movies = fv_movies.getFavoriteMovies();
            for (Movies movie1 : movies) {
                if (movie1.getId().equals(movie_id)) {
                    favorite_btn.setImageResource(R.drawable.star);
                }
            }


        } else {
            movieDtails = savedInstanceState.getStringArrayList("saved_details");
        }

//            title, image , release date , average, overview,id


        title = String.valueOf(movieDtails.get(0));
        image_path = String.valueOf(movieDtails.get(1));
        rls_date = String.valueOf(movieDtails.get(2));
        rating_str = String.valueOf(movieDtails.get(3));
        overview_str = String.valueOf(movieDtails.get(4));
        id = String.valueOf(movieDtails.get(5));







        detail_title.setText(title);
        Picasso.with(this).load(Utilities.BASE_IMAGE_PATH + image_path).into(tnImage);
        release_date.setText(rls_date);
        rating.setText(rating_str);
        overview.setText(overview_str);


        if (id != null) {


                new DetailBackgroudTask().execute(Utilities.getMovieTrailers(id));





        }

//        adapter = new TrailerAdapter(trailersArrayList,this);
//        trailer_views.setAdapter(adapter);

//        System.out.println(trailersArrayList.size());

    }


    // end of oncreate method ............................

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);


        outState.putStringArrayList("saved_details", movieDtails);


    }

//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//
//
//
//
//
//    }

    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.mvsetting, menu);
//        return true;
//
//    }

    //
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }


        return super.onOptionsItemSelected(item);


    }


    private class DetailBackgroudTask extends AsyncTask<String,Integer,String>{
        final Data tdata = new Data();

        @Override
        protected String doInBackground(String... strings) {
            String trailer = tdata.getData(strings[0]);

            return trailer;
        }

        @Override
        protected void onPostExecute(String s) {

            super.onPostExecute(s);



            System.out.println("bens " + s );

        }
    }



}
