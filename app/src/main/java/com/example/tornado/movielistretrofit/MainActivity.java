package com.example.tornado.movielistretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    List<MoviesModel> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieList=new ArrayList<>();

        recyclerAdapter= new RecyclerAdapter(movieList,this);

        recyclerView=findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerAdapter);


        ApiInterface apiService=ApiClient.getClient().create(ApiInterface.class);
        Call<List<MoviesModel>> call=apiService.getMovies();
        call.enqueue(new Callback<List<MoviesModel>>() {
            @Override
            public void onResponse(Call<List<MoviesModel>> call, Response<List<MoviesModel>> response) {

                movieList=response.body();
                recyclerAdapter.setMovieList(movieList);
                Log.d("TAG","Response = "+movieList);
            }

            @Override
            public void onFailure(Call<List<MoviesModel>> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
                Toast.makeText(MainActivity.this, "Error"+t, Toast.LENGTH_SHORT).show();
            }
        });


    }
}
