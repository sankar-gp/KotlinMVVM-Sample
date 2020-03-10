package com.sankar.kotlinmvvm.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.sankar.kotlinmvvm.repository.ListViewRepository;
import com.sankar.kotlinmvvm.response.ListViewResponse;


public class ListViewViewModel extends AndroidViewModel {

    private ListViewRepository articleRepository;
    private LiveData<ListViewResponse> articleResponseLiveData;

    public ListViewViewModel(@NonNull Application application) {
        super(application);

        articleRepository = new ListViewRepository();
        this.articleResponseLiveData = articleRepository.getMovieArticles("movies", "84a7decf3110498ea372bd16dd0601e8");
    }

    public LiveData<ListViewResponse> getArticleResponseLiveData() {
        return articleResponseLiveData;
    }
}
