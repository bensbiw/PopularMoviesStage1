package com.example.stenleyaltidor.popularmoviesstage1;


import org.json.JSONException;
import org.json.JSONObject;

class Utilities {

    private static String base_url = "https://api.themoviedb.org/3/movie/";
    private static final String API_KEY = "";//  API key goes here
    public static  final String TOP_RATED_URL = base_url + "top_rated?api_key=" +API_KEY + "&language=en-US";
    public static  final String POPULAR_URL = base_url + "popular?api_key=" +API_KEY + "&language=en-US";
    public static final String BASE_IMAGE_PATH = "http://image.tmdb.org/t/p/w185/";



    public static JSONObject getJsonObject(String string) throws JSONException{

            JSONObject jsonObject = new JSONObject(string);
            return jsonObject;
    }

    public static String getString(String s,JSONObject jsonObject) throws JSONException{


        return jsonObject.getString(s);
    }

    public static int getInt(String s,JSONObject jsonObject) throws JSONException{


        return jsonObject.getInt(s);
    }

    public static float getFloat(String s,JSONObject jsonObject) throws JSONException{


        return (float) jsonObject.getInt(s);
    }

    public static String getMovieTrailers(String id){


         String MOVIE_TRAILER = "https://api.themoviedb.org/3/movie/" +id + "/videos"+ "?api_key=" + API_KEY;

        return MOVIE_TRAILER;
    }
    public static String getReviews(String id){
        String reviews = "https://api.themoviedb.org/3/movie/"+ id + "/reviews?api_key=" + API_KEY ;

        return reviews;
    }

//    https://api.themoviedb.org/3/movie/157336/videos?api_key=efeeb6f76b2d10a9081ddcfbcfb9466e


}
