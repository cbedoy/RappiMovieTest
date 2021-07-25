package com.cbedoy.core.feature.movie_list.adapter

import android.content.res.Resources
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.cbedoy.core.data.database.models.Movie
import com.cbedoy.core.databinding.ViewHolderMovieBinding

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    fun bind(movie: Movie) {
        val binding = ViewHolderMovieBinding.bind(itemView)
        with(binding) {
            val widthPixels = Resources.getSystem().displayMetrics.widthPixels
            cardContainer.layoutParams.apply {
                width = widthPixels / 3
            }
            movieImage.load(movie.image) {
                crossfade(true)
            }
            movieName.text = movie.title
        }
    }

}