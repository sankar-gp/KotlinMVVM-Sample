package com.sankar.kotlinmvvm.view;

import android.os.Bundle;

import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sankar.kotlinmvvm.R;
import com.sankar.kotlinmvvm.adapter.ListViewAdapter;
import com.sankar.kotlinmvvm.model.ListModel;
import com.sankar.kotlinmvvm.response.ListViewResponse;
import com.sankar.kotlinmvvm.viewmodel.ListViewViewModel;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    private static final String TAG = ListViewActivity.class.getSimpleName();
    private RecyclerView my_recycler_view;
    private ProgressBar progress_circular_movie_article;
    private LinearLayoutManager layoutManager;
    private ListViewAdapter adapter;
    private ArrayList<ListModel> articleArrayList = new ArrayList<>();
    ListViewViewModel articleViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialization();

        getMovieArticles();
    }

    /**
     * initialization of views and others
     *
     * @param @null
     */
    private void initialization() {
        progress_circular_movie_article = (ProgressBar) findViewById(R.id.progress_circular_movie_article);
        my_recycler_view = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(ListViewActivity.this);
        my_recycler_view.setLayoutManager(layoutManager);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        my_recycler_view.setHasFixedSize(true);

        // adapter
        adapter = new ListViewAdapter(ListViewActivity.this, articleArrayList);
        my_recycler_view.setAdapter(adapter);

        // View Model
        articleViewModel = ViewModelProviders.of(this).get(ListViewViewModel.class);
    }

    /**
     * get movies articles from news api
     *
     * @param @null
     */
    private void getMovieArticles() {
        articleViewModel.getArticleResponseLiveData().observe(this, new Observer<ListViewResponse>() {
            @Override
            public void onChanged(ListViewResponse articleResponse) {
                if (articleResponse != null) {

                    progress_circular_movie_article.setVisibility(View.GONE);
                    List<ListModel> articles = articleResponse.getArticles();
                    articleArrayList.addAll(articles);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }
}
