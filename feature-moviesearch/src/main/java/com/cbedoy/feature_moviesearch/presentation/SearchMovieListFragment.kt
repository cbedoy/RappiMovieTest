package com.cbedoy.feature_moviesearch.presentation

import android.view.Menu
import android.view.MenuInflater
import android.widget.SearchView
import androidx.fragment.app.Fragment
import com.cbedoy.feature_moviesearch.R

class SearchMovieListFragment : Fragment(){



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.movie_menu, menu)

        val searchView = menu.findItem(R.id.search).actionView as SearchView?
        searchView?.let {
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    //Do your search
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    if (newText.isEmpty()) {

                    }
                    return false
                }
            })
        }
    }
}