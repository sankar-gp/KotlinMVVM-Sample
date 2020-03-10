package com.sankar.kotlinmvvm.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sankar.kotlinmvvm.R
import com.sankar.kotlinmvvm.model.Model
import java.util.*

class ListViewAdapter(
    private val context: Context,
    var articleArrayList: ArrayList<Model.Article>
) :
    RecyclerView.Adapter<ListViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        i: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_each_row_movie_article, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        viewHolder: ViewHolder,
        i: Int
    ) {
        val article = articleArrayList[i]
        viewHolder.tvTitle.text = article.title
        viewHolder.tvAuthorAndPublishedAt.text =
            "-" + article.author + " | " + "Piblishetd At: " + article.publishedAt
        viewHolder.tvDescription.text = article.description
        Glide.with(context)
            .load(article.urlToImage)
            .into(viewHolder.imgViewCover)
    }

    override fun getItemCount(): Int {
        return articleArrayList.size
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val imgViewCover: ImageView
        val tvTitle: TextView
        val tvAuthorAndPublishedAt: TextView
        internal val tvDescription: TextView

        init {
            imgViewCover =
                itemView.findViewById<View>(R.id.imgViewCover) as ImageView
            tvTitle = itemView.findViewById<View>(R.id.tvTitle) as TextView
            tvAuthorAndPublishedAt =
                itemView.findViewById<View>(R.id.tvAuthorAndPublishedAt) as TextView
            tvDescription = itemView.findViewById<View>(R.id.tvDescription) as TextView
        }
    }

}
