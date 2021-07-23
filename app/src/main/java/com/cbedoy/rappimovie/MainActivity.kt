package com.cbedoy.rappimovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.cbedoy.feature_movielist.presentation.ui.MovieListFragment
import com.cbedoy.rappimovie.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(LayoutInflater.from(this))

        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(binding.container.id, MovieListFragment())
            .commit()
    }
}