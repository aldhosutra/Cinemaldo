package com.aldhosutra.cinemaldo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aldhosutra.cinemaldo.MainActivity;
import com.aldhosutra.cinemaldo.R;
import com.aldhosutra.cinemaldo.holder.MovieListHolder;
import com.aldhosutra.cinemaldo.model.MovieListModel;

import java.util.ArrayList;

/**
 * Created by aldho on 06-Dec-17.
 */

public class MovieListAdapter extends RecyclerView.Adapter<MovieListHolder> {

    private ArrayList<MovieListModel> mMovieListModels;
    private static String URL_MOVE_ID_SEND = null;
    private Context context;

    public MovieListAdapter(Context context) {
        this.context = context;
    }

    public MovieListAdapter(ArrayList<MovieListModel> mMovieListModels) {
        this.mMovieListModels = mMovieListModels;
    }

    public static String getUrlMoveIdSend() {
        return URL_MOVE_ID_SEND;
    }

    public static void setUrlMoveIdSend(String urlMoveIdSend) {
        URL_MOVE_ID_SEND = urlMoveIdSend;
    }

    @Override
    public MovieListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View card = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_movie_showcase,parent,false);
        return new MovieListHolder(card, parent.getContext());
    }

    @Override
    public void onBindViewHolder(MovieListHolder holder, int position) {
        final MovieListModel movieLists = mMovieListModels.get(position);

        final String URL_BASE = "https://api.themoviedb.org/3/";
        final String URL_SEARCH_ID = "movie/";
        final String URL_API_KEY = "&api_key=2bea38317c7da072ccff5b9ad2bcc5a2";

        holder.updateUI(movieLists);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(view.getContext(),"ONCLICK TOAST",Toast.LENGTH_SHORT).show();

                MainActivity.getMainActivity().loadDetailMovie(movieLists);

                String URL_MOVIE_BY_ID = URL_BASE + URL_SEARCH_ID +movieLists.getId() +"?" + URL_API_KEY + "&append_to_response=videos";
                setUrlMoveIdSend(URL_MOVIE_BY_ID);

//                Toast.makeText(view.getContext(),URL_MOVIE_BY_ID,Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return mMovieListModels.size();
    }
}
