package com.cbedoy.feature_moviesearch.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.cbedoy.core.R
import com.cbedoy.core.data.database.models.Movie
import com.cbedoy.core.feature.movie_list.adapter.MovieListAdapterDiffUtil
import com.cbedoy.core.feature.movie_list.adapter.MovieViewHolder

class SearchMovieAdapter (
    private val onSelectedMovie: (Movie) -> Unit
) : ListAdapter<Movie, MovieViewHolder>(
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