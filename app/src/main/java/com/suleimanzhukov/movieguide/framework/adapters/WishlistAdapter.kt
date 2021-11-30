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

class WishlistAdapter(
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<WishlistAdapter.WishlistViewHolder>() {
    private lateinit var binding: RecycleViewUpcomingItemBinding
    private var moviesData: List<Movie> = listOf()

    @SuppressLint("notifyDataSetChanged")
    fun setWishlistMovies(data: List<Movie>) {
        moviesData = data
        notifyDataSetChanged()
    }

    inner class WishlistViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(movie: Movie) = with(binding) {
            titleUpcoming.text = movie.title
            genreUpcoming.text = movie.genre
            imageViewUpcoming.load(movie.poster)
            root.setOnClickListener { onItemClickListener.onMovieClickListener() }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishlistViewHolder {
        binding = RecycleViewUpcomingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WishlistViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: WishlistViewHolder, position: Int) {
        holder.bind(moviesData[position])
    }

    override fun getItemCount(): Int {
        return moviesData.size
    }
}