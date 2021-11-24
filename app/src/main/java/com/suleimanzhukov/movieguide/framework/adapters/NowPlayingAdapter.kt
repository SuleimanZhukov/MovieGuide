package com.suleimanzhukov.movieguide.framework.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.suleimanzhukov.movieguide.databinding.RecycleViewNowplayingItemBinding
import com.suleimanzhukov.movieguide.framework.ui.main.MainFragment
import com.suleimanzhukov.movieguide.model.entities.Movie

class NowPlayingAdapter(
    private val movieClickListener: MainFragment.OnItemClickListener
) : RecyclerView.Adapter<NowPlayingAdapter.NowPlayingViewHolder>() {
    private lateinit var binding: RecycleViewNowplayingItemBinding
    private var moviesData: List<Movie> = listOf()

    @SuppressLint("notifyDataSetChanged")
    fun setUpcomingMovies(data: List<Movie>) {
        moviesData = data
        notifyDataSetChanged()
    }

    inner class NowPlayingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(movie: Movie) = with(binding) {
            titleNowPlaying.text = movie.title
            genreNowPlaying.text = movie.genre
            ratingNowPlaying.text = movie.rating
            root.setOnClickListener { movieClickListener.onMovieClickListener() }
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