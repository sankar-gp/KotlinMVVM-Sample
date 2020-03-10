package com.sankar.kotlinmvvm.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.sankar.kotlinmvvm.R
import com.sankar.kotlinmvvm.adapter.ListViewAdapter
import com.sankar.kotlinmvvm.model.Model
import com.sankar.kotlinmvvm.viewmodel.ListViewViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class ListViewActivity : AppCompatActivity() {
    private var adapter: ListViewAdapter? = null
    private val articleArrayList =
        ArrayList<Model.Article>()
    var articleViewModel: ListViewViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialization()
        movieArticles
    }

    private fun initialization() {
        my_recycler_view!!.layoutManager = LinearLayoutManager(this@ListViewActivity)
        adapter = ListViewAdapter(this@ListViewActivity, articleArrayList)
        my_recycler_view!!.adapter = adapter

        articleViewModel =
            ViewModelProviders.of(this).get(ListViewViewModel::class.java)
    }

    private val movieArticles: Unit
        private get() {
            articleViewModel!!.articleResponseLiveData
                .observe(this,
                    Observer { articleResponse ->
                        if (articleResponse != null) {
                            progress_circular_movie_article!!.visibility = View.GONE
                            val articles: List<Model.Article>? =
                                articleResponse.articles
                            if (articles != null) {
                                articleArrayList.addAll(articles)
                            }
                            adapter!!.notifyDataSetChanged()
                        }
                    })
        }

    companion object {
        private val TAG = ListViewActivity::class.java.simpleName
    }
}
