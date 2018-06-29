package com.example.tornado.movielistretrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Tornado on 6/29/2018.
 */

public interface ApiInterface {
    @GET("volley_array.json")
    Call<List<MoviesModel>> getMovies();
}
