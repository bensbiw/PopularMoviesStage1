package SaveMovies;


import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import java.util.ArrayList;

public class SaveFavoriteMovies {
    public static String MOVIE_PREFS;


    public static void saveFavoriteMovie(View v, ArrayList movieDtails,Context context){

        SharedPreferences preferences = context.getSharedPreferences (MOVIE_PREFS, context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();



        String movieDateilsListTostring = "";
        for(int i= 0; i<movieDtails.size();i++){
            movieDateilsListTostring += movieDtails.get(i);
            if(i<movieDtails.size() -1 ){

                movieDateilsListTostring+="slpit";
            }


        }
        System.out.println(movieDateilsListTostring);

//        to clear all the movies
//        editor.clear();

//        adding movies to the list
        editor.putString(String.valueOf(movieDtails.get(0)),movieDateilsListTostring);
        editor.apply();




    }


}
