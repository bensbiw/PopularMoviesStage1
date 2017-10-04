package SaveMovies;


import android.content.Context;
import android.content.SharedPreferences;
import com.example.stenleyaltidor.popularmoviesstage1.Movies;




import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class FavoriteMovies {

    private List<Movies> movies_list = new ArrayList<Movies>();
    SharedPreferences sharedPreferences;

//    private Context context;



    public FavoriteMovies( SharedPreferences sharedPreferences ) {

//        this.context = context;
        this.sharedPreferences = sharedPreferences;


    }




    public List<Movies> getFavoriteMovies() {

        Map<String, ?> keys = sharedPreferences.getAll();


        for (Map.Entry<String, ?> entry : keys.entrySet()) {
            ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(entry.getValue().toString().split("slpit")));
            String original_title = arrayList.get(0).toString();
            String poster = arrayList.get(1).toString();
            String release_date = arrayList.get(2).toString();
            String vote_average = arrayList.get(3).toString();

            String overview = arrayList.get(4).toString();


            String id = arrayList.get(5).toString();


            Movies favorite_movies = new Movies(poster, release_date, original_title, overview, vote_average, id);


            movies_list.add(favorite_movies);


        }

        return movies_list;
    }

}
