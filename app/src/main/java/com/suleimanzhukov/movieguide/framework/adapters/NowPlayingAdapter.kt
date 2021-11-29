package com.suleimanzhukov.movieguide.framework.adapters

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.suleimanzhukov.movieguide.R
import com.suleimanzhukov.movieguide.databinding.RecycleViewNowplayingItemBinding
import com.suleimanzhukov.movieguide.framework.MainActivity
import com.suleimanzhukov.movieguide.framework.OnItemClickListener
import com.suleimanzhukov.movieguide.framework.ui.details.DetailsFragment
import com.suleimanzhukov.movieguide.model.entities.Movie

class NowPlayingAdapter(
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<NowPlayingAdapter.NowPlayingViewHolder>() {
    private lateinit var binding: RecycleViewNowplayingItemBinding
    private var moviesData: List<Movie> = listOf()

    @SuppressLint("notifyDataSetChanged")
    fun setNowPlayingMovies(data: List<Movie>) {
        moviesData = data
        notifyDataSetChanged()
    }

    inner class NowPlayingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(movie: Movie) = with(binding) {
            titleNowPlaying.text = movie.title
            genreNowPlaying.text = movie.genre
            ratingNowPlaying.text = movie.rating
            imageViewNowPlaying.load(movie.poster)
            root.setOnClickListener { onItemClickListener.onMovieClickListener() }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayingViewHolder {
        binding = RecycleViewNowplayingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NowPlayingViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: NowPlayingViewHolder, position: Int) {
        holder.bind(moviesData[position])
    }

    override fun getItemCount(): Int {
        return moviesData.size
    }
}