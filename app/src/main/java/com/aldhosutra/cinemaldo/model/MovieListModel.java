package com.aldhosutra.cinemaldo.model;

import android.content.Context;
import android.media.Image;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by aldho on 06-Dec-17.
 */

public class MovieListModel {
    String movTitle;
    String movRating;
    String movYear;
    String posterPath;
    String id;
    String overview;
    ImageView movImageView;

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getMovTitle() {
        return movTitle;
    }

    public void setMovTitle(String movTitle) {
        this.movTitle = movTitle;
    }

    public String getMovRating() {
        return movRating;
    }

    public void setMovRating(String movRating) {
        this.movRating = movRating;
    }

    public String getMovYear() {
        return movYear;
    }

    public void setMovYear(String movYear) {

        SimpleDateFormat formatDefault = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatYear = new SimpleDateFormat("yyyy");

        try {
            Date YearFormat = formatDefault.parse(movYear);
            movYear = formatYear.format(YearFormat);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        this.movYear = movYear;
    }

    public ImageView getMovImageView() {
        return movImageView;
    }

    public void setMovImageView(Context mCon, String url, ImageView movImageView) {
        Picasso.with(mCon).load(url).into(movImageView);
//        this.movImageView = movImageView;
    }

    public MovieListModel(String posterPath, String movTitle, String movYear, String movRating, String overview, String id) {
        this.movTitle = movTitle;
        this.movRating = movRating;
        setMovYear(movYear);
        this.posterPath = posterPath;
        this.id = id;
        this.overview = overview;
    }
}
