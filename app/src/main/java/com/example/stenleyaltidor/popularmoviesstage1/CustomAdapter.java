package com.example.stenleyaltidor.popularmoviesstage1;

import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


import java.util.List;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MviewHolder> {
   private final List<Movies> movieList;
    private final Context context;
    final private CustomAdapterListener mcustomAdapterListener;

    public CustomAdapter(List<Movies> mList, Context context, CustomAdapterListener listener) {
        this.movieList = mList;
        this.context = context;
        mcustomAdapterListener = listener;
    }


    @Override
    public MviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movies, parent, false);

        MviewHolder mviewHolder = new MviewHolder(view);
        return mviewHolder;
    }

    @Override
    public void onBindViewHolder(MviewHolder holder, int position) {

        Movies movie = movieList.get(position);

        if (holder != null) {
            Picasso.with(context).load(Utilities.BASE_IMAGE_PATH + movie.getPoster()).into(holder.movieImage);


        }


    }


    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MviewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ImageView movieImage;


        public MviewHolder(View itemView) {
            super(itemView);

            movieImage = (ImageView) itemView.findViewById(R.id.movies_images);
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mcustomAdapterListener.onCustomAdapterItemClick(clickedPosition);
        }
    }


}
