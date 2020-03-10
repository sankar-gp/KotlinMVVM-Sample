package com.sankar.kotlinmvvm.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sankar.kotlinmvvm.model.Model
import com.sankar.kotlinmvvm.retrofit.ApiRequest
import com.sankar.kotlinmvvm.retrofit.RetrofitRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ListViewRepository {
    private val apiRequest: ApiRequest
    fun getMovieArticles(query: String?, key: String?): MutableLiveData<Model?> {
        val data = MutableLiveData<Model?>()
        apiRequest.getMovieArticles(query, key)
            ?.enqueue(object : Callback<Model?> {
                override fun onResponse(
                    call: Call<Model?>,
                    response: Response<Model?>
                ) {
                    Log.d(
                        TAG,
                        "onResponse response:: $response"
                    )
                    if (response.body() != null) {
                        data.value = response.body()
                        Log.d(
                            TAG,
                            "articles total result:: " + response.body()!!.totalResults
                        )
                        Log.d(
                            TAG,
                            "articles size:: " + response.body()!!.articles!!.size
                        )
                        Log.d(
                            TAG,
                            "articles title pos 0:: " + response.body()!!.articles!![0].title
                        )
                    }
                }

                override fun onFailure(
                    call: Call<Model?>,
                    t: Throwable
                ) {
                    data.value = null
                }
            })
        return data
    }

    companion object {
        private val TAG = ListViewRepository::class.java.simpleName
    }

    init {
        apiRequest =
            RetrofitRequest.retrofitInstance!!.create(ApiRequest::class.java)
    }
}
