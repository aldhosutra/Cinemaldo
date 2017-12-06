package com.aldhosutra.cinemaldo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aldhosutra.cinemaldo.R;
import com.aldhosutra.cinemaldo.adapter.MovieListAdapter;
import com.aldhosutra.cinemaldo.config.Constant;
import com.aldhosutra.cinemaldo.model.MovieListModel;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class showcaseFragment extends Fragment {

    private ArrayList<MovieListModel> mMovieLists = new ArrayList<>();

    MovieListAdapter movieAdapter;


    public showcaseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_showcase, container, false);
        ButterKnife.bind(this, view);

        final RecyclerViewPager mRecyclerView = (RecyclerViewPager) view.findViewById(R.id.list);
        movieAdapter = new MovieListAdapter(mMovieLists);
        //set adapter
        //You just need to implement ViewPageAdapter by yourself like a normal RecyclerView.Adpater.
        mRecyclerView.setAdapter(movieAdapter);

        // setLayoutManager like normal RecyclerView, you do not need to change any thing.
        LinearLayoutManager layout = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(layout);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLongClickable(true);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int scrollState) {
                // do nothing
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                int childCount = mRecyclerView.getChildCount();
                int width = mRecyclerView.getChildAt(0).getWidth();
                int padding = (mRecyclerView.getWidth() - width) / 2;

                for (int j = 0; j < childCount; j++) {
                    View v = recyclerView.getChildAt(j);
                    float rate = 0;
                    ;
                    if (v.getLeft() <= padding) {
                        if (v.getLeft() >= padding - v.getWidth()) {
                            rate = (padding - v.getLeft()) * 1f / v.getWidth();
                        } else {
                            rate = 1;
                        }
                        v.setScaleY(1 - rate * 0.1f);
                        v.setScaleX(1 - rate * 0.1f);

                    } else {
                        if (v.getLeft() <= recyclerView.getWidth() - padding) {
                            rate = (recyclerView.getWidth() - padding - v.getLeft()) * 1f / v.getWidth();
                        }
                        v.setScaleY(0.9f + rate * 0.1f);
                        v.setScaleX(0.9f + rate * 0.1f);
                    }
                }
            }
        });

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Constant.FULL_API_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //                    JSONArray movieArray = response.getJSONArray("results");
                try {
                    JSONArray array = response.getJSONArray("results");

                    for (int i = 0; i<10;i++){
                        JSONObject object= array.getJSONObject(i);
                        String poster_path = object.getString("poster_path");
                        String title = object.getString("title");

                        String overview = object.getString("overview");
                        String voteAvarage = object.getString("vote_average");
                        String id = object.getString("id");
                        String releaseDate = object.getString("release_date");

                        Log.d("asd",poster_path);
                        System.out.println("COBA= "+poster_path);

                        MovieListModel movieLists =  new MovieListModel(poster_path,title,releaseDate,voteAvarage,overview,id);
                        mMovieLists.add(movieLists);
                        movieAdapter.notifyDataSetChanged();
                    }

                    Log.d("Results",array.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", error.getMessage());
            }
        });

        Volley.newRequestQueue(getContext()).add(jsonObjectRequest);



        return view;
    }

}
