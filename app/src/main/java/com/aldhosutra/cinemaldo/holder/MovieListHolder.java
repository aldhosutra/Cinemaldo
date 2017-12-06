package com.aldhosutra.cinemaldo.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aldhosutra.cinemaldo.R;
import com.aldhosutra.cinemaldo.model.MovieListModel;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by aldho on 06-Dec-17.
 */

public class MovieListHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.txt_movie_title) TextView movTitleList;
    @BindView(R.id.movie_year) TextView movYearList;
    @BindView(R.id.txt_rating) TextView movRatingList;
    @BindView(R.id.movie_image) ImageView movImageList;
    private Context context;

    public MovieListHolder(View itemView, Context context) {
        super(itemView);
        this.context=context;
        ButterKnife.bind(this, itemView);
    }

    public void updateUI(MovieListModel movieListModel){
        final String URL_IMAGE = "http://image.tmdb.org/t/p/w500"+movieListModel.getPosterPath();

        movTitleList.setText(movieListModel.getMovTitle());
        movRatingList.setText(movieListModel.getMovRating());
        Picasso.with(context).load(URL_IMAGE).into(movImageList);
        movYearList.setText(movieListModel.getMovYear());

    }
}
