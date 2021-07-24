package com.cbedoy.feature_movielist.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.cbedoy.core.data.database.models.Movie
import com.cbedoy.feature_movielist.R

class MovieListAdapter(
    private val onSelectedMovie: (Movie) -> Unit
) : PagingDataAdapter<Movie, MovieViewHolder>(
    MovieListAdapterDiffUtil
){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_holder_movie,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = getItem(position)
        item?.run {
            holder.bind(item)
            holder.itemView.setOnClickListener {
                onSelectedMovie(item)
            }
        }
    }

}

object MovieListAdapterDiffUtil : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.uuid == newItem.uuid
    }

}
