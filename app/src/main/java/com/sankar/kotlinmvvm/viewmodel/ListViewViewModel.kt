package com.sankar.kotlinmvvm.viewmodel

import android.app.Application
import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.sankar.kotlinmvvm.model.Model
import com.sankar.kotlinmvvm.repository.ListViewRepository

class ListViewViewModel(@NonNull application: Application?) :
    AndroidViewModel(application!!) {
    private val articleRepository: ListViewRepository
    internal val articleResponseLiveData: LiveData<Model?>
    fun getArticleResponseLiveData(): LiveData<Model?> {
        return articleResponseLiveData
    }

    init {
        articleRepository = ListViewRepository()
        articleResponseLiveData =
            articleRepository.getMovieArticles("movies", "84a7decf3110498ea372bd16dd0601e8")
    }
}