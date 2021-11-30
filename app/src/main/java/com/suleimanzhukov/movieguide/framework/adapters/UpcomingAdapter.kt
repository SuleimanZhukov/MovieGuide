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
import com.suleimanzhukov.movieguide.databinding.RecycleViewUpcomingItemBinding
import com.suleimanzhukov.movieguide.framework.OnItemClickListener
import com.suleimanzhukov.movieguide.framework.ui.details.DetailsFragment
import com.suleimanzhukov.movieguide.model.entities.Movie

class UpcomingAdapter(
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<UpcomingAdapter.UpcomingViewHolder>() {
    private lateinit var binding: RecycleViewUpcomingItemBinding
    private var moviesData: List<Movie> = listOf()

    @SuppressLint("notifyDataSetChanged")
    fun setUpcomingMovies(data: List<Movie>) {
        moviesData = data
        notifyDataSetChanged()
    }

    inner class UpcomingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(movie: Movie) = with(binding) {
            titleUpcoming.text = movie.title
            genreUpcoming.text = movie.genre
            imageViewUpcoming.load(movie.poster)
            root.setOnClickListener { onItemClickListener.onMovieClickListener() }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingViewHolder {
        binding = RecycleViewUpcomingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UpcomingViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: UpcomingViewHolder, position: Int) {
        holder.bind(moviesData[position])
    }

    override fun getItemCount(): Int {
        return moviesData.size
    }
}