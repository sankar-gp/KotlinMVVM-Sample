package com.sankar.kotlinmvvm.retrofit;

import com.sankar.kotlinmvvm.response.ListViewResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiRequest {

    @GET("v2/everything/")
    Call<ListViewResponse> getMovieArticles(
            @Query("q") String query,
            @Query("apikey") String apiKey
    );
}
