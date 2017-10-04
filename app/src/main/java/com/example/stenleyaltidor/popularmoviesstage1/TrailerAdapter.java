package com.example.stenleyaltidor.popularmoviesstage1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class TrailerAdapter  extends BaseAdapter{
    ArrayList<Trailers> trailers;
    private Context context ;

    public  TrailerAdapter(ArrayList<Trailers> trailers, Context context){
        this.trailers = trailers;
        this.context = context.getApplicationContext();
    }
    @Override
    public int getCount() {
        return trailers.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.trailer_list, viewGroup, false);
        }



        ((TextView) view.findViewById(R.id.trailer_number))
                .setText(trailers.get(i).getTrailer_num());
        return view;
    }
}
