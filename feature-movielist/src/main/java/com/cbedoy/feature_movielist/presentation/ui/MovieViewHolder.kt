package com.cbedoy.feature_movielist.presentation.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.cbedoy.core.data.database.models.Movie
import com.cbedoy.feature_movielist.databinding.ViewHolderMovieBinding

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    fun bind(movie: Movie) {
        val binding = ViewHolderMovieBinding.bind(itemView)
        with(binding) {
            movieImage.load(movie.image) {
                crossfade(true)
            }
            movieName.text = movie.title
        }
    }

}