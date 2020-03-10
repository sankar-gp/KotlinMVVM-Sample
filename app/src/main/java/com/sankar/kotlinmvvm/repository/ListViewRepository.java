package com.sankar.kotlinmvvm.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sankar.kotlinmvvm.response.ListViewResponse;
import com.sankar.kotlinmvvm.retrofit.ApiRequest;
import com.sankar.kotlinmvvm.retrofit.RetrofitRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListViewRepository {
    private static final String TAG = ListViewRepository.class.getSimpleName();
    private ApiRequest apiRequest;

    public ListViewRepository() {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }

    public LiveData<ListViewResponse> getMovieArticles(String query, String key) {
        final MutableLiveData<ListViewResponse> data = new MutableLiveData<>();
        apiRequest.getMovieArticles(query, key)
                .enqueue(new Callback<ListViewResponse>() {


                    @Override
                    public void onResponse(Call<ListViewResponse> call, Response<ListViewResponse> response) {
                        Log.d(TAG, "onResponse response:: " + response);



                        if (response.body() != null) {
                            data.setValue(response.body());

                            Log.d(TAG, "articles total result:: " + response.body().getTotalResults());
                            Log.d(TAG, "articles size:: " + response.body().getArticles().size());
                            Log.d(TAG, "articles title pos 0:: " + response.body().getArticles().get(0).getTitle());
                        }
                    }

                    @Override
                    public void onFailure(Call<ListViewResponse> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }
}
